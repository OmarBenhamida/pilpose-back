package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.ClientEntity;
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
public class ClientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idClient;

	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getIdClient() {
		return idClient;
	}


	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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


	/**
	 * Convert ClientEntity -> ClientDto
	 *
	 * @return ClientEntity
	 * @throws ParseException
	 */
	public static ClientDto entityToDto(ClientEntity entity) throws ParseException {
		ClientDto dto = null;
		if (entity != null) {
			dto = new ClientDto();

			dto.setIdClient(entity.getIdClient());
			dto.setNom(entity.getNom());
			dto.setPrenom(entity.getPrenom());
			dto.setTelephone(entity.getTelephone());
			dto.setAdresse(entity.getAdresse());

		}
		return dto;
	}

	/**
	 * Convert ClientDto -> ClientEntity
	 *
	 * @param ClientDto
	 */
	public static ClientEntity dtoToEntity(ClientDto dto) throws ParseException {
		ClientEntity entity = null;
		if (dto != null) {
			entity = new ClientEntity();

			entity.setIdClient(dto.getIdClient());
			entity.setNom(dto.getNom());
			entity.setPrenom(dto.getPrenom());
			entity.setTelephone(dto.getTelephone());
			entity.setAdresse(dto.getAdresse());
		}

		return entity;
	}

	/**
	 * Convert list ClientDto -> list ClientEntity
	 *
	 * @param List<ClientDto>
	 * @throws ParseException
	 */
	public static List<ClientEntity> dtosToEntities(List<ClientDto> listDto) throws ParseException {
		List<ClientEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (ClientDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list ClientEntity -> list ClientDto
	 *
	 * @param List<ClientEntity>
	 * @throws ParseException
	 */
	public static List<ClientDto> entitiesToDtos(List<ClientEntity> listEntity) throws ParseException {
		List<ClientDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (ClientEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
