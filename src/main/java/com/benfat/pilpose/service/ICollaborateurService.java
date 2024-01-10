package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
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
	 * 
	 * @param id
	 * @return
	 * @throws PilposeTechnicalException
	 */
	CollaborateurEntity getCollaborateurById(Long id) throws PilposeTechnicalException;
	
	/**
	 * 
	 * @param fonction
	 * @return
	 * @throws PilposeTechnicalException
	 */
	List<CollaborateurEntity> getCollaborateurByfonction(String fonction) throws PilposeTechnicalException;

	/**
	 * get refresh chantier
	 * 
	 * @return
	 */
	List<CollaborateurEntity> getRefreshedCollaborateur();

	/**
	 * 
	 * @param idC
	 * @return
	 * @throws ParseException
	 */
	List<PlanningDto> getPlanningById(Long idC) throws ParseException;

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	List<PlanningDto> getPlanningAll() throws ParseException;
	
	
	/**
	 * genererLoader Salaries
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException,IOException;
	
	
	/**
	 * Générer fichier excel 
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderCollaborateur() throws ParseException, IOException;
	
	
	/**
	 * Générer fichier csv 
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException 
	 * @throws CsvDataTypeMismatchException 
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;

}
