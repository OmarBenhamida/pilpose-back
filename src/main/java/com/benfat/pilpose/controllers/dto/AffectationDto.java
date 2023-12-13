package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.AffectationEntity;
import com.benfat.pilpose.util.Functions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : BENHAMIDA OMAR SOFRECOM
 * @see : <omar.benhamida@sofrecom.com>
 * @creation : 01/12/2023
 * @version : 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AffectationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAffectation;

	private CollaborateurDto idCollaborateur;

	private TacheDto idTache;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Convert AffectationEntity -> AffectationDto
	 * 
	 * @return AffectationEntity
	 * @throws ParseException
	 */
	public static AffectationDto entityToDto(AffectationEntity entity) throws ParseException {
		AffectationDto dto = null;
		if (entity != null) {
			dto = new AffectationDto();
			dto.setIdAffectation(entity.getIdAffectation());
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(entity.getIdCollaborateur()));
			dto.setIdTache(TacheDto.entityToDto(entity.getIdTache()));

		}
		return dto;
	}

	/**
	 * Convert AffectationDto -> AffectationEntity
	 * 
	 * @param AffectationDto
	 */
	public static AffectationEntity dtoToEntity(AffectationDto dto) throws ParseException {
		AffectationEntity entity = null;
		if (dto != null) {
			entity = new AffectationEntity();

			entity.setIdAffectation(dto.getIdAffectation());
			entity.setIdCollaborateur(CollaborateurDto.dtoToEntity(dto.getIdCollaborateur()));
			entity.setIdTache(TacheDto.dtoToEntity(dto.getIdTache()));
		}

		return entity;
	}

	/**
	 * Convert list AffectationDto -> list AffectationEntity
	 * 
	 * @param List<AffectationDto>
	 * @throws ParseException
	 */
	public static List<AffectationEntity> dtosToEntities(List<AffectationDto> listDto) throws ParseException {
		List<AffectationEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (AffectationDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list AffectationEntity -> list AffectationDto
	 * 
	 * @param List<AffectationEntity>
	 * @throws ParseException
	 */
	public static List<AffectationDto> entitiesToDtos(List<AffectationEntity> listEntity) throws ParseException {
		List<AffectationDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (AffectationEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
