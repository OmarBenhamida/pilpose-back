package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.controllers.dto.NoteFraisDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.entities.NoteFraisEntity;


public interface INoteFraisService {

	/**
	 * Get all NoteFrais
	 *
	 *
	 * @return List<NoteFraisEntity>
	 */
	List<NoteFraisEntity> getAllNoteFrais();

	/**
	 * add or update NoteFrais
	 *
	 * @param NoteFraisEntity
	 * @return
	 */
	NoteFraisEntity addOrUpdateNoteFrais(NoteFraisDto noteFrais);

	/**
	 * delete NoteFrais
	 *
	 * @param entity
	 */
	boolean deleteNoteFrais(Long idNoteFrais);

	/**
	 * get refresh NoteFrais
	 *
	 * @return
	 */
	List<NoteFraisEntity> getRefreshedNoteFrais();

	/**
	 * genererLoader NoteFrais
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
	byte[] genererLoaderNoteFrais() throws ParseException, IOException;

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

	NoteFraisEntity addNoteWithRecu(NoteFraisEntity fileEntity,MultipartFile file) throws IOException;

}
