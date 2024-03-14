package com.benfat.pilpose.logging;

import java.util.Date;

public class RuleLog extends LogClass {

	protected Date dateStart;
	protected Date dateEnd;

	/**
	 * Rules log
	 *
	 * @param ctx
	 * @param origine
	 * @param description
	 * @param params
	 */
	public RuleLog(String ctx, String origine, String description, Object params) {
		super(ctx, origine, description, params);
	}

	/**
	 * Rules log width dateStart and dateEnd
	 *
	 * @param ctx
	 * @param origine
	 * @param description
	 * @param dateStart
	 * @param dateEnd
	 * @param params
	 */
	public RuleLog(String ctx, String origine, String description, Date dateStart, Date dateEnd, Object params) {
		super(ctx, origine, description, params);
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(getCtx());
		sb.append(" |");
		sb.append(getOrigine());
		sb.append(" |");
		sb.append(getDescription());
		sb.append(" |");
		sb.append(getParams());
		sb.append("]");
		return sb.toString();
	}
}
