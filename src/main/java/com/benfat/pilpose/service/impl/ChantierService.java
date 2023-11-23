package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.dao.IChantierRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IChantierService;
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
public class ChantierService implements IChantierService {

	private static Logger logger = LoggerFactory.getLogger(ChantierService.class);

	@Autowired
	IChantierRepository chantierRepository;

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
	public ChantierEntity addOrUpdateSite(ChantierDto chantier) {

		Date dateDeb = new Date();
		
		if(chantier.getIdChantier() == null) {
			
			chantier.setEtat("En Cours");
			
		}
		
		ChantierEntity entity = new ChantierEntity();
		try {
			
			entity = chantierRepository.save(ChantierDto.dtoToEntity(chantier));
		} catch (Exception e) {
			throw new PilposeBusinessException("SiteService::addOrUpdateChantier on line "
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

}
