package com.benfat.pilpose.logging;

import java.util.Date;

import com.benfat.pilpose.enums.CtxtEnum;
import com.benfat.pilpose.util.Constants;

/**
 * @category : Factory log parent
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public final class FactoryLog {

	/**
	 * Private constructor
	 */
	private FactoryLog() {
		super();
	}

	/**
	 * Rest logging
	 * 
	 * @param origine
	 * @param description
	 * @param username
	 * @param pathRs
	 * @param params
	 * @return
	 */
	public static String getRsLog(String origine, String description, String username, String method, String pathRs, Object params) {
		if (params == null) {
			params = Constants.NO_PARAM;
		}
		return new RsLog(CtxtEnum.REST.toString(), origine, description, username, method, pathRs, params).toString();
	}
	
	/**
	 * Rest logging with port
	 * 
	 * @param origine
	 * @param description
	 * @param username
	 * @param pathRs
	 * @param params
	 * @return
	 */
	public static String getRsLog(String origine, Integer port, String description, String username, String method, String pathRs, Object params) {
		if (params == null) {
			params = Constants.NO_PARAM;
		}
		return new RsLog(CtxtEnum.REST.toString(), origine, port, description, username, method, pathRs, params).toString();
	}

	/**
	 * Service logging
	 * 
	 * @param origine
	 * @param description
	 * @param dateStart
	 * @param dateEnd
	 * @param username
	 * @param params
	 * @return
	 */
	public static String getServLog(String origine, String description, Date dateStart, Date dateEnd, Object params) {
		if (params == null) {
			params = Constants.NO_PARAM;
		}
		return new ServiceLog(CtxtEnum.SERVICE.toString(), origine, description, dateStart, dateEnd, params).toString();
	}

	/**
	 * Rule logging
	 * 
	 * @param origine
	 * @param description
	 * @param params
	 * @return
	 */
	public static String getRuleLog(String origine, String description, Object params) {
		if (params == null) {
			params = Constants.NO_PARAM;
		}
		return new RuleLog(CtxtEnum.RULES.toString(), origine, description, params).toString();
	}

	/**
	 * Error loging
	 * 
	 * @param context
	 * @param origine
	 * @param dateErreur
	 * @param messageErreur
	 * @param severity
	 * @param params
	 * @return
	 */
	public static String getErrorLog(String context, String origine, Date dateErreur, String messageErreur, String severity, Object params) {
		if (params == null) {
			params = Constants.NULL_OBJECT;
		}
		return new ErrorLog(context, origine, dateErreur, messageErreur, severity, params).toString();
	}
}