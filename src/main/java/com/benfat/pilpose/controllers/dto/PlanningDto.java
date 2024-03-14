package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;

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
public class PlanningDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private CollaborateurDto idCollaborateur;

	private TacheDto idTache;

	public CollaborateurDto getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurDto idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}

	public TacheDto getIdTache() {
		return idTache;
	}

	public void setIdTache(TacheDto idTache) {
		this.idTache = idTache;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
