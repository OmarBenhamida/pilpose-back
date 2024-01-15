package com.benfat.pilpose.service;

import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.ChantierRecapDto;
import com.benfat.pilpose.controllers.dto.CollaborateurRecapDto;
import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.entities.FeuilleTempsEntity;

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
	
	
	
	/**
	 * get refresh FeuilleTemps
	 * 
	 * @return
	 * @throws ParseException 
	 */
	List<ChantierRecapDto> getChantierRecaps() throws ParseException;
	
	
	/**
	 * get refresh FeuilleTemps
	 * 
	 * @return
	 * @throws ParseException 
	 */
	List<CollaborateurRecapDto> getCollaborateurRecaps() throws ParseException;
	



}
