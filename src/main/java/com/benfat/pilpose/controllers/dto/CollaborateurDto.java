package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.CollaborateurEntity;
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
public class CollaborateurDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idCollaborateur;
	private String nom;
	private String prenom;
	private String dateEmbauche;
	private String dateCreation;
	private String email;
	private String fonction;
	private String password;
	private String dateNaissance;
	private String adresse;
	private String telephone;
	private String username;
	private String role;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getIdCollaborateur() {
		return idCollaborateur;
	}


	public void setIdCollaborateur(Long idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDateEmbauche() {
		return dateEmbauche;
	}


	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}


	public String getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFonction() {
		return fonction;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * Convert CollaborateurEntity -> CollaborateurDto
	 *
	 * @return CollaborateurEntity
	 * @throws ParseException
	 */
	public static CollaborateurDto entityToDto(CollaborateurEntity entity) throws ParseException {
		CollaborateurDto dto = null;
		if (entity != null) {
			dto = new CollaborateurDto();
			dto.setIdCollaborateur(entity.getIdCollaborateur());
			dto.setNom(entity.getNom());
			dto.setPrenom(entity.getPrenom());
			dto.setDateEmbauche(entity.getDateEmbauche());
			dto.setDateNaissance(entity.getDateNaissance());
			dto.setDateCreation(entity.getDateCreation());
			dto.setEmail(entity.getEmail());
			dto.setFonction(entity.getFonction());
			dto.setUsername(entity.getUsername());
			dto.setPassword(entity.getPassword());
			dto.setRole(entity.getRole());
			dto.setAdresse(entity.getAdresse());
			dto.setTelephone(entity.getTelephone());

		}
		return dto;
	}

	/**
	 * Convert CollaborateurDto -> CollaborateurEntity
	 *
	 * @param CollaborateurDto
	 */
	public static CollaborateurEntity dtoToEntity(CollaborateurDto dto) throws ParseException {
		CollaborateurEntity entity = null;
		if (dto != null) {
			entity = new CollaborateurEntity();
			entity.setIdCollaborateur(dto.getIdCollaborateur());
			entity.setNom(dto.getNom());
			entity.setPrenom(dto.getPrenom());
			if (dto.getDateEmbauche() != null) {
				entity.setDateEmbauche(dto.getDateEmbauche());
			}
			entity.setDateNaissance(dto.getDateNaissance());
			entity.setEmail(dto.getEmail());
			entity.setFonction(dto.getFonction());
			entity.setUsername(dto.getUsername());
			entity.setPassword(dto.getPassword());
			entity.setRole(dto.getRole());
			if (dto.getAdresse() != null) {
				entity.setAdresse(dto.getAdresse());
			}
			entity.setTelephone(dto.getTelephone());
			entity.setDateCreation(dto.getDateCreation());

		}

		return entity;
	}

	/**
	 * Convert list CollaborateurDto -> list CollaborateurEntity
	 *
	 * @param List<CollaborateurDto>
	 * @throws ParseException
	 */
	public static List<CollaborateurEntity> dtosToEntities(List<CollaborateurDto> listDto) throws ParseException {
		List<CollaborateurEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (CollaborateurDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list CollaborateurEntity -> list CollaborateurDto
	 *
	 * @param List<CollaborateurEntity>
	 * @throws ParseException
	 */
	public static List<CollaborateurDto> entitiesToDtos(List<CollaborateurEntity> listEntity) throws ParseException {
		List<CollaborateurDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (CollaborateurEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
