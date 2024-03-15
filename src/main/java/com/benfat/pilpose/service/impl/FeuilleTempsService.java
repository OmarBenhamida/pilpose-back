package com.benfat.pilpose.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.controllers.dto.ChantierRecapDto;
import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.CollaborateurRecapDto;
import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.IFeuilleTempsRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.FeuilleTempsEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.EmailService;
import com.benfat.pilpose.service.IFeuilleTempsService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class FeuilleTempsService implements IFeuilleTempsService {

	private static Logger logger = LoggerFactory.getLogger(FeuilleTempsService.class);
	private static final String CSV_SEPARATOR = ";";

	@Autowired
	IFeuilleTempsRepository feuilleTempsRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EmailService emailService;

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Override
	public List<FeuilleTempsEntity> getAllFeuilleTemps() {
		Date dateDeb = new Date();
		List<FeuilleTempsEntity> feuille = null;

		try {
			feuille = feuilleTempsRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::getAllFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all feuille", dateDeb,
					new Date(), null));
		}
		return feuille;
	}

	@Override
	public FeuilleTempsEntity addOrUpdateFeuilleTemps(FeuilleTempsDto feuilleTemps) {
		Date dateDeb = new Date();

		try {
			FeuilleTempsEntity entity = FeuilleTempsDto.dtoToEntity(feuilleTemps);
			CollaborateurEntity demandeur = collaborateurRepository
					.getUserById(feuilleTemps.getIdCollaborateur().getIdCollaborateur());

			/** Création d'une nouvelle feuille de temps */
			if (entity.getIdFeuilleTemps() == null) {
				entity.setStatut("En cours de validation");
				List<FeuilleTempsEntity> list = feuilleTempsRepository.findAll();
				entity.setReference("ref" + (list.size() + 1));
			}

			/** Modification d'une feuille de temps */
			if (feuilleTemps.getIdFeuilleTemps() != null) {
				String fonctionCollaborateur = demandeur.getFonction();
				boolean validationGerant = feuilleTemps.isValidationGerant();

				if (fonctionCollaborateur.equals(ConstantsApplication.CHEF_EQUIPE)
						|| fonctionCollaborateur.equals(ConstantsApplication.SALARIE)
						|| fonctionCollaborateur.equals(ConstantsApplication.R_T)
						|| fonctionCollaborateur.equals(ConstantsApplication.R_A)) {

					if (validationGerant) {
						entity.setStatut("Validé");
						entity.setValidationChefEquipe(true);
						entity.setValidationResponsableTravaux(true);
						emailService.sendEmail(demandeur.getEmail(), "Pilpose - Feuille de temps validée ",
								"Bonjour,\nVotre feuille de temps est validée");
					}
				}
			}

			entity = feuilleTempsRepository.save(entity);

			/** Envoi d'un email si une nouvelle feuille de temps a été créée */
			if (feuilleTemps.getIdFeuilleTemps() == null) {
				emailService.sendEmail(demandeur.getEmail(), "Pilpose - Feuille de temps créée ",
						"Bonjour,\nVotre feuille de temps est créée");
			}

			return entity;
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::addOrUpdateFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		} finally {
			if (logger.isInfoEnabled()) {
				logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update FeuilleTemps",
						dateDeb, new Date(), null));
			}
		}
	}

	@Override
	public boolean deleteFeuilleTemps(Long idFeuilleTemps) {
		Date dateDeb = new Date();

		try {
			feuilleTempsRepository.deleteById(idFeuilleTemps);

		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::addOrUpdateFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete FeuilleTemps", dateDeb,
					new Date(), null));
		}
		return true;
	}

	@Override
	public List<FeuilleTempsEntity> getRefreshedFeuilleTemps() {
		Date dateDeb = new Date();
		List<FeuilleTempsEntity> feuille = null;

		try {
			feuille = feuilleTempsRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::getAllFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all feuille", dateDeb,
					new Date(), null));
		}
		return feuille;
	}

	@Override
	public List<ChantierRecapDto> getChantierRecaps() throws ParseException {
		List<ChantierEntity> chantiers = feuilleTempsRepository.getIdsChantier();

		List<ChantierRecapDto> list = new ArrayList<>();

		for (ChantierEntity c : chantiers) {

			int count = feuilleTempsRepository.getChantierCout(c.getIdChantier());

			ChantierRecapDto chantier = new ChantierRecapDto();

			chantier.setIdChantier(ChantierDto.entityToDto(c));
			chantier.setTotalHeuresTravaille(count);

			list.add(chantier);
		}

		return list;
	}

	@Override
	public List<CollaborateurRecapDto> getCollaborateurRecaps() throws ParseException {
		List<CollaborateurEntity> collab = feuilleTempsRepository.getIdsCollaborateur();

		List<CollaborateurRecapDto> list = new ArrayList<>();

		for (CollaborateurEntity c : collab) {

			int count = feuilleTempsRepository.getCollabCout(c.getIdCollaborateur());

			CollaborateurRecapDto salarie = new CollaborateurRecapDto();

			salarie.setIdCollaborateur(CollaborateurDto.entityToDto(c));
			salarie.setTotalHeuresTravaille(count);

			list.add(salarie);
		}

		return list;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excelFeuille = genererLoaderFeuilleTemps();
		byte[] feuilleCsv = genererLoaderCsv();
		PilposeLoaderResponseDto response = new PilposeLoaderResponseDto();

		response.setPilposeXsl(excelFeuille);
		response.setPilposeCsv(feuilleCsv);

		if (logger.isInfoEnabled()) {
			logger.info("Les 2 Loaders Excel et CSV  généré avec succées");
		}

		return response;
	}

	@Override
	public byte[] genererLoaderFeuilleTemps() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream feuilleTemplateFile = getClass().getClassLoader()
				.getResourceAsStream(Constants.MODEL_FEUILLE_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(feuilleTemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigneFeuilleTemps = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<FeuilleTempsDto> feuilleDtos = FeuilleTempsDto.entitiesToDtos(feuilleTempsRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (FeuilleTempsDto ca : feuilleDtos) {
					row = PilposeUtils.getXRow(sheet, indexLigneFeuilleTemps);

					Cell typeTravauxCell = PilposeUtils.getXCell(row, 0);
					typeTravauxCell.setCellValue(ca.getTypeTravaux());
					typeTravauxCell.setCellStyle(style);

					Cell dateCell = PilposeUtils.getXCell(row, 1);
					dateCell.setCellValue(ca.getJourSemaine());
					dateCell.setCellStyle(style);

					Cell nbrHeureCell = PilposeUtils.getXCell(row, 2);
					nbrHeureCell.setCellValue(ca.getHeureTravaille());
					nbrHeureCell.setCellStyle(style);

					Cell chantierCell = PilposeUtils.getXCell(row, 3);
					chantierCell.setCellValue(ca.getNomCompletChantier());
					chantierCell.setCellStyle(style);

					Cell vehiculeCell = PilposeUtils.getXCell(row, 4);
					vehiculeCell.setCellValue(ca.getVehicule());
					vehiculeCell.setCellStyle(style);

					Cell communeDeCell = PilposeUtils.getXCell(row, 5);
					communeDeCell.setCellValue(ca.getVehiculeSuite());
					communeDeCell.setCellStyle(style);

					Cell commantaireCell = PilposeUtils.getXCell(row, 6);
					commantaireCell.setCellValue(ca.getCommantaire());
					commantaireCell.setCellStyle(style);

					Cell kmCell = PilposeUtils.getXCell(row, 7);
					kmCell.setCellValue(ca.getKm());
					kmCell.setCellStyle(style);

					Cell salarieCell = PilposeUtils.getXCell(row, 8);
					salarieCell.setCellValue(ca.getNomCompletSalarie());
					salarieCell.setCellStyle(style);

					Cell chefEquipeCell = PilposeUtils.getXCell(row, 9);
					chefEquipeCell.setCellValue(ca.getNomCompletResponsable());
					chefEquipeCell.setCellStyle(style);

					Cell indimniteCell = PilposeUtils.getXCell(row, 10);
					indimniteCell.setCellValue(ca.isIndemnite());
					indimniteCell.setCellStyle(style);

					Cell metierCell = PilposeUtils.getXCell(row, 11);
					metierCell.setCellValue(ca.getMetier());
					metierCell.setCellStyle(style);

					Cell etatCell = PilposeUtils.getXCell(row, 12);
					etatCell.setCellValue(ca.getStatut());
					etatCell.setCellStyle(style);

					indexLigneFeuilleTemps++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader chantiergénéré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<FeuilleTempsDto> feuilleDtos = FeuilleTempsDto.entitiesToDtos(feuilleTempsRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Type Travaux");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Date");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Nombre heure");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Chantier");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Véhicule");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Commune de");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Commantaire");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("KM");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Salarié");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Responsable");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Indimnité");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Métier");
		headerLine.append(CSV_SEPARATOR);

		headerLine.append("Etat");
		writer.write(headerLine.toString());
		writer.newLine();

		for (FeuilleTempsDto l : feuilleDtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getTypeTravaux());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getJourSemaine());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getHeureTravaille());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getNomCompletChantier());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getVehicule());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getVehiculeSuite());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getCommantaire());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getKm());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getNomCompletSalarie());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getNomCompletResponsable());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.isIndemnite());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getMetier());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getStatut());
			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

}
