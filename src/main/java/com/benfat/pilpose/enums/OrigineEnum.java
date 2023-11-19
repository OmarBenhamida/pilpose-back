package com.benfat.pilpose.enums;

/**
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
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
