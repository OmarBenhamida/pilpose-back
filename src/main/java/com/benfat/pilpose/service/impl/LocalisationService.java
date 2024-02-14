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

import com.benfat.pilpose.controllers.dto.LocalisationDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.IChantierRepository;
import com.benfat.pilpose.dao.ILocalisationRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.LocalisationEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ILocalisationService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class LocalisationService implements ILocalisationService {

	private static Logger logger = LoggerFactory.getLogger(LocalisationService.class);

	@Autowired
	ILocalisationRepository localisationRepository;

	@Autowired
	IChantierRepository chantierRepository;

	@Override
	public List<LocalisationEntity> getAllLocalisation() {
		Date dateDeb = new Date();
		List<LocalisationEntity> villes = null;

		try {
			villes = localisationRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("LocalisationService::getAllLocalisation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all villes²", dateDeb,
					new Date(), null));
		}
		return villes;
	}

	@Override
	public LocalisationEntity addOrUpdateLocalisation(LocalisationDto localisation) {
		Date dateDeb = new Date();
		LocalisationEntity entity = new LocalisationEntity();
		if (localisation != null) {
			try {
				entity = LocalisationDto.dtoToEntity(localisation);
				entity = localisationRepository.save(entity);
			} catch (ParseException e) {
				throw new PilposeBusinessException("LocalisationService::addLocalisation on line "
						+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "Localisation ajoutee avec succes",
					dateDeb, new Date(), null));
		}
		return entity;
	}

	@Override
	public boolean deleteLocalisation(Long idLocalisation) {
		List<ChantierEntity> chantiers = chantierRepository.verifierCommunechantierEnCours(idLocalisation);

		if (chantiers.isEmpty() || chantiers == null) {

			try {
				localisationRepository.deleteById(idLocalisation);

			} catch (Exception e) {
				throw new PilposeBusinessException("LocalisationService::deleteClient on line "
						+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
			}

			return true;
		} else {

			return false;

		}
	}

	@Override
	public byte[] genererLoaderCommune() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_COMMUNES_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<LocalisationDto> dtos = LocalisationDto.entitiesToDtos(localisationRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (LocalisationDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell salarieCell = PilposeUtils.getXCell(row, 0);
					salarieCell.setCellValue(ca.getVille());
					salarieCell.setCellStyle(style);

					Cell referenceCell = PilposeUtils.getXCell(row, 1);
					referenceCell.setCellValue(ca.getCodePostale());
					referenceCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader communes généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<LocalisationDto> dtos = LocalisationDto.entitiesToDtos(localisationRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Ville");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Code postale");

		writer.write(headerLine.toString());
		writer.newLine();

		for (LocalisationDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getVille());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getCodePostale());

			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderCommune();
		byte[] csv = genererLoaderCsv();
		PilposeLoaderResponseDto response = new PilposeLoaderResponseDto();

		response.setPilposeXsl(excel);
		response.setPilposeCsv(csv);

		if (logger.isInfoEnabled()) {
			logger.info("Les 2 Loaders Excel et CSV  généré avec succées");
		}

		return response;
	}

}
