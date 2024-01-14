package com.benfat.pilpose.exception;

import org.springframework.http.HttpStatus;

public class PilposeBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public enum Severity {
		ERROR, MESSAGE, WARNING
	}

	private HttpStatus httpResponseStatus;
	private String code;
	private String libelle;
	private Severity severity;
	private Class<?> Origine;
	private String errorMessage;

	/**
	 * Default constructor
	 */
	public PilposeBusinessException() {
		super();
	}

	/**
	 * 
	 * @param e
	 */
	public PilposeBusinessException(Exception e) {
		super(e);
	}

	/**
	 * Constructor using <libelle>
	 * 
	 * @param libelle
	 */
	public PilposeBusinessException(String libelle) {
		super(libelle);
		this.libelle = libelle;
	}

	/**
	 * Constructor using <code> && <libelle>
	 * 
	 * @param code
	 * @param libelle
	 */
	public PilposeBusinessException(String code, String libelle) {
		super(libelle);
		this.code = code;
		this.libelle = libelle;
		this.errorMessage = libelle;
	}

	/**
	 * Constructor using <code> && <libelle> && <severity>
	 * 
	 * @param code
	 * @param libelle
	 * @param severity
	 */
	public PilposeBusinessException(String code, String libelle, Severity severity) {
		super(libelle);
		this.code = code;
		this.libelle = libelle;
		this.severity = severity;
		this.errorMessage = libelle;
	}

	/**
	 * @param code
	 * @param libelle
	 * @param severity
	 * @param origine
	 */
	public PilposeBusinessException(String code, String libelle, Severity severity, Class<?> origine) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.severity = severity;
		Origine = origine;
		this.errorMessage = libelle;
	}

	/**
	 * @param httpResponseStatus
	 * @param code
	 * @param libelle
	 * @param origine
	 */
	public PilposeBusinessException(HttpStatus httpResponseStatus, String code, String libelle) {
		super();
		this.httpResponseStatus = httpResponseStatus;
		this.code = code;
		this.libelle = libelle;
		this.errorMessage = libelle;
	}

	/**
	 * @param httpResponseStatus
	 * @param libelle
	 */
	public PilposeBusinessException(HttpStatus httpResponseStatus, String libelle) {
		super();
		this.httpResponseStatus = httpResponseStatus;
		this.libelle = libelle;
		this.errorMessage = libelle;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	/**
	 * @return the origine
	 */
	public Class<?> getOrigine() {
		return Origine;
	}

	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(Class<Object> origine) {
		Origine = origine;
	}

	/**
	 * @return the httpResponseStatus
	 */
	public HttpStatus getHttpResponseStatus() {
		return httpResponseStatus;
	}

	/**
	 * @param httpResponseStatus the httpResponseStatus to set
	 */
	public void setHttpResponseStatus(HttpStatus httpResponseStatus) {
		this.httpResponseStatus = httpResponseStatus;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
