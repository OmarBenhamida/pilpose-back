package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.benfat.pilpose.entities.TacheEntity;
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
public class TacheDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTache;

	private String reference;

	private String libelle;

	private String dateDebut;

	private String dateFin;

	private int heureDebut;

	private int heureFin;

	private String commantaire;

	private ChantierDto idChantier;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @return TacheEntity
	 * @throws ParseException
	 */
	public static TacheDto entityToDto(TacheEntity entity) throws ParseException {
		TacheDto dto = null;
		if (entity != null) {
			dto = new TacheDto();
			dto.setIdTache(entity.getIdTache());
			dto.setReference(entity.getReference());
			dto.setLibelle(entity.getLibelle());
			dto.setDateDebut(entity.getDateDebut());
			dto.setDateFin(entity.getDateFin());
			dto.setHeureDebut(entity.getHeureDebut());
			dto.setHeureFin(entity.getHeureFin());
			dto.setCommantaire(entity.getCommantaire());
			dto.setIdChantier(ChantierDto.entityToDto(entity.getIdChantier()));

		}
		return dto;
	}

	/**
	 * Convert TacheDto -> TacheEntity
	 * 
	 * @param TacheDto
	 */
	public static TacheEntity dtoToEntity(TacheDto dto) throws ParseException {
		TacheEntity entity = null;
		if (dto != null) {
			entity = new TacheEntity();

			entity.setIdTache(dto.getIdTache());
			entity.setReference(dto.getReference());
			entity.setLibelle(dto.getLibelle());
			entity.setDateDebut(dto.getDateDebut());
			entity.setDateFin(dto.getDateFin());
			entity.setHeureDebut(dto.getHeureDebut());
			entity.setHeureFin(dto.getHeureFin());
			entity.setCommantaire(dto.getCommantaire());
			entity.setIdChantier(ChantierDto.dtoToEntity(dto.getIdChantier()));

		}

		return entity;
	}

	/**
	 * Convert list TacheDto -> list TacheEntity
	 * 
	 * @param List<TacheDto>
	 * @throws ParseException
	 */
	public static List<TacheEntity> dtosToEntities(List<TacheDto> listDto) throws ParseException {
		List<TacheEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (TacheDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list TacheEntity -> list TacheDto
	 * 
	 * @param List<TacheEntity>
	 * @throws ParseException
	 */
	public static List<TacheDto> entitiesToDtos(List<TacheEntity> listEntity) throws ParseException {
		List<TacheDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (TacheEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
