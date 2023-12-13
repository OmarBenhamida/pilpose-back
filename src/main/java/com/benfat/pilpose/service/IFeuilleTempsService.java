package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.entities.FeuilleTempsEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface IFeuilleTempsService {

	/**
	 * Get all FeuilleTemps
	 * 
	 * 
	 * @return List<FeuilleTempsEntity>
	 */
	List<FeuilleTempsEntity> getAllFeuilleTemps();

	/**
	 * add or update FeuilleTemps
	 * 
	 * @param FeuilleTempsEntity
	 * @return
	 */
	FeuilleTempsEntity addOrUpdateFeuilleTemps(FeuilleTempsDto feuilleTemps);

	/**
	 * delete FeuilleTemps
	 * 
	 * @param entity
	 */
	boolean deleteFeuilleTemps(Long idFeuilleTemps);

	/**
	 * get refresh FeuilleTemps
	 * 
	 * @return
	 */
	List<FeuilleTempsEntity> getRefreshedFeuilleTemps();

}
