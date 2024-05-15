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
public class FeuilleTempsCheckDto implements Serializable {

	private static final long serialVersionUID = 1L;

	 private boolean lundi = false;
	    private boolean mardi = false;
	    private boolean mercredi = false;
	    private boolean jeudi = false;
	    private boolean vendredi = false;




}
