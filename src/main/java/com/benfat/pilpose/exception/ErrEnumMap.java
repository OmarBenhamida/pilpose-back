package com.benfat.pilpose.exception;

/**
 * @author : Gestion des erreurs sur les APIs (errors Handler)
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public enum ErrEnumMap {
	TWENTY(20, "Invalid URL parameter value"), TWENTY_ONE(21, "Missing body"), TWENTY_TWO(22, "Invalid body"), TWENTY_THREE(23,
			"Missing body field"), TWENTY_FOUR(24, "Invalid body field"), TWENTY_FIVE(25, "Missing header"), TWENTY_SIX(26,
					"Invalid header value"), TWENTY_SEVEN(27, "Missing query-string parameter"), TWNETY_EIGHT(28,
							"Invalid query-string parameter value"), FORTY(40, "Missing credentials"), FORTY_ONE(41, "Invalid credentials"), FORTY_TWO(42,
									"Expired credentials"), FIFTY(50, "Access denied"), FIFTY_ONE(51, "Forbidden requester"), FIFTY_TWO(52,
											"Forbidden user"), FIFTY_THREE(53, "Too many requests"), SIXTY(60, "Resource not found"), SIXTY_ONE(61,
													"Method not allowed"), SIXTY_TWO(62, "Not acceptable"), SIXTY_THREE(63, "Request time-out"), SIXTY_FOUR(64,
															"Length required"), SIXTY_FIVE(65, "Precondition failed"), SIXTY_SIX(66,
																	"Request entity too large"), SIXTY_SEVEN(67, "Request-URI too long"), ONE(1,
																			"Internal error"), FIVE(5, "The service is temporarily unavailable"), SIX(6,
																					"Orange API is over capacity, retry later !"),;

	private int codeErr;
	private String msgErr;

	private ErrEnumMap(int codeErr, String msgErr) {
		this.codeErr = codeErr;
		this.msgErr = msgErr;
	}

	/**
	 * @return the msgErr
	 */
	public String getMsgErr() {
		return msgErr;
	}

	public int getCodeErr() {
		return codeErr;
	}

}
