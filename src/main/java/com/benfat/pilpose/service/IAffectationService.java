package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.entities.AffectationEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface IAffectationService {

	/**
	 * Get all affectation
	 * 
	 * 
	 * @return List<AffectationEntity>
	 */
	List<AffectationEntity> getAllAffectation();

	/**
	 * add or update Affectation
	 * 
	 * @param AffectationEntity
	 * @return
	 */
	AffectationEntity addOrUpdateAffecation(AffectationDto affectation);

	/**
	 * delete affectation
	 * 
	 * @param entity
	 */
	boolean deleteAffectation(Long idAffectation);

	/**
	 * get refresh affectation
	 * 
	 * @return
	 */
	List<AffectationEntity> getRefreshedAffectation();

}
