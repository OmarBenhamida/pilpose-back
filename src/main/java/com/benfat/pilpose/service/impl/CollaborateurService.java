package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.dao.ICollaborateurRepository;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.exception.PilposeTechnicalException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ICollaborateurService;
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
public class CollaborateurService implements ICollaborateurService {

	private static Logger logger = LoggerFactory.getLogger(CollaborateurService.class);

	@Autowired
	ICollaborateurRepository collaborateurRepository;

	@Override
	public List<CollaborateurEntity> getAllCollaborateur() {
		Date dateDeb = new Date();
		List<CollaborateurEntity> collaborateur = null;

		try {
			collaborateur = collaborateurRepository.getAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getAllCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public CollaborateurEntity addOrUpdateCollaborateur(CollaborateurDto collaborateur) {
		Date dateDeb = new Date();

		CollaborateurEntity entity = new CollaborateurEntity();
		try {

			entity = collaborateurRepository.save(CollaborateurDto.dtoToEntity(collaborateur));
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::addOrUpdateCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update COLLAB", dateDeb,
					new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteCollaborateur(Long idCollaborateur) {
		Date dateDeb = new Date();

		try {
			collaborateurRepository.deleteById(idCollaborateur);

		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::deleteCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete COLLAB", dateDeb, new Date(),
					null));
		}
		return true;
	}

	@Override
	public CollaborateurEntity getCollaborateurByCin(String cin) throws PilposeTechnicalException {
		Date dateDeb = new Date();
		CollaborateurEntity collaborateur = null;

		try {
			collaborateur = collaborateurRepository.getUserByCin(cin);
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getUserByCin on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

	@Override
	public List<CollaborateurEntity> getRefreshedCollaborateur() {
		Date dateDeb = new Date();
		List<CollaborateurEntity> collaborateur = null;

		try {
			collaborateur = collaborateurRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("CollaborateurService::getAllCollaborateur on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all Collaborateur", dateDeb,
					new Date(), null));
		}
		return collaborateur;
	}

}
