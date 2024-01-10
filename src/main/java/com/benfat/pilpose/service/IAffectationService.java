package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
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
	
	/**
	 * genererLoader Affectation
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException,IOException;
	
	
	/**
	 * Générer fichier excel Affectation
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderAffectation() throws ParseException, IOException;
	
	
	/**
	 * Générer fichier csv Affectation
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException 
	 * @throws CsvDataTypeMismatchException 
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;

}
