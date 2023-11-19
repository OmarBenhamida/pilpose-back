package com.benfat.pilpose.enums;

/**
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public enum SeverityEnum {

	CRITIQUE("CRITIQUE"), MAJEUR("MAJEUR"), MINEUR("MINEUR");

	private final String value;

	private SeverityEnum(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
