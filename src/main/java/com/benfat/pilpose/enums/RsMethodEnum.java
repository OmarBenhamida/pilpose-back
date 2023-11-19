package com.benfat.pilpose.enums;

/**
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public enum RsMethodEnum {
	GET("GET"), POST("POST"), PUT("PUT"), PATCH("PATCH"), DELETE("DELETE");

	private final String value;

	private RsMethodEnum(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
