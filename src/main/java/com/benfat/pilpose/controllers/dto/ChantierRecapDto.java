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
public class ChantierRecapDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private ChantierDto idChantier;

	private int totalHeuresTravaille;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public ChantierDto getIdChantier() {
		return idChantier;
	}

	public void setIdChantier(ChantierDto idChantier) {
		this.idChantier = idChantier;
	}
	   
	public int getTotalHeuresTravaille() {
		return totalHeuresTravaille;
	}

	public void setTotalHeuresTravaille(int totalHeuresTravaille) {
		this.totalHeuresTravaille = totalHeuresTravaille;
	}
	   

}
