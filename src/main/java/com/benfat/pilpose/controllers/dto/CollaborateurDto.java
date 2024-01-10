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
	private String numSecuSocial;
	private String adresse;
	private String telephone;
	private String username;
	private String role;

	public static long getSerialversionuid() {
		return serialVersionUID;
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
			if (dto.getDateEmbauche() != null)
				entity.setDateEmbauche(dto.getDateEmbauche());
			entity.setDateNaissance(dto.getDateNaissance());
			entity.setEmail(dto.getEmail());
			entity.setFonction(dto.getFonction());
			entity.setUsername(dto.getUsername());
			entity.setPassword(dto.getPassword());
			entity.setRole(dto.getRole());
			if (dto.getAdresse() != null)
				entity.setAdresse(dto.getAdresse());
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
