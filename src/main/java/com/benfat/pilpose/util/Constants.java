package com.benfat.pilpose.util;

/**
 * @category : constants transverse
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public final class Constants {

	public static final String HEADERS = "Accept=application/json";
	public static final String HEADERS_XML = "Accept=application/xml";
	public static final String HEADERS_JSON_PATCH = "application/json-patch+json";
	public static final String APPLICATION_JSON = "application/json";
	public static final String AUTHORITIES_KEY = "authorities";
	public static final String ROLES = "roles";
	public static final String DG_ROLE = "directeur-general";
	public static final String ID_USER = "idUser";
	public static final String CR = "charge-recrutement";
	public static final String SUPER_ADMIN = "super-admin";
	public static final String DRH = "drh";
	public static final String MANAGER = "manager";
	public static final String COLLABORATEUR = "collaborateur";
	public static final String CDP = "charge mission";
	public static final String CHS = "charge social";
	public static final String NO_REPLEY_EMAIL = "noreply.sirhus@sofrecom.com";
	public static final String MATRICULE = "matricule";
	public static final String USERNAME = "user_name";
	public static final String CP = "charge-personnel";
	/**
	 * time zone
	 */
	public static final String TIME_ZONE = "UTC+1";
	/**
	 * Common
	 */
	public static final String EMPTY = "";
	public static final String HTTP = "http://";
	public static final String HTTPS = "https://";
	public static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * Size string
	 */
	public static final int STR_LENGTH_LESS1 = -1;
	public static final int STR_LENGTH_0 = 0;
	public static final int STR_LENGTH_1 = 1;
	public static final int STR_LENGTH_2 = 2;
	public static final int STR_LENGTH_5 = 5;
	public static final int STR_LENGTH_8 = 8;
	public static final int STR_LENGTH_9 = 9;
	public static final int STR_LENGTH_11 = 11;
	public static final int STR_LENGTH_12 = 12;
	public static final int STR_LENGTH_35 = 35;
	public static final int STR_LENGTH_3 = 3;
	public static final int STR_LENGTH_4 = 4;
	public static final int STR_LENGTH_14 = 14;
	public static final int STR_LENGTH_18 = 18;
	public static final int STR_LENGTH_19 = 19;
	public static final int STR_LENGTH_23 = 23;
	public static final int STR_LENGTH_25 = 25;
	public static final int STR_LENGTH_32 = 32;
	public static final int STR_LENGTH_49 = 49;
	public static final int STR_LENGTH_30 = 30;

	/**
	 * Status
	 */
	public static final String CREATED_STATUS = "CREATE";
	public static final String UPDATED_STATUS = "UPDATE";
	public static final String DELETED_STATUS = "DELETE";

	/**
	 * Message files names
	 */
	public static final String MSG_SOCLE_FILE_NAME = "socle.message.properties";

	/**
	 * Other
	 */
	public static final String NO_PARAM = "Pas de param\u00E9tre";
	public static final String NULL_OBJECT = "Objet null";

	public static final String ERRORS_NULL = "errors.null";
	public static final String ERRORS_NUMBER = "errors.number";
	public static final String ERRORS_DATE = "errors.date";
	public static final String VALIDATOR_RESOURCES = ".ValidatorResources";
	public static final String PARSE_DATE_ERROR = "Erreur parsing date!";
	public static final String TIMESTAMP_FORMAT = "validator.timestampFormat";
	public static final String NUMBER_FORMAT = "validator.numberFormat";
	public static final String DECIMAL_FORMAT = "validator.decimalFormat";
	public static final String DATE_FORMAT_COURT = "validator.dateFormat.court";

	/**
	 * date format
	 */
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String UNDERSCORE_DATE_FORMAT = "dd_MM_yyyy";
	public static final String DATE_FORMAT_YYYY = "yyyy";
	/**
	 * change value of IsAnterieure
	 */
	public static final Boolean IS_ANTERIEUR = true;


	/**
	 * default constructor
	 */
	private Constants() {
		super();
	}
}