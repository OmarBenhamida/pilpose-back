package com.benfat.pilpose.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.controllers.dto.CongeDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.dao.ICongeRepository;
import com.benfat.pilpose.entities.CongeEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ICongeService;
import com.benfat.pilpose.util.Functions;

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
public class CongeService implements ICongeService {

	private static Logger logger = LoggerFactory.getLogger(CongeService.class);

	@Autowired
	ICongeRepository congeRepository;

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
	

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Specify your desired date format
	        String formattedDate = dateFormat.format(dateDeb);
		List<CongeEntity> list =congeRepository.findAll();
		CongeEntity entity = new CongeEntity();
		try {
			entity = CongeDto.dtoToEntity(conge);
			entity.setReference("ref".concat(list.size()+1+""));
			entity.setDateDepot(formattedDate);
			entity = congeRepository.save(entity);
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
		// TODO Auto-generated method stub
		
		 byte[] bytes = file.getBytes();
		 Path path = Paths.get("/pilpose-back/src/main/resources/assets/" + file.getOriginalFilename());
		 Files.write(path, bytes);

		return null;
	}

}
