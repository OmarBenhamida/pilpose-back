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
public class PlanningDto implements Serializable  {
	
	private CollaborateurDto idCollaborateur;
	
	private TacheDto idTache;

}
