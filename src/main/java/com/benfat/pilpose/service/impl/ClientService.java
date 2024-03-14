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

import com.benfat.pilpose.controllers.dto.ClientDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.dao.IChantierRepository;
import com.benfat.pilpose.dao.IClientRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.ClientEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IClientService;
import com.benfat.pilpose.util.Constants;
import com.benfat.pilpose.util.Functions;
import com.benfat.pilpose.util.PilposeUtils;

@Service
@Transactional
public class ClientService implements IClientService {

	private static Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	IClientRepository clientRepository;

	@Autowired
	IChantierRepository chantierRepository;

	@Override
	public List<ClientEntity> getAllClient() {
		Date dateDeb = new Date();
		List<ClientEntity> client = null;

		try {
			client = clientRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("ClientService::getAllClient on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Client", dateDeb,
					new Date(), null));
		}
		return client;
	}

	@Override
	public ClientEntity addOrUpdateClient(ClientDto client) {
		Date dateDeb = new Date();
		ClientEntity entity = new ClientEntity();
		try {
			entity = ClientDto.dtoToEntity(client);
			if (entity.getAdresse().isEmpty() || entity.getAdresse() == "") {

				entity.setAdresse(null);
			}
			if (entity.getTelephone().isEmpty() || entity.getTelephone() == "") {

				entity.setTelephone(null);
			}

			entity = clientRepository.save(entity);
		} catch (Exception e) {
			throw new PilposeBusinessException("ClientService::addOrUpdateClient on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update client", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteClient(Long idClient) {

		List<ChantierEntity> chantiers = chantierRepository.verifierClientchantierEnCours(idClient);

		if (chantiers.isEmpty() || chantiers == null) {

			try {
				clientRepository.deleteById(idClient);

			} catch (Exception e) {
				throw new PilposeBusinessException("ClientService::deleteClient on line "
						+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
			}

			return true;
		} else {

			return false;

		}
	}

	@Override
	public List<ClientEntity> getRefreshedClient() {
		Date dateDeb = new Date();
		List<ClientEntity> client = null;

		try {
			client = clientRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("ClientService::getAllClient on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Client", dateDeb,
					new Date(), null));
		}
		return client;
	}

	@Override
	public PilposeLoaderResponseDto genererLoader() throws ParseException, IOException {

		byte[] excel = genererLoaderClient();
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
	public byte[] genererLoaderClient() throws ParseException, IOException {
		byte[] bytes = {};

		InputStream TemplateFile = getClass().getClassLoader().getResourceAsStream(Constants.MODEL_CLIENTS_TEMPLATE);

		try (XSSFWorkbook workbook = new XSSFWorkbook(TemplateFile)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int indexLigne = 1;
			XSSFRow row = null;

			/** fill the sheets with data */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			try (bos) {
				List<ClientDto> dtos = ClientDto.entitiesToDtos(clientRepository.findAll());

				XSSFCellStyle style = workbook.createCellStyle();
				style.setBorderTop(BorderStyle.MEDIUM);
				style.setBorderBottom(BorderStyle.MEDIUM);
				style.setBorderLeft(BorderStyle.MEDIUM);
				style.setBorderRight(BorderStyle.MEDIUM);
				style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				style.setFillPattern(FillPatternType.FINE_DOTS);

				for (ClientDto ca : dtos) {
					row = PilposeUtils.getXRow(sheet, indexLigne);

					Cell salarieCell = PilposeUtils.getXCell(row, 0);
					salarieCell.setCellValue(ca.getNom());
					salarieCell.setCellStyle(style);

					Cell referenceCell = PilposeUtils.getXCell(row, 1);
					referenceCell.setCellValue(ca.getPrenom());
					referenceCell.setCellStyle(style);

					Cell dateDebutCell = PilposeUtils.getXCell(row, 2);
					dateDebutCell.setCellValue(ca.getAdresse());
					dateDebutCell.setCellStyle(style);

					Cell dateFinCell = PilposeUtils.getXCell(row, 3);
					dateFinCell.setCellValue(ca.getTelephone());
					dateFinCell.setCellStyle(style);

					indexLigne++;
				}

				workbook.write(bos);

			} finally {
				bos.close();
			}
			bytes = bos.toByteArray();

		}
		if (logger.isInfoEnabled()) {
			logger.info("Loader client généré avec succées");
		}

		return bytes;
	}

	@Override
	public byte[] genererLoaderCsv() throws ParseException, IOException {
		List<ClientDto> dtos = ClientDto.entitiesToDtos(clientRepository.findAll());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos));
		StringBuilder headerLine = new StringBuilder();

		headerLine.append("Nom");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Prénom");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Adresse");
		headerLine.append(Constants.CSV_SEPARATOR);
		headerLine.append("Téléphone");

		writer.write(headerLine.toString());
		writer.newLine();

		for (ClientDto l : dtos) {
			StringBuilder oneLine = new StringBuilder();

			oneLine.append(l.getNom());
			oneLine.append(Constants.CSV_SEPARATOR);
			oneLine.append(l.getPrenom());
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
