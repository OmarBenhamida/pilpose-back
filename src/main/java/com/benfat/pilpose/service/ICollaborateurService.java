package com.benfat.pilpose.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.PlanningDto;
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
	
	CollaborateurEntity getCollaborateurById(Long id) throws PilposeTechnicalException;

	/**
	 * get refresh chantier
	 * 
	 * @return
	 */
	List<CollaborateurEntity> getRefreshedCollaborateur();

	List<PlanningDto> getPlanningById(Long idC) throws ParseException;

	List<PlanningDto> getPlanningAll() throws ParseException;

}
