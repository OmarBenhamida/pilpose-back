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

	private boolean indemnite;


	private boolean validationChefEquipe;

	private boolean validationResponsableTravaux;

	private boolean validationGerant;

	private boolean validationResponsableAdministratif;



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Long getIdFeuilleTemps() {
		return idFeuilleTemps;
	}



	public void setIdFeuilleTemps(Long idFeuilleTemps) {
		this.idFeuilleTemps = idFeuilleTemps;
	}



	public String getReference() {
		return reference;
	}



	public void setReference(String reference) {
		this.reference = reference;
	}



	public String getTypeTravaux() {
		return typeTravaux;
	}



	public void setTypeTravaux(String typeTravaux) {
		this.typeTravaux = typeTravaux;
	}



	public String getJourSemaine() {
		return jourSemaine;
	}



	public void setJourSemaine(String jourSemaine) {
		this.jourSemaine = jourSemaine;
	}



	public int getHeureTravaille() {
		return heureTravaille;
	}



	public void setHeureTravaille(int heureTravaille) {
		this.heureTravaille = heureTravaille;
	}



	public String getVehicule() {
		return vehicule;
	}



	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}



	public String getVehiculeSuite() {
		return vehiculeSuite;
	}



	public void setVehiculeSuite(String vehiculeSuite) {
		this.vehiculeSuite = vehiculeSuite;
	}



	public int getKm() {
		return km;
	}



	public void setKm(int km) {
		this.km = km;
	}



	public String getCommantaire() {
		return commantaire;
	}



	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}



	public ChantierDto getIdChantier() {
		return idChantier;
	}



	public void setIdChantier(ChantierDto idChantier) {
		this.idChantier = idChantier;
	}



	public CollaborateurDto getIdCollaborateur() {
		return idCollaborateur;
	}



	public void setIdCollaborateur(CollaborateurDto idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}



	public CollaborateurDto getResponsable() {
		return responsable;
	}



	public void setResponsable(CollaborateurDto responsable) {
		this.responsable = responsable;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public String getNomCompletResponsable() {
		return nomCompletResponsable;
	}



	public void setNomCompletResponsable(String nomCompletResponsable) {
		this.nomCompletResponsable = nomCompletResponsable;
	}



	public String getNomCompletSalarie() {
		return nomCompletSalarie;
	}



	public void setNomCompletSalarie(String nomCompletSalarie) {
		this.nomCompletSalarie = nomCompletSalarie;
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



	public String getMetier() {
		return metier;
	}



	public void setMetier(String metier) {
		this.metier = metier;
	}



	public boolean isIndemnite() {
		return indemnite;
	}



	public void setIndemnite(boolean indemnite) {
		this.indemnite = indemnite;
	}



	public boolean isValidationChefEquipe() {
		return validationChefEquipe;
	}



	public void setValidationChefEquipe(boolean validationChefEquipe) {
		this.validationChefEquipe = validationChefEquipe;
	}



	public boolean isValidationResponsableTravaux() {
		return validationResponsableTravaux;
	}



	public void setValidationResponsableTravaux(boolean validationResponsableTravaux) {
		this.validationResponsableTravaux = validationResponsableTravaux;
	}



	public boolean isValidationGerant() {
		return validationGerant;
	}



	public void setValidationGerant(boolean validationGerant) {
		this.validationGerant = validationGerant;
	}



	public boolean isValidationResponsableAdministratif() {
		return validationResponsableAdministratif;
	}



	public void setValidationResponsableAdministratif(boolean validationResponsableAdministratif) {
		this.validationResponsableAdministratif = validationResponsableAdministratif;
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
			dto.setIndemnite(entity.isIndemnite());
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

			dto.setValidationChefEquipe(entity.isValidationChefEquipe());
			dto.setValidationGerant(entity.isValidationGerant());
			dto.setValidationResponsableTravaux(entity.isValidationResponsableTravaux());
			dto.setValidationResponsableAdministratif(entity.isValidationResponsableAdministratif());

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
			entity.setIndemnite(dto.isIndemnite());
			entity.setValidationChefEquipe(dto.isValidationChefEquipe());
			entity.setValidationGerant(dto.isValidationGerant());
			entity.setValidationResponsableTravaux(dto.isValidationResponsableTravaux());
			entity.setValidationResponsableAdministratif(dto.isValidationResponsableAdministratif());
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
