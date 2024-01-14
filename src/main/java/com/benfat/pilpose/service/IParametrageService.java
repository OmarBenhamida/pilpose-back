package com.benfat.pilpose.service;

import java.text.ParseException;
import java.util.List;

import com.benfat.pilpose.controllers.dto.ParametrageDto;
import com.benfat.pilpose.entities.ParametrageEntity;

public interface IParametrageService {
	/**
	 * modifier parametrage
	 *
	 * @param ParametrageDto
	 * @return
	 * @throws SofAppsTechnicalException
	 */
	List<ParametrageEntity> modifierParametrage(List<ParametrageDto> parametrageDtos) throws ParseException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	ParametrageEntity getById(Long id) throws ParseException;

	/**
	 * get All Parametrage
	 * 
	 * @return
	 * @throws ParseException
	 */
	List<ParametrageDto> getAllParametrage() throws ParseException;

	/**
	 * Recuperer la durée d'expiration mot de passe
	 * 
	 * @param code
	 * @return
	 */
	ParametrageEntity getDureeMdpByCode(String code);

}
