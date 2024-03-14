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

	private CollaborateurDto responsable;

	private String nomCompletResponsable;

	private String nomCompletChantier;

	private String nomCompletClient;

	private String ville;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getIdTache() {
		return idTache;
	}


	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getTypeTravaux() {
		return typeTravaux;
	}


	public void setTypeTravaux(String typeTravaux) {
		this.typeTravaux = typeTravaux;
	}


	public String getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}


	public String getDateFin() {
		return dateFin;
	}


	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}


	public String getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}


	public String getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}


	public String getCommantaire() {
		return commantaire;
	}


	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}


	public String getTypeTache() {
		return typeTache;
	}


	public void setTypeTache(String typeTache) {
		this.typeTache = typeTache;
	}


	public ChantierDto getIdChantier() {
		return idChantier;
	}


	public void setIdChantier(ChantierDto idChantier) {
		this.idChantier = idChantier;
	}


	public CollaborateurDto getResponsable() {
		return responsable;
	}


	public void setResponsable(CollaborateurDto responsable) {
		this.responsable = responsable;
	}


	public String getNomCompletResponsable() {
		return nomCompletResponsable;
	}


	public void setNomCompletResponsable(String nomCompletResponsable) {
		this.nomCompletResponsable = nomCompletResponsable;
	}


	public String getNomCompletChantier() {
		return nomCompletChantier;
	}


	public void setNomCompletChantier(String nomCompletChantier) {
		this.nomCompletChantier = nomCompletChantier;
	}


	public String getNomCompletClient() {
		return nomCompletClient;
	}


	public void setNomCompletClient(String nomCompletClient) {
		this.nomCompletClient = nomCompletClient;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
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
			dto.setResponsable(CollaborateurDto.entityToDto(entity.getResponsable()));
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
			entity.setResponsable(CollaborateurDto.dtoToEntity(dto.getResponsable()));
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
