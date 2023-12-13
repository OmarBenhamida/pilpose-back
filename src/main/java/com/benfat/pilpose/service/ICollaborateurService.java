package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.entities.CollaborateurEntity;
import com.benfat.pilpose.exception.PilposeTechnicalException;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface ICollaborateurService {

	/**
	 * Get all Collaborateur
	 * 
	 * 
	 * @return List<CollaborateurEntity>
	 */
	List<CollaborateurEntity> getAllCollaborateur();

	/**
	 * add or update Collaborateur
	 * 
	 * @param CollaborateurEntity
	 * @return
	 */
	CollaborateurEntity addOrUpdateCollaborateur(CollaborateurDto collaborateur);

	/**
	 * delete Collaborateur
	 * 
	 * @param entity
	 */
	boolean deleteCollaborateur(Long idChantier);

	/**
	 * get Collaborateur by cin
	 * 
	 * @param reference
	 * @return
	 * @throws Exception
	 */
	CollaborateurEntity getCollaborateurByCin(String cin) throws PilposeTechnicalException;

	/**
	 * get refresh chantier
	 * 
	 * @return
	 */
	List<CollaborateurEntity> getRefreshedCollaborateur();

}
