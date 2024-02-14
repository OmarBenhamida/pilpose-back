package com.benfat.pilpose.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.controllers.dto.CongeDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.IAffectationRepository;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.ICongeRepository;
import com.benfat.pilpose.dao.ITacheRepository;
import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.CongeEntity;
import com.benfat.pilpose.entities.TacheEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.EmailService;
import com.benfat.pilpose.service.ICongeService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class CongeService implements ICongeService {

	private static Logger logger = LoggerFactory.getLogger(CongeService.class);

	@Autowired
	ICongeRepository congeRepository;

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Autowired
	ITacheRepository tacheRepository;
	@Autowired
	IAffectationRepository affectationRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public List<CongeEntity> getAllConge() {
		Date dateDeb = new Date();
		List<CongeEntity> conge = null;

		try {
			conge = congeRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CongeService::getAllConge on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Conge", dateDeb, new Date(),
					null));
		}
		return conge;
	}

	@Override
	public CongeEntity addOrUpdateConge(CongeDto conge) {
		Date dateDeb = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = dateFormat.format(dateDeb);

		CongeEntity entity = new CongeEntity();

		CollaborateurEntity demandeur = collaborateurRepository
				.getUserById(conge.getIdCollaborateur().getIdCollaborateur());

		if (conge.getIdConge() != null) {

			if (conge.isValidationChefEquipe() && conge.isValidationResponsableAdministratif()
					&& conge.isValidationGerant() && conge.isValidationResponsableTravaux())
			{
				conge.setStatut("Validé");
				emailService.sendEmail(demandeur.getEmail(), "Pilpose - Demande de congé validée ",
						"Bonjour /n Votre demande de congé est validée");
				affectationPlanning(conge, demandeur);
			}
			

		}

		try {
			entity = CongeDto.dtoToEntity(conge);
			if (conge.getIdConge() == null) {
			entity.setReference(entity.getDateDebut() + "-" + entity.getDateFin() + "-" + demandeur.getUsername());
			entity.setDateDepot(formattedDate);
			
			}
			entity = congeRepository.save(entity);

			emailService.sendEmail(demandeur.getEmail(), "Pilpose - Demande de congé crée ",
					"Bonjour /n Votre demande de congé est crée");

		} catch (Exception e) {
			throw new PilposeBusinessException("CongeService::addOrUpdateConge on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update conge", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	void affectationPlanning(CongeDto conge, CollaborateurEntity demandeur) {

		TacheEntity tache = new TacheEntity();
		tache.setDateDebut(conge.getDateDebut());
		tache.setDateFin(conge.getDateFin());
		tache.setHeureDebut(conge.getHeureDebut());
		tache.setHeureFin(conge.getHeureFin());
		tache.setLibelle(conge.getTypeConge());
		tache.setTypeTache("conge");

		tacheRepository.save(tache);

		List<TacheEntity> lastTache = tacheRepository.getLastLineInserted();

		TacheEntity tacheInsere = lastTache.get(0);
		AffectationEntity affectationCongePlannig = new AffectationEntity();

		affectationCongePlannig.setIdTache(tacheInsere);
		affectationCongePlannig.setIdCollaborateur(demandeur);

		affectationRepository.save(affectationCongePlannig);
	}

	@Override
	public boolean deleteConge(Long idConge) {
		Date dateDeb = new Date();

		try {
			congeRepository.deleteById(idConge);

		} catch (Exception e) {
			throw new PilposeBusinessException("CongeService::deleteConge on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete Affectation", dateDeb,
					new Date(), null));
		}
		return true;
	}

	@Override
	public List<CongeEntity> getRefreshedConge() {
		Date dateDeb = new Date();
		List<CongeEntity> conge = null;

		try {
			conge = congeRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CongeService::getAllConge on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Conge", dateDeb, new Date(),
					null));
		}
		return conge;
	}

	@Override
	public String addOrUpdateCongesExcel(MultipartFile file, Long idC) throws IOException {

		byte[] bytes = file.getBytes();
		Path path = Paths.get("/pilpose-back/src/main/resources/assets/" + file.getOriginalFilename());
		Files.write(path, bytes);

		return null;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderConge();
		byte[] csv = genererLoaderCsv();
		PilposeLoaderResponseDto response = new PilposeLoaderResponseDto();

		response.setPilposeXsl(excel);
		response.setPilposeCsv(csv);

		if (logger.isInfoEnabled()) {
			logger.info("Les 2 Loaders Excel et CSV  généré avec succées");
		}

		return response;
	}

	@Override
	public byte[] genererLoaderConge() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_CONGE_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<CongeDto> dtos = CongeDto.entitiesToDtos(congeRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (CongeDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell salarieCell = PilposeUtils.getXCell(row, 0);
					salarieCell.setCellValue(ca.getNomCompletEmploye());
					salarieCell.setCellStyle(style);

					Cell referenceCell = PilposeUtils.getXCell(row, 1);
					referenceCell.setCellValue(ca.getReference());
					referenceCell.setCellStyle(style);

					Cell dateDebutCell = PilposeUtils.getXCell(row, 2);
					dateDebutCell.setCellValue(ca.getDateDebut());
					dateDebutCell.setCellStyle(style);

					Cell dateFinCell = PilposeUtils.getXCell(row, 3);
					dateFinCell.setCellValue(ca.getDateFin());
					dateFinCell.setCellStyle(style);

					Cell heureDebutCell = PilposeUtils.getXCell(row, 4);
					heureDebutCell.setCellValue(ca.getHeureDebut());
					heureDebutCell.setCellStyle(style);

					Cell heureFinCell = PilposeUtils.getXCell(row, 5);
					heureFinCell.setCellValue(ca.getHeureFin());
					heureFinCell.setCellStyle(style);

					Cell dateDepotCell = PilposeUtils.getXCell(row, 6);
					dateDepotCell.setCellValue(ca.getDateDepot());
					dateDepotCell.setCellStyle(style);

					Cell typeCongeCell = PilposeUtils.getXCell(row, 7);
					typeCongeCell.setCellValue(ca.getTypeConge());
					typeCongeCell.setCellStyle(style);

					Cell statusCell = PilposeUtils.getXCell(row, 8);
					statusCell.setCellValue(ca.getStatut());
					statusCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader congés généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<CongeDto> dtos = CongeDto.entitiesToDtos(congeRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Salarié");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Référence");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date début");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date Fin");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Heure début");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Heure Fin");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date dépot");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Type de congé");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Statuts");

		writer.write(headerLine.toString());
		writer.newLine();

		for (CongeDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getNomCompletEmploye());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getReference());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getHeureDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getHeureFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateDepot());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getTypeConge());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getStatut());
			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

}
