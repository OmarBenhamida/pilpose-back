package com.benfat.pilpose.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.controllers.dto.PlanningDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.dao.IAffectationRepository;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.ITacheRepository;
import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ICollaborateurService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

/**
 * Site service
 * 
 * @inteface ISiteService
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@Service
@Transactional
public class CollaborateurService implements ICollaborateurService {

	private static Logger logger = LoggerFactory.getLogger(CollaborateurService.class);

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Autowired
	ITacheRepository tacheRepository;

	@Autowired
	IAffectationRepository affectationRepository;

	@Override
	public List<CollaborateurEntity> getAllCollaborateur() {
		Date dateDeb = new Date();
		List<CollaborateurEntity> collaborateur = null;

		try {
			collaborateur = collaborateurRepository.getAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getAllCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public CollaborateurEntity addOrUpdateCollaborateur(CollaborateurDto collaborateur) {
		Date dateDeb = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Specify your desired date format
		String formattedDate = dateFormat.format(dateDeb);
		CollaborateurEntity entity = new CollaborateurEntity();
		try {
			entity = CollaborateurDto.dtoToEntity(collaborateur);
			entity.setDateCreation(formattedDate);
			entity = collaborateurRepository.save(entity);
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::addOrUpdateCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update COLLAB", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteCollaborateur(Long idCollaborateur) {
		Date dateDeb = new Date();

		try {
			collaborateurRepository.deleteById(idCollaborateur);

		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::deleteCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete COLLAB", dateDeb, new Date(),
					null));
		}
		return true;
	}

	@Override
	public CollaborateurEntity getCollaborateurById(Long id) throws PilposeTechnicalException {
		Date dateDeb = new Date();
		CollaborateurEntity collaborateur = null;

		try {
			collaborateur = collaborateurRepository.getUserById(id);
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getUserByCin on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public List<CollaborateurEntity> getRefreshedCollaborateur() {
		Date dateDeb = new Date();
		List<CollaborateurEntity> collaborateur = null;

		try {
			collaborateur = collaborateurRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getAllCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public List<PlanningDto> getPlanningById(Long idC) throws ParseException {
		List<PlanningDto> list = new ArrayList<>();
		List<AffectationEntity> affectationEntities = affectationRepository.getByIdCollab(idC);
		for (AffectationEntity num : affectationEntities) {

			PlanningDto dto = new PlanningDto();
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(num.getIdCollaborateur()));
			dto.setIdTache(TacheDto.entityToDto(num.getIdTache()));

			list.add(dto);
		}

		return list;
	}

	@Override
	public List<PlanningDto> getPlanningAll() throws ParseException {
		List<PlanningDto> list = new ArrayList<>();
		List<AffectationEntity> affectationEntities = affectationRepository.findAll();
		for (AffectationEntity num : affectationEntities) {

			PlanningDto dto = new PlanningDto();
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(num.getIdCollaborateur()));
			dto.setIdTache(TacheDto.entityToDto(num.getIdTache()));

			list.add(dto);
		}

		return list;
	}

	@Override
	public List<CollaborateurEntity> getCollaborateurByfonction(String fonction) throws PilposeTechnicalException {
		Date dateDeb = new Date();
		List<CollaborateurEntity> collaborateur = null;

		try {
			collaborateur = collaborateurRepository.getUserByFonction(fonction);
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getUserByFonction on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderCollaborateur();
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
	public byte[] genererLoaderCollaborateur() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_SALARIES_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<CollaborateurDto> dtos = CollaborateurDto.entitiesToDtos(collaborateurRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (CollaborateurDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell nomCell = PilposeUtils.getXCell(row, 0);
					nomCell.setCellValue(ca.getNom());
					nomCell.setCellStyle(style);

					Cell prenomCell = PilposeUtils.getXCell(row, 1);
					prenomCell.setCellValue(ca.getPrenom());
					prenomCell.setCellStyle(style);

					Cell dateNaissanceCell = PilposeUtils.getXCell(row, 2);
					dateNaissanceCell.setCellValue(ca.getDateNaissance());
					dateNaissanceCell.setCellStyle(style);

					Cell dateEmbaucheCell = PilposeUtils.getXCell(row, 3);
					dateEmbaucheCell.setCellValue(ca.getDateEmbauche());
					dateEmbaucheCell.setCellStyle(style);

					Cell fonctionCell = PilposeUtils.getXCell(row, 4);
					fonctionCell.setCellValue(ca.getFonction());
					fonctionCell.setCellStyle(style);

					Cell emailCell = PilposeUtils.getXCell(row, 5);
					emailCell.setCellValue(ca.getEmail());
					emailCell.setCellStyle(style);

					Cell usernameCell = PilposeUtils.getXCell(row, 6);
					usernameCell.setCellValue(ca.getUsername());
					usernameCell.setCellStyle(style);

					Cell roletCell = PilposeUtils.getXCell(row, 7);
					roletCell.setCellValue(ca.getRole());
					roletCell.setCellStyle(style);

					Cell adresseCell = PilposeUtils.getXCell(row, 8);
					adresseCell.setCellValue(ca.getAdresse());
					adresseCell.setCellStyle(style);

					Cell telephoneCell = PilposeUtils.getXCell(row, 9);
					telephoneCell.setCellValue(ca.getTelephone());
					telephoneCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader salarié généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<CollaborateurDto> dtos = CollaborateurDto.entitiesToDtos(collaborateurRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Nom");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Prénom");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date de naissance");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("date d'embauche");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Fonction");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Email");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Username");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Role");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Adresse");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Téléphone");

		writer.write(headerLine.toString());
		writer.newLine();

		for (CollaborateurDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getNom());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getPrenom());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateNaissance());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateEmbauche());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getFonction());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getEmail());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getUsername());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getRole());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getAdresse());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getTelephone());

			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

}
