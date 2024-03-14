package com.benfat.pilpose.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.ClientDto;
import com.benfat.pilpose.controllers.dto.PilposeLoaderResponseDto;
import com.benfat.pilpose.entities.ClientEntity;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface IClientService {

	/**
	 * Get all Client
	 *
	 * @return List<ClientEntity>
	 */
	List<ClientEntity> getAllClient();

	/**
	 * add or update Client
	 *
	 * @param ClientEntity
	 * @return
	 */
	ClientEntity addOrUpdateClient(ClientDto conge);

	/**
	 * delete Client
	 *
	 * @param entity
	 */
	boolean deleteClient(Long idClient);

	/**
	 * get refresh Client
	 *
	 * @return
	 */
	List<ClientEntity> getRefreshedClient();

	/**
	 * genererLoader Client
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	PilposeLoaderResponseDto genererLoader() throws ParseException, IOException;

	/**
	 * Générer fichier excel Client
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	byte[] genererLoaderClient() throws ParseException, IOException;

	/**
	 * Générer fichier csv Client
	 *
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 */
	byte[] genererLoaderCsv() throws ParseException, IOException;

}
