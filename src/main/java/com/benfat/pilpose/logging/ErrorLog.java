package com.benfat.pilpose.logging;

import java.util.Date;

import com.benfat.pilpose.util.Functions;

public class ErrorLog extends LogClass {

	protected Date dateErreur;
	protected String severity;

	public ErrorLog(String ctx, String origine, Date dateErreur, String messageErreur, String severity, Object params) {
		super(ctx, origine, messageErreur, params);
		this.dateErreur = dateErreur;
		this.severity = severity;
	}

	public ErrorLog() {
		super();
	}

	/**
	 * @return the dateErreur
	 */
	public String getDateErreur() {
		return Functions.getDateInLogDateFormat(dateErreur);
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n [");
		sb.append(getCtx());
		sb.append(" |");
		sb.append(getOrigine());
		sb.append(" |");
		sb.append(getDateErreur());
		sb.append(" |");
		sb.append(getDescription());
		sb.append(" |");
		sb.append(getSeverity());
		sb.append(" |");
		sb.append(getParams());
		sb.append("] \n");
		return sb.toString();
	}

	public static void main(String[] args) {
		ErrorLog e = new ErrorLog();
		e.setSeverity(null);
	}
}