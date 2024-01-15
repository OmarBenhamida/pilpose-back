package com.benfat.pilpose.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.controllers.dto.ChantierRecapDto;
import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.CollaborateurRecapDto;
import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.dao.IFeuilleTempsRepository;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.entities.FeuilleTempsEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.IFeuilleTempsService;
import com.benfat.pilpose.util.Functions;

@Service
@Transactional
public class FeuilleTempsService implements IFeuilleTempsService {

	private static Logger logger = LoggerFactory.getLogger(FeuilleTempsService.class);

	@Autowired
	IFeuilleTempsRepository feuilleTempsRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<FeuilleTempsEntity> getAllFeuilleTemps() {
		Date dateDeb = new Date();
		List<FeuilleTempsEntity> feuille = null;

		try {
			feuille = feuilleTempsRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::getAllFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all feuille", dateDeb,
					new Date(), null));
		}
		return feuille;
	}

	@Override
	public FeuilleTempsEntity addOrUpdateFeuilleTemps(FeuilleTempsDto feuilleTemps) {
		Date dateDeb = new Date();

		List<FeuilleTempsEntity> list = feuilleTempsRepository.findAll();
		FeuilleTempsEntity entity = new FeuilleTempsEntity();
		try {
			entity = FeuilleTempsDto.dtoToEntity(feuilleTemps);
			entity.setReference("ref".concat(list.size() + 1 + ""));
			entity = feuilleTempsRepository.save(entity);
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::addOrUpdateFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "add or update FeuilleTemps",
					dateDeb, new Date(), null));
		}

		return entity;
	}

	@Override
	public boolean deleteFeuilleTemps(Long idFeuilleTemps) {
		Date dateDeb = new Date();

		try {
			feuilleTempsRepository.deleteById(idFeuilleTemps);

		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::addOrUpdateFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "delete FeuilleTemps", dateDeb,
					new Date(), null));
		}
		return true;
	}

	@Override
	public List<FeuilleTempsEntity> getRefreshedFeuilleTemps() {
		Date dateDeb = new Date();
		List<FeuilleTempsEntity> feuille = null;

		try {
			feuille = feuilleTempsRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("FeuilleTempsService::getAllFeuilleTemps on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all feuille", dateDeb,
					new Date(), null));
		}
		return feuille;
	}

	@Override
	public List<ChantierRecapDto> getChantierRecaps() throws ParseException {
		List<ChantierEntity> chantiers = feuilleTempsRepository.getIdsChantier();

		List<ChantierRecapDto> list = new ArrayList<>();

		for (ChantierEntity c : chantiers) {

			int count = feuilleTempsRepository.getChantierCout(c.getIdChantier());

			ChantierRecapDto chantier = new ChantierRecapDto();

			chantier.setIdChantier(ChantierDto.entityToDto(c));
			chantier.setTotalHeuresTravaille(count);

			list.add(chantier);
		}

		return list;
	}

	@Override
	public List<CollaborateurRecapDto> getCollaborateurRecaps() throws ParseException {
		List<CollaborateurEntity> collab = feuilleTempsRepository.getIdsCollaborateur();

		List<CollaborateurRecapDto> list = new ArrayList<>();

		for (CollaborateurEntity c : collab) {

			int count = feuilleTempsRepository.getCollabCout(c.getIdCollaborateur());

			CollaborateurRecapDto salarie = new CollaborateurRecapDto();

			salarie.setIdCollaborateur(CollaborateurDto.entityToDto(c));
			salarie.setTotalHeuresTravaille(count);

			list.add(salarie);
		}

		return list;
	}

}
