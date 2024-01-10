package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.benfat.pilpose.entities.LocalisationEntity;
import com.benfat.pilpose.util.Functions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 15/05/2022
 * @version : 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idLocalisation;

	private String ville;

	@Size(max = 10)
	private String codePostale;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Convert LocalisationEntity -> LocalisationDto
	 * 
	 * @return LocalisationEntity
	 * @throws ParseException
	 */
	public static LocalisationDto entityToDto(LocalisationEntity entity) throws ParseException {
		LocalisationDto dto = null;
		if (entity != null) {
			dto = new LocalisationDto();
			dto.setIdLocalisation(entity.getIdLocalisation());
			dto.setVille(entity.getVille());
			dto.setCodePostale(entity.getCodePostale());

		}
		return dto;
	}

	/**
	 * Convert LocalisationDto -> LocalisationEntity
	 * 
	 * @param LocalisationDto
	 */
	public static LocalisationEntity dtoToEntity(LocalisationDto dto) throws ParseException {
		LocalisationEntity entity = null;
		if (dto != null) {
			entity = new LocalisationEntity();

			entity.setIdLocalisation(dto.getIdLocalisation());
			entity.setVille(dto.getVille());
			entity.setCodePostale(dto.getCodePostale());

		}

		return entity;
	}

	/**
	 * Convert list LocalisationDto -> list LocalisationEntity
	 * 
	 * @param List<LocalisationDto>
	 * @throws ParseException
	 */
	public static List<LocalisationEntity> dtosToEntities(List<LocalisationDto> listDto) throws ParseException {
		List<LocalisationEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (LocalisationDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list LocalisationEntity -> list LocalisationDto
	 * 
	 * @param List<LocalisationEntity>
	 * @throws ParseException
	 */
	public static List<LocalisationDto> entitiesToDtos(List<LocalisationEntity> listEntity) throws ParseException {
		List<LocalisationDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (LocalisationEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
