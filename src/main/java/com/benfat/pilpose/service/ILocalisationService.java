package com.benfat.pilpose.service;

import java.util.List;

import com.benfat.pilpose.entities.LocalisationEntity;

public interface ILocalisationService {

	/**
	 * Get all villes
	 * 
	 * 
	 * @return List<LocalisationEntity>
	 */
	List<LocalisationEntity> getAllLocalisation();

}
