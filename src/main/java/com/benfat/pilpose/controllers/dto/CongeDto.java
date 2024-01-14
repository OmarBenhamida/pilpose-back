package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.CongeEntity;
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
public class CongeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idConge;

	private String reference;

	private String statut;

	private String dateDebut;

	private String dateFin;

	private String dateDepot;

	private String nomCompletEmploye;

	private String heureDebut;

	private String heureFin;

	private String typeConge;

	private CollaborateurDto idCollaborateur;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Convert CongeEntity -> CongeDto
	 * 
	 * @return CongeEntity
	 * @throws ParseException
	 */
	public static CongeDto entityToDto(CongeEntity entity) throws ParseException {
		CongeDto dto = null;
		if (entity != null) {
			dto = new CongeDto();
			dto.setIdConge(entity.getIdConge());
			dto.setReference(entity.getReference());
			dto.setStatut(entity.getStatut());
			dto.setDateDebut(entity.getDateDebut());
			dto.setDateFin(entity.getDateFin());
			dto.setDateDepot(entity.getDateDepot());
			dto.setHeureDebut(entity.getHeureDebut());
			dto.setHeureFin(entity.getHeureFin());
			dto.setTypeConge(entity.getTypeConge());
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(entity.getIdCollaborateur()));

			if (entity.getIdCollaborateur() != null) {
				dto.setNomCompletEmploye(
						entity.getIdCollaborateur().getNom() + "  " + entity.getIdCollaborateur().getPrenom());
			}

		}
		return dto;
	}

	/**
	 * Convert CongeDto -> CongeEntity
	 * 
	 * @param CongeDto
	 */
	public static CongeEntity dtoToEntity(CongeDto dto) throws ParseException {
		CongeEntity entity = null;
		if (dto != null) {
			entity = new CongeEntity();

			entity.setIdConge(dto.getIdConge());
			entity.setReference(dto.getReference());
			entity.setStatut(dto.getStatut());
			entity.setDateDebut(dto.getDateDebut());
			entity.setDateFin(dto.getDateFin());
			entity.setDateDepot(dto.getDateDepot());
			entity.setHeureDebut(dto.getHeureDebut());
			entity.setHeureFin(dto.getHeureFin());
			entity.setTypeConge(dto.getTypeConge());
			entity.setIdCollaborateur(CollaborateurDto.dtoToEntity(dto.getIdCollaborateur()));
		}

		return entity;
	}

	/**
	 * Convert list CongeDto -> list CongeEntity
	 * 
	 * @param List<CongeDto>
	 * @throws ParseException
	 */
	public static List<CongeEntity> dtosToEntities(List<CongeDto> listDto) throws ParseException {
		List<CongeEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (CongeDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list CongeEntity -> list CongeDto
	 * 
	 * @param List<CongeEntity>
	 * @throws ParseException
	 */
	public static List<CongeDto> entitiesToDtos(List<CongeEntity> listEntity) throws ParseException {
		List<CongeDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (CongeEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
