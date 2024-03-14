package com.benfat.pilpose.enums;

public enum OrigineEnum {

	/** */
	PILPOSE_AUTH("PILPOSE_AUTH");

	private final String value;

	private OrigineEnum(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
