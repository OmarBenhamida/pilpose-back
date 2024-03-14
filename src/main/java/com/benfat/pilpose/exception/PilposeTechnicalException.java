package com.benfat.pilpose.exception;

public class PilposeTechnicalException extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut.
	 */
	public PilposeTechnicalException() {
	}

	/**
	 * @param message : message
	 */
	public PilposeTechnicalException(final String message) {
		super(message);
	}

	/**
	 * @param cause : cause
	 */
	public PilposeTechnicalException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message : message
	 * @param cause   : cause
	 */
	public PilposeTechnicalException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
