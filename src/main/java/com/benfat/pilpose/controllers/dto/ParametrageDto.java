package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.benfat.pilpose.entities.ParametrageEntity;
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
public class ParametrageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idParametrage;

	private Integer valeur;


	private String libelle;


	private String code;

	private String description;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getIdParametrage() {
		return idParametrage;
	}


	public void setIdParametrage(Long idParametrage) {
		this.idParametrage = idParametrage;
	}


	public Integer getValeur() {
		return valeur;
	}


	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * Convert ParametrageEntity -> ParametrageDto
	 *
	 * @return DeviseDto
	 * @throws ParseException
	 */
	public static ParametrageDto entityToDto(ParametrageEntity entity) throws ParseException {
		ParametrageDto dto = null;
		if (entity != null) {
			dto = new ParametrageDto();
			dto.setIdParametrage(entity.getIdParametrage());
			dto.setLibelle(entity.getLibelle());
			dto.setDescription(entity.getDescription());
			dto.setValeur(entity.getValeur());
			dto.setCode(entity.getCode());
		}
		return dto;
	}

	/**
	 * Convert ParametrageDto -> ParametrageEntity
	 *
	 * @param ParametrageDto
	 */
	public static ParametrageEntity dtoToEntity(ParametrageDto dto) throws ParseException {
		ParametrageEntity entity = null;
		if (dto != null) {
			entity = new ParametrageEntity();

			entity.setIdParametrage(dto.getIdParametrage());
			entity.setLibelle(dto.getLibelle());
			entity.setDescription(dto.getDescription());
			entity.setValeur(dto.getValeur());
			entity.setCode(dto.getCode());

		}

		return entity;
	}

	/**
	 * Convert list SiteDto -> list SiteEntity
	 *
	 * @param List<DeviseDto>
	 * @throws ParseException
	 */
	public static List<ParametrageEntity> dtosToEntities(List<ParametrageDto> listDto) throws ParseException {
		List<ParametrageEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (ParametrageDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list SiteEntity -> list SiteDto
	 *
	 * @param List<SiteEntity>
	 * @throws ParseException
	 */
	public static List<ParametrageDto> entitiesToDtos(List<ParametrageEntity> listEntity) throws ParseException {
		List<ParametrageDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (ParametrageEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
