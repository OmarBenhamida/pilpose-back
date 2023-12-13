package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.entities.TacheEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface ITacheService {

	/**
	 * Get all Tache
	 * 
	 * 
	 * @return List<TacheEntity>
	 */
	List<TacheEntity> getAllTache();

	/**
	 * add or update Tache
	 * 
	 * @param TacheEntity
	 * @return
	 */
	TacheEntity addOrUpdateTache(TacheDto tache);

	/**
	 * delete Tache
	 * 
	 * @param entity
	 */
	boolean deleteTache(Long idTache);

	/**
	 * get refresh Tache
	 * 
	 * @return
	 */
	List<TacheEntity> getRefreshedTache();

}
