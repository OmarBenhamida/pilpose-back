package com.benfat.pilpose.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.controllers.dto.CongeDto;
import com.benfat.pilpose.entities.CongeEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
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
	
	String addOrUpdateCongesExcel(MultipartFile file,Long idC) throws IOException;

}
