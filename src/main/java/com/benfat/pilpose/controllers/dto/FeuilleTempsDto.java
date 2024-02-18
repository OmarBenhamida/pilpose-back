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

	private int heureTravaille;

	private String vehicule;
	
	private String vehiculeSuite;

	private int km;

	private String commantaire;

	private ChantierDto idChantier;

	private CollaborateurDto idCollaborateur;

	private CollaborateurDto responsable;

	private String statut;

	private String nomCompletResponsable;

	private String nomCompletSalarie;

	private String nomCompletChantier;

	private String nomCompletClient;

	private String ville;
	
	private String metier;
	
	private String indemnite;

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
            dto.setMetier(entity.getMetier());
			dto.setVehicule(entity.getVehicule());
			dto.setVehiculeSuite(entity.getVehiculeSuite());
			dto.setKm(entity.getKm());
			dto.setIndemnite(entity.getIndemnite());
			dto.setCommantaire(entity.getCommantaire());
			dto.setHeureTravaille(entity.getHeureTravaille());
			dto.setIdChantier(ChantierDto.entityToDto(entity.getIdChantier()));
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(entity.getIdCollaborateur()));
			dto.setResponsable(CollaborateurDto.entityToDto(entity.getResponsable()));
			dto.setStatut(entity.getStatut());
			if (entity.getResponsable() != null) {
				dto.setNomCompletResponsable(entity.getResponsable().getNom() + "  " + entity.getResponsable().getPrenom());
			}
			if (entity.getIdCollaborateur() != null) {
				dto.setNomCompletSalarie(entity.getIdCollaborateur().getNom() + "  " + entity.getIdCollaborateur().getPrenom());
			}
			if (entity.getIdChantier() != null) {
				dto.setNomCompletChantier(entity.getIdChantier().getNomChantier() + "  " + entity.getIdChantier().getReference());
				}
			if (entity.getIdChantier().getClient() != null) {
					dto.setNomCompletClient(entity.getIdChantier().getClient().getPrenom() + "  "+ entity.getIdChantier().getClient().getNom());
				}
			if (entity.getIdChantier().getLocalisation() != null) {
					dto.setVille(entity.getIdChantier().getLocalisation().getVille() + "-"+ entity.getIdChantier().getLocalisation().getCodePostale());
				}
				
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
            entity.setMetier(dto.getMetier());
			entity.setVehicule(dto.getVehicule());
			entity.setVehiculeSuite(dto.getVehiculeSuite());
			entity.setKm(dto.getKm());
			entity.setCommantaire(dto.getCommantaire());
			entity.setHeureTravaille(dto.getHeureTravaille());
			entity.setIdChantier(ChantierDto.dtoToEntity(dto.getIdChantier()));
			entity.setIdCollaborateur(CollaborateurDto.dtoToEntity(dto.getIdCollaborateur()));
			entity.setResponsable(CollaborateurDto.dtoToEntity(dto.getResponsable()));
			entity.setStatut(dto.getStatut());
			entity.setIndemnite(dto.getIndemnite());
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
