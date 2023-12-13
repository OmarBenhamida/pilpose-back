package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.dao.IAffectationRepository;
import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IAffectationService;
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

}
