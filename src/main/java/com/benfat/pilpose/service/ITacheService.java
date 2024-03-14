package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.entities.TacheEntity;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

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

	/**
	 * genererLoader Tache
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

	/**
	 * Générer fichier excel
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderTache() throws ParseException, IOException;

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

	/**
	 *
	 * @return
	 * @throws ParseException
	 */
	TacheDto getTacheByAttribute() throws ParseException;


	/**
	 *
	 * @return
	 * @throws ParseException
	 */
	TacheDto getTacheByIdTache(Long idTache) throws ParseException;

}
