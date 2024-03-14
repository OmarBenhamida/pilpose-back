package com.benfat.pilpose.controllers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter

@NoArgsConstructor
public class UpdateAffectationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private TacheDto tache;

	private List<Long> listIdsCollab;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TacheDto getTache() {
		return tache;
	}

	public void setTache(TacheDto tache) {
		this.tache = tache;
	}

	public List<Long> getListIdsCollab() {
		return listIdsCollab;
	}

	public void setListIdsCollab(List<Long> listIdsCollab) {
		this.listIdsCollab = listIdsCollab;
	}

}
