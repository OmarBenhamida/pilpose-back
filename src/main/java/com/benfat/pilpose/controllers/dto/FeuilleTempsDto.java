package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.FeuilleTempsEntity;
import com.benfat.pilpose.util.Functions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeuilleTempsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idFeuilleTemps;

	private String reference;

	private String typeTravaux;

	private String jourSemaine;

	private byte[] panier;

	private int heureTravaille;

	private String vehicule;

	private ChantierDto idChantier;

	private CollaborateurDto idCollaborateur;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @return FeuilleTempsEntity
	 * @throws ParseException
	 */
	public static FeuilleTempsDto entityToDto(FeuilleTempsEntity entity) throws ParseException {
		FeuilleTempsDto dto = null;
		if (entity != null) {
			dto = new FeuilleTempsDto();
			dto.setIdFeuilleTemps(entity.getIdFeuilleTemps());
			dto.setReference(entity.getReference());
			dto.setTypeTravaux(entity.getTypeTravaux());
			dto.setJourSemaine(entity.getJourSemaine());
			//dto.setPanier(entity.getPanier());
			dto.setVehicule(entity.getVehicule());
			dto.setHeureTravaille(entity.getHeureTravaille());
			dto.setIdChantier(ChantierDto.entityToDto(entity.getIdChantier()));
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(entity.getIdCollaborateur()));

		}
		return dto;
	}

	/**
	 * Convert FeuilleTempsDto -> FeuilleTempsEntity
	 * 
	 * @param FeuilleTempsDto
	 */
	public static FeuilleTempsEntity dtoToEntity(FeuilleTempsDto dto) throws ParseException {
		FeuilleTempsEntity entity = null;
		if (dto != null) {
			entity = new FeuilleTempsEntity();

			entity.setIdFeuilleTemps(dto.getIdFeuilleTemps());
			entity.setReference(dto.getReference());
			entity.setTypeTravaux(dto.getTypeTravaux());
			entity.setJourSemaine(dto.getJourSemaine());
			//entity.setPanier(dto.getPanier());
			entity.setVehicule(dto.getVehicule());
			entity.setHeureTravaille(dto.getHeureTravaille());
			entity.setIdChantier(ChantierDto.dtoToEntity(dto.getIdChantier()));
			entity.setIdCollaborateur(CollaborateurDto.dtoToEntity(dto.getIdCollaborateur()));

		}

		return entity;
	}

	/**
	 * Convert list FeuilleTempsDto -> list FeuilleTempsEntity
	 * 
	 * @param List<FeuilleTempsDto>
	 * @throws ParseException
	 */
	public static List<FeuilleTempsEntity> dtosToEntities(List<FeuilleTempsDto> listDto) throws ParseException {
		List<FeuilleTempsEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (FeuilleTempsDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list FeuilleTempsEntity -> list FeuilleTempsDto
	 * 
	 * @param List<FeuilleTempsEntity>
	 * @throws ParseException
	 */
	public static List<FeuilleTempsDto> entitiesToDtos(List<FeuilleTempsEntity> listEntity) throws ParseException {
		List<FeuilleTempsDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (FeuilleTempsEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
