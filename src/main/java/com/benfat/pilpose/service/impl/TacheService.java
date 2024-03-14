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

import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.dao.IAffectationRepository;
import com.benfat.pilpose.dao.ITacheRepository;
import com.benfat.pilpose.entities.TacheEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ITacheService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class TacheService implements ITacheService {

	private static Logger logger = LoggerFactory.getLogger(TacheService.class);

	@Autowired
	ITacheRepository tacheRepository;
	@Autowired
	IAffectationRepository affectationRepository;

	@Override
	public List<TacheEntity> getAllTache() {
		Date dateDeb = new Date();
		List<TacheEntity> tache = null;

		try {
			tache = tacheRepository.getAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("TacheService::getAllTache on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Tache", dateDeb, new Date(),
					null));
		}
		return tache;
	}

	@Override
	public TacheEntity addOrUpdateTache(TacheDto tache) {
		Date dateDeb = new Date();
		TacheEntity entity = new TacheEntity();
		try {
			entity = TacheDto.dtoToEntity(tache);
			entity = tacheRepository.save(entity);
		} catch (Exception e) {
			throw new PilposeBusinessException("TacheService::addOrUpdateTache on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update tache", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteTache(Long idTache) {
		Date dateDeb = new Date();

		affectationRepository.deleteAffectationByTache(idTache);

		try {
			tacheRepository.deleteById(idTache);

		} catch (Exception e) {
			throw new PilposeBusinessException("TacheService::deleteTache on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete tache", dateDeb, new Date(),
					null));
		}
		return true;
	}

	@Override
	public List<TacheEntity> getRefreshedTache() {
		Date dateDeb = new Date();
		List<TacheEntity> tache = null;

		try {
			tache = tacheRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("TacheService::getAllTache on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Tache", dateDeb, new Date(),
					null));
		}
		return tache;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderTache();
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
	public byte[] genererLoaderTache() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_TACHE_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<TacheDto> dtos = TacheDto.entitiesToDtos(tacheRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (TacheDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell libelleTacheCell = PilposeUtils.getXCell(row, 0);
					libelleTacheCell.setCellValue(ca.getLibelle());
					libelleTacheCell.setCellStyle(style);

					Cell dateDebutCell = PilposeUtils.getXCell(row, 1);
					dateDebutCell.setCellValue(ca.getDateDebut());
					dateDebutCell.setCellStyle(style);

					Cell dateFinCell = PilposeUtils.getXCell(row, 2);
					dateFinCell.setCellValue(ca.getDateFin());
					dateFinCell.setCellStyle(style);

					Cell heureDebutCell = PilposeUtils.getXCell(row, 3);
					heureDebutCell.setCellValue(ca.getHeureDebut());
					heureDebutCell.setCellStyle(style);

					Cell heureFinCell = PilposeUtils.getXCell(row, 4);
					heureFinCell.setCellValue(ca.getHeureFin());
					heureFinCell.setCellStyle(style);

					Cell chantierCell = PilposeUtils.getXCell(row, 5);
					chantierCell.setCellValue(ca.getNomCompletChantier());
					chantierCell.setCellStyle(style);

					Cell responsableCell = PilposeUtils.getXCell(row, 6);
					responsableCell.setCellValue(ca.getNomCompletResponsable());
					responsableCell.setCellStyle(style);

					Cell commantaireCell = PilposeUtils.getXCell(row, 7);
					commantaireCell.setCellValue(ca.getCommantaire());
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
			logger.info("Loader taches généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<TacheDto> dtos = TacheDto.entitiesToDtos(tacheRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

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

		for (TacheDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getLibelle());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getHeureDebut());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getHeureFin());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getNomCompletChantier());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getNomCompletResponsable());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getCommantaire());
			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

	@Override
	public TacheDto getTacheByAttribute() throws ParseException {

		return TacheDto.entityToDto(tacheRepository.getByAttribute());
	}

	@Override
	public TacheDto getTacheByIdTache(Long idTache) throws ParseException {
		return TacheDto.entityToDto(tacheRepository.getByIdTache(idTache));
	}

}
