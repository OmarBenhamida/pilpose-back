package com.benfat.pilpose.exception;

public class ResponseMsg {
	private int code;
	private String message;
	private String description;
	private String infoURL;

	/**
	 * Constructor using field
	 * 
	 * @param code
	 * @param description
	 * @param message
	 */
	public ResponseMsg(int code, String description, String message) {
		super();
		this.code = code;
		this.description = description;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the infoURL
	 */
	public String getInfoURL() {
		return infoURL;
	}

	/**
	 * @param infoURL the infoURL to set
	 */
	public void setInfoURL(String infoURL) {
		this.infoURL = infoURL;
	}
}
