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

import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.LocalisationDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.IChantierRepository;
import com.benfat.pilpose.dao.IClientRepository;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.ILocalisationRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.LocalisationEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IChantierService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class ChantierService implements IChantierService {

	private static Logger logger = LoggerFactory.getLogger(ChantierService.class);
	private static final String CSV_SEPARATOR = ";";

	@Autowired
	IChantierRepository chantierRepository;

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Autowired
	ILocalisationRepository localisationRepository;

	@Autowired
	IClientRepository clientRepository;

	@Override
	public List<ChantierEntity> getAllChantier() {
		Date dateDeb = new Date();
		List<ChantierEntity> chantier = null;

		try {
			chantier = chantierRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("ChantierService::getAllChantier on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Chantier", dateDeb,
					new Date(), null));
		}
		return chantier;
	}

	@Override
	public ChantierEntity addOrUpdateSite(ChantierDto chantier) throws ParseException {

		Date dateDeb = new Date();

		if (chantier.getIdChantier() == null) {

			chantier.setEtat("En Cours");

		}

		LocalisationEntity ville = localisationRepository
				.getByIdLocalisation(chantier.getLocalisationDto().getIdLocalisation());

		chantier.setLocalisationDto(LocalisationDto.entityToDto(ville));

		CollaborateurEntity client = collaborateurRepository.getUserById(chantier.getClientDto().getIdCollaborateur());

		chantier.setClientDto(CollaborateurDto.entityToDto(client));
		ChantierEntity entity = new ChantierEntity();
		try {

			entity = chantierRepository.save(ChantierDto.dtoToEntity(chantier));
		} catch (Exception e) {
			throw new PilposeBusinessException("ClientService::addOrUpdateChantier on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update chantier", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteChantier(Long idChantier) {
		Date dateDeb = new Date();

		try {
			chantierRepository.deleteById(idChantier);

		} catch (Exception e) {
			throw new PilposeBusinessException("SiteService::deleteChantier on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete site", dateDeb, new Date(),
					null));
		}
		return true;
	}

	@Override
	public ChantierEntity getChantierByReference(String reference) throws PilposeTechnicalException {
		Date dateDeb = new Date();
		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get chantier By reference", dateDeb,
					new Date(), "chantier reference :" + reference));
		}
		return chantierRepository.getByCode(reference);
	}

	@Override
	public List<ChantierEntity> getRefreshedChantier() {
		Date dateDeb = new Date();
		List<ChantierEntity> chantier = null;

		try {
			chantier = chantierRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("ChantierService::getRefreshedChantier on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get refreshed chantier", dateDeb,
					new Date(), null));
		}
		return chantier;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excelChantier = genererLoaderChantier();
		byte[] chantierCsv = genererLoaderCsv();
		PilposeLoaderResponseDto response = new PilposeLoaderResponseDto();

		response.setPilposeXsl(excelChantier);
		response.setPilposeCsv(chantierCsv);

		if (logger.isInfoEnabled()) {
			logger.info("Les 2 Loaders Excel et CSV  généré avec succées");
		}

		return response;
	}

	@Override
	public byte[] genererLoaderChantier() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream chantierTemplateFile = getClass().getClassLoader()
				.getResourceAsStream(Constants.MODEL_CHANTIER_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(chantierTemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigneChantier = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<ChantierDto> chantierDtos = ChantierDto.entitiesToDtos(chantierRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (ChantierDto ca : chantierDtos) {
					row = PilposeUtils.getXRow(sheet, indexLigneChantier);

					Cell referenceCell = PilposeUtils.getXCell(row, 0);
					referenceCell.setCellValue(ca.getReference());
					referenceCell.setCellStyle(style);

					Cell nomChantierCell = PilposeUtils.getXCell(row, 1);
					nomChantierCell.setCellValue(ca.getNomChantier());
					nomChantierCell.setCellStyle(style);

					Cell clientCell = PilposeUtils.getXCell(row, 2);
					clientCell.setCellValue(ca.getNomCompletClient());
					clientCell.setCellStyle(style);

					Cell localisationCell = PilposeUtils.getXCell(row, 3);
					localisationCell.setCellValue(ca.getVille());
					localisationCell.setCellStyle(style);

					Cell etatCell = PilposeUtils.getXCell(row, 4);
					etatCell.setCellValue(ca.getEtat());
					etatCell.setCellStyle(style);

					indexLigneChantier++;
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
		List<ChantierDto> chantierDtos = ChantierDto.entitiesToDtos(chantierRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Réference");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Nom");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Client");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Localisation");
		headerLine.append(CSV_SEPARATOR);
		headerLine.append("Etat");
		writer.write(headerLine.toString());
		writer.newLine();

		for (ChantierDto l : chantierDtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getReference());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getNomChantier());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getNomCompletClient());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getVille());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(l.getEtat());
			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

}
