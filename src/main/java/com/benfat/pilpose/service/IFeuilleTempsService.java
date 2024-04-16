package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.ChantierRecapDto;
import com.benfat.pilpose.controllers.dto.CollaborateurRecapDto;
import com.benfat.pilpose.controllers.dto.FeuilleTempsDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
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
	 *   get Conge En Cours De Validation Cout
	 * @return
	 */
	int getFeuilleEnCoursDeValidationCout();
	
	/**
	 *   get Conge En Cours De Validation Cout CE
	 * @return
	 */
	int getFeuilleEnCoursDeValidationCoutCE();
	
	
	/**
	 *   get Conge En Cours De Validation Cout RT
	 * @return
	 */
	int getFeuilleEnCoursDeValidationCoutRT();

	/**
	 * add or update FeuilleTemps
	 * 
	 * @param FeuilleTempsEntity
	 * @return
	 */
	boolean addOrUpdateFeuilleTemps(FeuilleTempsDto feuilleTemps);

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
	
	/**
	 * genererLoader FeuilleTemps
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

	/**
	 * Générer fichier excel FeuilleTemps
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderFeuilleTemps() throws ParseException, IOException;

	/**
	 * Générer fichier csv FeuilleTemps
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;
	



}
