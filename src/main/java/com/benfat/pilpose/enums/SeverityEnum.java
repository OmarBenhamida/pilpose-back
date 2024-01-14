package com.benfat.pilpose.enums;

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
