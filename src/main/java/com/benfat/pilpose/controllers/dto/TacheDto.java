package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.TacheEntity;
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
public class TacheDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTache;

	private String libelle;
	
	private String typeTravaux;

	private String dateDebut;

	private String dateFin;

	private String heureDebut;

	private String heureFin;

	private String commantaire;
	
	private String typeTache;

	private ChantierDto idChantier;

	private CollaborateurPlannigDto responsable;

	private String nomCompletResponsable;

	private String nomCompletChantier;

	private String nomCompletClient;

	private String ville;

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
			dto.setTypeTravaux(entity.getTypeTravaux());
			dto.setLibelle(entity.getLibelle());
			dto.setDateDebut(entity.getDateDebut());
			dto.setDateFin(entity.getDateFin());
			dto.setHeureDebut(entity.getHeureDebut());
			dto.setHeureFin(entity.getHeureFin());
			dto.setCommantaire(entity.getCommantaire());
			dto.setTypeTache(entity.getTypeTache());
			dto.setIdChantier(ChantierDto.entityToDto(entity.getIdChantier()));
			dto.setResponsable(CollaborateurPlannigDto.entityToDto(entity.getResponsable()));
			if (entity.getResponsable() != null) {
				dto.setNomCompletResponsable(
						entity.getResponsable().getNom() + "  " + entity.getResponsable().getPrenom());
			}
			if (entity.getIdChantier() != null) {
				dto.setNomCompletChantier(
						entity.getIdChantier().getNomChantier() + "  " + entity.getIdChantier().getReference());
				if (entity.getIdChantier().getClient() != null) {
					dto.setNomCompletClient(entity.getIdChantier().getClient().getPrenom() + "  "
							+ entity.getIdChantier().getClient().getNom());
				}
				if (entity.getIdChantier().getLocalisation() != null) {

					dto.setVille(entity.getIdChantier().getLocalisation().getVille() + "-"
							+ entity.getIdChantier().getLocalisation().getCodePostale());
				}
			}

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
			entity.setTypeTravaux(dto.getTypeTravaux());
			entity.setLibelle(dto.getLibelle());
			entity.setDateDebut(dto.getDateDebut());
			entity.setDateFin(dto.getDateFin());
			entity.setHeureDebut(dto.getHeureDebut());
			entity.setHeureFin(dto.getHeureFin());
			entity.setCommantaire(dto.getCommantaire());
			entity.setTypeTache(dto.getTypeTache());
			entity.setIdChantier(ChantierDto.dtoToEntity(dto.getIdChantier()));
			entity.setResponsable(CollaborateurPlannigDto.dtoToEntity(dto.getResponsable()));
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
