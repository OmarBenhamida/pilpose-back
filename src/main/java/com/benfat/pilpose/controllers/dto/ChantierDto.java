package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.benfat.pilpose.entities.ChantierEntity;
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
public class ChantierDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idChantier;

	@Size(max = 20)
	private String reference;

	@Size(max = 20)
	private String client;

	private String etat;

	private String localisationDto;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * Convert ChantierEntity -> ChantierDto
	 * 
	 * @return ChantierEntity
	 * @throws ParseException
	 */
	public static ChantierDto entityToDto(ChantierEntity entity) throws ParseException {
		ChantierDto dto = null;
		if (entity != null) {
			dto = new ChantierDto();
			dto.setIdChantier(entity.getIdChantier());
			dto.setReference(entity.getReference());
			dto.setClient(entity.getClient());
			dto.setEtat(entity.getEtat());
			dto.setLocalisationDto(entity.getLocalisation());
			
		}
		return dto;
	}
 
	/**
	 * Convert ChantierDto -> ChantierEntity
	 * 
	 * @param ChantierDto
	 */
	public static ChantierEntity dtoToEntity(ChantierDto dto) throws ParseException {
		ChantierEntity entity = null;
		if (dto != null) {
			entity = new ChantierEntity();

			entity.setIdChantier(dto.getIdChantier());
			entity.setReference(dto.getReference());
			entity.setClient(dto.getClient());
			entity.setEtat(dto.getEtat());
			entity.setLocalisation(dto.getLocalisationDto());		
		}

		return entity;
	}

	/**
	 * Convert list ChantierDto -> list ChantierEntity
	 * 
	 * @param List<ChantierDto>
	 * @throws ParseException
	 */
	public static List<ChantierEntity> dtosToEntities(List<ChantierDto> listDto) throws ParseException {
		List<ChantierEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (ChantierDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list ChantierEntity -> list ChantierDto
	 * 
	 * @param List<ChantierEntity>
	 * @throws ParseException
	 */
	public static List<ChantierDto> entitiesToDtos(List<ChantierEntity> listEntity) throws ParseException {
		List<ChantierDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (ChantierEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
