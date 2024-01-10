package com.benfat.pilpose.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benfat.pilpose.dao.ILocalisationRepository;
import com.benfat.pilpose.entities.LocalisationEntity;
import com.benfat.pilpose.enums.OrigineEnum;
import com.benfat.pilpose.exception.PilposeBusinessException;
import com.benfat.pilpose.logging.FactoryLog;
import com.benfat.pilpose.service.ILocalisationService;
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
public class LocalisationService implements ILocalisationService {

	private static Logger logger = LoggerFactory.getLogger(LocalisationService.class);

	@Autowired
	ILocalisationRepository localisationRepository;

	@Override
	public List<LocalisationEntity> getAllLocalisation() {
		Date dateDeb = new Date();
		List<LocalisationEntity> villes = null;

		try {
			villes = localisationRepository.findAll();
		} catch (Exception e) {
			throw new PilposeBusinessException("LocalisationService::getAllLocalisation on line "
					+ Functions.getExceptionLineNumber(e) + " | " + e.getMessage());
		}

		if (logger.isInfoEnabled()) {
			logger.info(FactoryLog.getServLog(OrigineEnum.PILPOSE_AUTH.getValue(), "get all villes²", dateDeb,
					new Date(), null));
		}
		return villes;
	}

}
