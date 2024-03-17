package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.ChantierDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.entities.ChantierEntity;
import com.benfat.pilpose.exception.PilposeTechnicalException;

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
	ChantierEntity addOrUpdateSite(ChantierDto chantier) throws ParseException;

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

	/**
	 * genererLoader chantier
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

	/**
	 * Générer fichier excel Chantier
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderChantier() throws ParseException, IOException;

	/**
	 * Générer fichier csv chantier
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;

}
