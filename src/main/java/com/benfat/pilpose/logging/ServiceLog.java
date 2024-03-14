package com.benfat.pilpose.logging;

import java.util.Date;

import com.benfat.pilpose.util.Functions;

public class ServiceLog extends LogClass {

	/**
	 * Service log
	 *
	 * @param ctx
	 * @param origine
	 * @param description
	 * @param dateErreur
	 * @param dateEnd
	 * @param cuid
	 * @param params
	 */
	protected Date dateStart;
	protected Date dateEnd;

	public ServiceLog(String ctx, String origine, String description, Date dateStart, Date dateEnd, Object params) {
		super(ctx, origine, description, params);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	/**
	 * Service Log
	 *
	 * @param ctx
	 * @param origine
	 * @param description
	 * @param params
	 */
	public ServiceLog(String ctx, String origine, String description, Object params) {
		super(ctx, origine, description, params);
	}

	/**
	 * @return the dateStart
	 */
	public String getDateStart() {
		return Functions.getDateInLogDateFormat(this.dateStart);
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return Functions.getDateInLogDateFormat(this.dateEnd);
	}

	/**
	 * @param dateEnd
	 *
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the responseTime
	 */
	public String getResponseTime() {
		return Functions.diffDate(dateStart, dateEnd);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n [");
		sb.append(getCtx());
		sb.append(" |");
		sb.append(getOrigine());
		sb.append(" |");
		sb.append(getDescription());
		sb.append(" |");
		sb.append(getDateStart());
		sb.append(" |");
		sb.append(getDateEnd());
		sb.append(" |");
		sb.append(getResponseTime());
		sb.append(" |");
		sb.append(getParams());
		sb.append("] \n");
		return sb.toString();
	}
}