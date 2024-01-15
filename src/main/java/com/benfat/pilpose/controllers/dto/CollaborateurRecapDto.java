package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter

@NoArgsConstructor
public class CollaborateurRecapDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private CollaborateurDto idCollaborateur;

	private int totalHeuresTravaille;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public CollaborateurDto getIdCollaborateur() {
		return idCollaborateur;
	}

	public void setIdCollaborateur(CollaborateurDto idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}
	   
	public int getTotalHeuresTravaille() {
		return totalHeuresTravaille;
	}

	public void setTotalHeuresTravaille(int totalHeuresTravaille) {
		this.totalHeuresTravaille = totalHeuresTravaille;
	}
	   

}
