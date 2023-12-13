package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.dao.ITacheRepository;
import com.benfat.pilpose.entities.TacheEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ITacheService;
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
public class TacheService implements ITacheService {

	private static Logger logger = LoggerFactory.getLogger(TacheService.class);

	@Autowired
	ITacheRepository tacheRepository;

	@Override
	public List<TacheEntity> getAllTache() {
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
	public TacheEntity addOrUpdateTache(TacheDto tache) {
		Date dateDeb = new Date();

		TacheEntity entity = new TacheEntity();
		try {

			entity = tacheRepository.save(TacheDto.dtoToEntity(tache));
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

}
