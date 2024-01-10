package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.entities.LocalisationEntity;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 05/04/2022
 * @version : 1.0
 */
public interface ILocalisationService {

	/**
	 * Get all villes
	 * 
	 * 
	 * @return List<LocalisationEntity>
	 */
	List<LocalisationEntity> getAllLocalisation();

}
