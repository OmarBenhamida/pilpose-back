package com.benfat.pilpose.controllers.dto;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.benfat.pilpose.entities.NoteFraisEntity;
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
public class NoteFraisDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idNoteFrais;

	private String reference;

	private String typeNote;

	private String dateNote;

	private String nomCompletEmploye;

	private String statut;

	@Nullable
	private MultipartFile recu;

	private CollaborateurDto idCollaborateur;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getIdNoteFrais() {
		return idNoteFrais;
	}


	public void setIdNoteFrais(Long idNoteFrais) {
		this.idNoteFrais = idNoteFrais;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getTypeNote() {
		return typeNote;
	}


	public void setTypeNote(String typeNote) {
		this.typeNote = typeNote;
	}


	public String getDateNote() {
		return dateNote;
	}


	public void setDateNote(String dateNote) {
		this.dateNote = dateNote;
	}


	public String getNomCompletEmploye() {
		return nomCompletEmploye;
	}


	public void setNomCompletEmploye(String nomCompletEmploye) {
		this.nomCompletEmploye = nomCompletEmploye;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	public MultipartFile getRecu() {
		return recu;
	}


	public void setRecu(MultipartFile recu) {
		this.recu = recu;
	}


	public CollaborateurDto getIdCollaborateur() {
		return idCollaborateur;
	}


	public void setIdCollaborateur(CollaborateurDto idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}


	/**
	 * Convert NoteFraisEntity -> NoteFraisDto
	 *
	 * @return NoteFraisEntity
	 * @throws ParseException
	 * @throws IOException
	 */
	public static NoteFraisDto entityToDto(NoteFraisEntity entity) throws ParseException, IOException {
		NoteFraisDto dto = null;
		if (entity != null) {
			dto = new NoteFraisDto();
			dto.setIdNoteFrais(entity.getIdNoteFrais());
			dto.setReference(entity.getReference());
			dto.setTypeNote(entity.getTypeNote());
			dto.setDateNote(entity.getDateNote());
			dto.setStatut(entity.getStatut());
			// dto.setRecu(entity.getRecu());
			dto.setIdCollaborateur(CollaborateurDto.entityToDto(entity.getIdCollaborateur()));
			if (entity.getIdCollaborateur() != null) {
				dto.setNomCompletEmploye(
						entity.getIdCollaborateur().getNom() + "  " + entity.getIdCollaborateur().getPrenom());
			}

		}
		return dto;
	}

	/**
	 * Convert NoteFraisDto -> NoteFraisEntity
	 *
	 * @param NoteFraisDto
	 * @throws IOException
	 */
	public static NoteFraisEntity dtoToEntity(NoteFraisDto dto) throws ParseException, IOException {
		NoteFraisEntity entity = null;
		if (dto != null) {
			entity = new NoteFraisEntity();

			entity.setIdNoteFrais(dto.getIdNoteFrais());
			entity.setReference(dto.getReference());
			entity.setTypeNote(dto.getTypeNote());
			entity.setDateNote(dto.getDateNote());
			entity.setStatut(dto.getStatut());
			// entity.setRecu(dto.getRecu().getBytes());

			entity.setIdCollaborateur(CollaborateurDto.dtoToEntity(dto.getIdCollaborateur()));
		}

		return entity;
	}

	/**
	 * Convert list NoteFraisDto -> list NoteFraisEntity
	 *
	 * @param List<NoteFraisDto>
	 * @throws ParseException
	 * @throws IOException
	 */
	public static List<NoteFraisEntity> dtosToEntities(List<NoteFraisDto> listDto) throws ParseException, IOException {
		List<NoteFraisEntity> list = new ArrayList<>();
		if (Functions.isNotEmpty(listDto)) {
			for (NoteFraisDto dto : listDto) {
				list.add(dtoToEntity(dto));
			}
		}
		return list;
	}

	/**
	 * Convert list NoteFraisEntity -> list NoteFraisDto
	 *
	 * @param List<NoteFraisEntity>
	 * @throws ParseException
	 * @throws IOException
	 */
	public static List<NoteFraisDto> entitiesToDtos(List<NoteFraisEntity> listEntity)
			throws ParseException, IOException {
		List<NoteFraisDto> list = new ArrayList<>();
		if (Functions.isNotEmpty(listEntity)) {
			for (NoteFraisEntity entity : listEntity) {
				list.add(entityToDto(entity));
			}
		}
		return list;
	}

}
