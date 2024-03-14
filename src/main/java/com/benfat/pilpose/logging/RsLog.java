package com.benfat.pilpose.logging;

public class RsLog extends LogClass {
	private String pathRs;
	private String cuid;
	private String method;
	private Integer port;

	/**
	 * Rest log
	 *
	 * @param ctx
	 * @param origine
	 * @param description
	 * @param dateStart
	 * @param dateEnd
	 * @param cuid
	 * @param pathRs
	 * @param params
	 */
	public RsLog(String ctx, String origine, String description, String cuid, String method, String pathRs,
			Object params) {
		super(ctx, origine, description, params);
		this.pathRs = pathRs;
		this.cuid = cuid;
		this.method = method;
	}

	/**
	 * Rest log with port
	 *
	 * @param ctx
	 * @param origine
	 * @param port
	 * @param description
	 * @param cuid
	 * @param method
	 * @param pathRs
	 * @param params
	 */
	public RsLog(String ctx, String origine, Integer port, String description, String cuid, String method,
			String pathRs, Object params) {
		super(ctx, origine, description, params);
		this.pathRs = pathRs;
		this.cuid = cuid;
		this.method = method;
		this.port = port;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n [");
		sb.append(getCtx());
		sb.append(" |");
		sb.append(getOrigine());
		sb.append(" |");
		sb.append((port != null) ? "instance-" + port : "Pas de port !");
		sb.append(" |");
		sb.append(getDescription());
		sb.append(" |");
		sb.append(cuid);
		sb.append(" |");
		sb.append(" [");
		sb.append(method);
		sb.append("]");
		sb.append(pathRs);
		sb.append(" |");
		sb.append(getParams());
		sb.append("] \n");
		return sb.toString();
	}

}