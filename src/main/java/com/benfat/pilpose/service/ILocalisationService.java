package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.LocalisationDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.entities.LocalisationEntity;

public interface ILocalisationService {

	/**
	 * Get all villes
	 * 
	 * 
	 * @return List<LocalisationEntity>
	 */
	List<LocalisationEntity> getAllLocalisation();

	/**
	 * 
	 * @param localisation
	 * @return
	 */

	LocalisationEntity addOrUpdateLocalisation(LocalisationDto localisation);

	/**
	 * 
	 * @param idLocalisation
	 * @return
	 */
	boolean deleteLocalisation(Long idLocalisation);

	/**
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;

	/**
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderCommune() throws ParseException, IOException;
	
	/**
	 * genererLoader Client
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

}
