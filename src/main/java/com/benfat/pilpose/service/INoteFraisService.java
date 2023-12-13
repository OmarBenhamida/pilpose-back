package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.controllers.dto.NoteFraisDto;
import com.benfat.pilpose.entities.NoteFraisEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
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

}
