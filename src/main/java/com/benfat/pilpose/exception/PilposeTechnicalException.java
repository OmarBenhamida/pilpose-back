package com.benfat.pilpose.exception;

/**
 * @category : Exception technique sirhus
 * @author : HADDAOUI Yassine SOFRECOM
 * @see : <yassine.haddaoui@sofrecom.com>
 * @creation : 04/06/19
 * @version : 1.0
 */
public class PilposeTechnicalException extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut.
	 */
	public PilposeTechnicalException() {
	}

	/**
	 * @param message
	 *            : message
	 */
	public PilposeTechnicalException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            : cause
	 */
	public PilposeTechnicalException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            : message
	 * @param cause
	 *            : cause
	 */
	public PilposeTechnicalException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
