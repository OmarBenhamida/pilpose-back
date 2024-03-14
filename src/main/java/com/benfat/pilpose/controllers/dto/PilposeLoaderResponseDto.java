package com.benfat.pilpose.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PilposeLoaderResponseDto {

	private byte[] pilposeXsl;
	private byte[] pilposeCsv;
	public byte[] getPilposeXsl() {
		return pilposeXsl;
	}
	public void setPilposeXsl(byte[] pilposeXsl) {
		this.pilposeXsl = pilposeXsl;
	}
	public byte[] getPilposeCsv() {
		return pilposeCsv;
	}
	public void setPilposeCsv(byte[] pilposeCsv) {
		this.pilposeCsv = pilposeCsv;
	}


}