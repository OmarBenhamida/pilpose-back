package com.benfat.pilpose.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.ConstantsApplication;
import com.benfat.pilpose.controllers.dto.NoteFraisDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.dao.INoteFraisRepository;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.NoteFraisEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.EmailService;
import com.benfat.pilpose.service.INoteFraisService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class NoteFraisService implements INoteFraisService {

	private static Logger logger = LoggerFactory.getLogger(NoteFraisService.class);

	@Autowired
	INoteFraisRepository noteFraisRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Override
	public List<NoteFraisEntity> getAllNoteFrais() {
		Date dateDeb = new Date();
		List<NoteFraisEntity> note = null;

		try {
			note = noteFraisRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("NoteFraisService::getAllNoteFrais on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all note frais", dateDeb,
					new Date(), null));
		}
		return note;
	}

	@Override
	public NoteFraisEntity addOrUpdateNoteFrais(NoteFraisDto noteFrais) {
		Date dateDeb = new Date();
		CollaborateurEntity demandeur = collaborateurRepository
				.getUserById(noteFrais.getIdCollaborateur().getIdCollaborateur());
		NoteFraisEntity entitySec = new NoteFraisEntity();
		NoteFraisEntity entity = new NoteFraisEntity();
		List<NoteFraisEntity> list = noteFraisRepository.findAll();
		if (noteFrais.getIdNoteFrais() != null) {
			entitySec = noteFraisRepository.findById(noteFrais.getIdNoteFrais()).orElse(null);
		}
		try {

			entity = NoteFraisDto.dtoToEntity(noteFrais);
			if (entitySec != null) {
				entity.setPathRecu(entitySec.getPathRecu());
			}
			if (entity.getIdNoteFrais() == null) {
				entity.setReference("ref".concat(list.size() + 1 + ""));

				entity.setStatut("En cours de validation");

			}

			entity = noteFraisRepository.save(entity);
			emailService.sendEmail(demandeur.getEmail(), "Pilpose - Note de service crée ",
					"Bonjour /n Votre note de service est crée");

		} catch (Exception e) {
			throw new PilposeBusinessException("NoteFraisService::addOrUpdateNoteFrais on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update Affectation", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteNoteFrais(Long idNoteFrais) {
		Date dateDeb = new Date();

		try {
			noteFraisRepository.deleteById(idNoteFrais);

		} catch (Exception e) {
			throw new PilposeBusinessException("NoteFraisService::deleteNoteFrais on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete Note Frais", dateDeb,
					new Date(), null));
		}
		return true;
	}

	@Override
	public List<NoteFraisEntity> getRefreshedNoteFrais() {
		Date dateDeb = new Date();
		List<NoteFraisEntity> note = null;

		try {
			note = noteFraisRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("NoteFraisService::getAllNoteFrais on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all note frais", dateDeb,
					new Date(), null));
		}
		return note;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderNoteFrais();
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
	public byte[] genererLoaderNoteFrais() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_NOTE_FRAIS_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<NoteFraisDto> dtos = NoteFraisDto.entitiesToDtos(noteFraisRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (NoteFraisDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell salarieCell = PilposeUtils.getXCell(row, 0);
					salarieCell.setCellValue(ca.getReference());
					salarieCell.setCellStyle(style);

					Cell referenceCell = PilposeUtils.getXCell(row, 1);
					referenceCell.setCellValue(ca.getTypeNote());
					referenceCell.setCellStyle(style);

					Cell dateDebutCell = PilposeUtils.getXCell(row, 2);
					dateDebutCell.setCellValue(ca.getDateNote());
					dateDebutCell.setCellStyle(style);

					Cell dateFinCell = PilposeUtils.getXCell(row, 3);
					dateFinCell.setCellValue(ca.getNomCompletEmploye());
					dateFinCell.setCellStyle(style);

					Cell statutCell = PilposeUtils.getXCell(row, 4);
					statutCell.setCellValue(ca.getStatut());
					statutCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader notes de frais  généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<NoteFraisDto> dtos = NoteFraisDto.entitiesToDtos(noteFraisRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Référence");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Type de note");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Date de note");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Employé");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Staut");

		writer.write(headerLine.toString());
		writer.newLine();

		for (NoteFraisDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getReference());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getTypeNote());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getDateNote());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getNomCompletEmploye());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getStatut());

			writer.write(oneLine.toString());
			writer.newLine();

		}
		writer.flush();
		writer.close();

		return baos.toByteArray();

	}

	public void addOrRenameFile(String newPath, byte[] data) throws IOException {
		File oldFile = new File(newPath);
		writeDataToFile(oldFile, data);
	}

	public void writeDataToFile(File file, byte[] data) throws IOException {
		writeData(file, data);
	}

	public void writeData(File file, byte[] data) throws IOException {
		try {
			FileOutputStream output = new FileOutputStream(file);
			output.write(data);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public NoteFraisEntity addNoteWithRecu(NoteFraisEntity fileEntity, MultipartFile file) throws IOException {
		String newTemplateFileName = fileEntity.getReference() + "_" + "note.jpeg";
		addOrRenameFile(ConstantsApplication.ASSETS.concat(newTemplateFileName), file.getBytes());
		fileEntity.setPathRecu(newTemplateFileName);
		noteFraisRepository.save(fileEntity);
		return fileEntity;
	}

}
