package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.benfat.pilpose.entities.ChantierEntity;
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
public class ChantierDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idChantier;

	@Size(max = 20)
	private String reference;

	private String nomChantier;

	private String nomCompletClient;

	private String etat;

	private String ville;

	private LocalisationDto localisationDto;

	private CollaborateurDto clientDto;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Long getIdChantier() {
		return idChantier;
	}




	public void setIdChantier(Long idChantier) {
		this.idChantier = idChantier;
	}




	public String getReference() {
		return reference;
	}




	public void setReference(String reference) {
		this.reference = reference;
	}




	public String getNomChantier() {
		return nomChantier;
	}




	public void setNomChantier(String nomChantier) {
		this.nomChantier = nomChantier;
	}




	public String getNomCompletClient() {
		return nomCompletClient;
	}




	public void setNomCompletClient(String nomCompletClient) {
		this.nomCompletClient = nomCompletClient;
	}




	public String getEtat() {
		return etat;
	}




	public void setEtat(String etat) {
		this.etat = etat;
	}




	public String getVille() {
		return ville;
	}




	public void setVille(String ville) {
		this.ville = ville;
	}




	public LocalisationDto getLocalisationDto() {
		return localisationDto;
	}




	public void setLocalisationDto(LocalisationDto localisationDto) {
		this.localisationDto = localisationDto;
	}




	public CollaborateurDto getClientDto() {
		return clientDto;
	}




	public void setClientDto(CollaborateurDto clientDto) {
		this.clientDto = clientDto;
	}




	/**
	 * Convert ChantierEntity -> ChantierDto
	 *
	 * @return ChantierEntity
	 * @throws ParseException
	 */
	public static ChantierDto entityToDto(ChantierEntity entity) throws ParseException {
		ChantierDto dto = null;
		if (entity != null) {
			dto = new ChantierDto();
			dto.setIdChantier(entity.getIdChantier());
			dto.setReference(entity.getReference());
			dto.setClientDto(CollaborateurDto.entityToDto(entity.getClient()));
			dto.setEtat(entity.getEtat());
			dto.setLocalisationDto(LocalisationDto.entityToDto(entity.getLocalisation()));
			if (entity.getLocalisation() != null) {
				dto.setVille(entity.getLocalisation().getVille() + " - " + entity.getLocalisation().getCodePostale());
			}
			if (entity.getClient() != null) {
				dto.setNomCompletClient(entity.getClient().getNom() + "  " + entity.getClient().getPrenom());
			}
			dto.setNomChantier(entity.getNomChantier());
		}
		return dto;
	}

	/**
	 * Convert ChantierDto -> ChantierEntity
	 *
	 * @param ChantierDto
	 */
	public static ChantierEntity dtoToEntity(ChantierDto dto) throws ParseException {
		ChantierEntity entity = null;
		if (dto != null) {
			entity = new ChantierEntity();

			entity.setIdChantier(dto.getIdChantier());
			entity.setReference(dto.getReference());
			entity.setClient(CollaborateurDto.dtoToEntity(dto.getClientDto()));
			entity.setEtat(dto.getEtat());
			entity.setNomChantier(dto.getNomChantier());
			entity.setLocalisation(LocalisationDto.dtoToEntity(dto.getLocalisationDto()));
		}

		return entity;
	}

	/**
	 * Convert list ChantierDto -> list ChantierEntity
	 *
	 * @param List<ChantierDto>
	 * @throws ParseException
	 */
	public static List<ChantierEntity> dtosToEntities(List<ChantierDto> listDto) throws ParseException {
		List<ChantierEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (ChantierDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list ChantierEntity -> list ChantierDto
	 *
	 * @param List<ChantierEntity>
	 * @throws ParseException
	 */
	public static List<ChantierDto> entitiesToDtos(List<ChantierEntity> listEntity) throws ParseException {
		List<ChantierDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (ChantierEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
