package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.AffectationDto;
import com.benfat.pilpose.controllers.dto.CollaborateurDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.controllers.dto.TacheDto;
import com.benfat.pilpose.entities.AffectationEntity;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

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
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

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

	/**
	 *
	 * @param tache
	 * @param idCollab
	 * @return
	 */
	boolean addOrUpdateListAffecation(TacheDto tache, List<Long> idCollab);


	/**
	 * get refresh affectation
	 *
	 * @return
	 * @throws ParseException
	 */
	List<CollaborateurDto> getCollabByIdTache(Long idTache) throws ParseException;

	/**
	 *
	 * @param tache
	 * @param idCollab
	 * @return
	 */
	boolean updateListAffecation(TacheDto tache, List<Long> idCollab);

}
