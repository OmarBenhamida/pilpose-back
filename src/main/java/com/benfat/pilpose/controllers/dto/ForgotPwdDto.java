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
public class ForgotPwdDto implements Serializable {

	private static final long serialVersionUID = 1L;

private String mail;

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}



}
