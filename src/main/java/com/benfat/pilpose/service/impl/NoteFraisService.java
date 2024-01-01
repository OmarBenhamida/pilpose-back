package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.NoteFraisDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.dao.INoteFraisRepository;
import com.benfat.pilpose.entities.NoteFraisEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.INoteFraisService;
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
public class NoteFraisService implements INoteFraisService {

	private static Logger logger = LoggerFactory.getLogger(NoteFraisService.class);

	@Autowired
	INoteFraisRepository noteFraisRepository;

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

		NoteFraisEntity entity = new NoteFraisEntity();
		List<NoteFraisEntity> list = noteFraisRepository.findAll();
		try {
			entity = NoteFraisDto.dtoToEntity(noteFrais);
			entity.setReference("ref".concat(list.size()+1+""));
			entity = noteFraisRepository.save(entity);
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

}
