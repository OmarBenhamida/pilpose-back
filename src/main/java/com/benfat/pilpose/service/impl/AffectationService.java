package com.benfat.pilpose.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
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

import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.IAffectationRepository;
import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IAffectationService;
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
public class AffectationService implements IAffectationService {

	private static Logger logger = LoggerFactory.getLogger(AffectationService.class);

	@Autowired
	IAffectationRepository affectationRepository;

	@Override
	public List<AffectationEntity> getAllAffectation() {
		Date dateDeb = new Date();
		List<AffectationEntity> affectation = null;

		try {
			affectation = affectationRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("AffectationService::getAllAffectation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Affectation", dateDeb,
					new Date(), null));
		}
		return affectation;
	}

	@Override
	public AffectationEntity addOrUpdateAffecation(AffectationDto affectation) {

		Date dateDeb = new Date();

		AffectationEntity entity = new AffectationEntity();
		try {

			entity = affectationRepository.save(AffectationDto.dtoToEntity(affectation));
		} catch (Exception e) {
			throw new PilposeBusinessException("AffectationService::addOrUpdateAffecation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update Affectation", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteAffectation(Long idAffectation) {
		Date dateDeb = new Date();

		try {
			affectationRepository.deleteById(idAffectation);

		} catch (Exception e) {
			throw new PilposeBusinessException("AffectationService::deleteAffectation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete Affectation", dateDeb,
					new Date(), null));
		}
		return true;
	}

	@Override
	public List<AffectationEntity> getRefreshedAffectation() {
		Date dateDeb = new Date();
		List<AffectationEntity> affectation = null;

		try {
			affectation = affectationRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("AffectationService::getRefreshedAffectation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get refreshed affectation", dateDeb,
					new Date(), null));
		}
		return affectation;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderAffectation();
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
	public byte[] genererLoaderAffectation() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader()
				.getResourceAsStream(Constants.MODEL_AFFECTATION_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<AffectationDto> dtos = AffectationDto.entitiesToDtos(affectationRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (AffectationDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell salarieCell = PilposeUtils.getXCell(row, 0);
					salarieCell
							.setCellValue(ca.getIdCollaborateur().getNom() + " " + ca.getIdCollaborateur().getPrenom());
					salarieCell.setCellStyle(style);

					Cell libelleTacheCell = PilposeUtils.getXCell(row, 1);
					libelleTacheCell.setCellValue(ca.getIdTache().getLibelle());
					libelleTacheCell.setCellStyle(style);

					Cell dateDebutCell = PilposeUtils.getXCell(row, 2);
					dateDebutCell.setCellValue(ca.getIdTache().getDateDebut());
					dateDebutCell.setCellStyle(style);

					Cell dateFinCell = PilposeUtils.getXCell(row, 3);
					dateFinCell.setCellValue(ca.getIdTache().getDateFin());
					dateFinCell.setCellStyle(style);

					Cell heureDebutCell = PilposeUtils.getXCell(row, 4);
					heureDebutCell.setCellValue(ca.getIdTache().getHeureDebut());
					heureDebutCell.setCellStyle(style);
					
					Cell heureFinCell = PilposeUtils.getXCell(row, 5);
					heureFinCell.setCellValue(ca.getIdTache().getHeureFin());
					heureFinCell.setCellStyle(style);

					Cell chantierCell = PilposeUtils.getXCell(row, 6);
					chantierCell.setCellValue(ca.getIdTache().getNomCompletChantier());
					chantierCell.setCellStyle(style);
					
					Cell responsableCell = PilposeUtils.getXCell(row, 7);
					responsableCell.setCellValue(ca.getIdTache().getNomCompletResponsable());
					responsableCell.setCellStyle(style);
					
					Cell commantaireCell = PilposeUtils.getXCell(row, 8);
					commantaireCell.setCellValue(ca.getIdTache().getCommantaire());
					commantaireCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader affectation généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<AffectationDto> dtos = AffectationDto.entitiesToDtos(affectationRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Salarié");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Libelle Tache");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date début");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date Fin");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Heure début");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Heure Fin");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Chantier");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Responsable");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Commantaire");
	
		writer.write(headerLine.toString());
		writer.newLine();

		for (AffectationDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getIdCollaborateur().getNom() + " " + l.getIdCollaborateur().getPrenom());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getLibelle());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getDateDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getDateFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getHeureDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getHeureFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getNomCompletChantier());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getNomCompletResponsable());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getIdTache().getCommantaire());
			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

}
