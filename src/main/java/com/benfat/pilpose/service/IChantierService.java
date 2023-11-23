package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.exception.PilposeTechnicalException;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface IChantierService {

	/**
	 * Get all chantier
	 * 
	 * 
	 * @return List<ChantierEntity>
	 */
	List<ChantierEntity> getAllChantier();

	/**
	 * add or update Chantier
	 * 
	 * @param ChantierEntity
	 * @return
	 */
	ChantierEntity addOrUpdateSite(ChantierDto chantier);

	/**
	 * delete Chnatier
	 * 
	 * @param entity
	 */
	boolean deleteChantier(Long idChantier);

	/**
	 * get chantier by reference
	 * 
	 * @param reference
	 * @return
	 * @throws Exception
	 */
	ChantierEntity getChantierByReference(String reference) throws PilposeTechnicalException;

	/**
	 * get refresh chantier
	 * 
	 * @return
	 */
	List<ChantierEntity> getRefreshedChantier();

}
