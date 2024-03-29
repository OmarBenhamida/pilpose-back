package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.controllers.dto.CongeDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.entities.CongeEntity;

public interface ICongeService {

	/**
	 * Get all Conge
	 * 
	 * @return List<CongeEntity>
	 */
	List<CongeEntity> getAllConge();

	/**
	 * add or update Conge
	 * 
	 * @param CongeEntity
	 * @return
	 */
	CongeEntity addOrUpdateConge(CongeDto conge);

	/**
	 * delete Conge
	 * 
	 * @param entity
	 */
	boolean deleteConge(Long idConge);

	/**
	 * get refresh Conge
	 * 
	 * @return
	 */
	List<CongeEntity> getRefreshedConge();

	/**
	 * 
	 * @param file
	 * @param idC
	 * @return
	 * @throws IOException
	 */

	String addOrUpdateCongesExcel(MultipartFile file, Long idC) throws IOException;

	/**
	 * genererLoader conges
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
	byte[] genererLoaderConge() throws ParseException, IOException;

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
