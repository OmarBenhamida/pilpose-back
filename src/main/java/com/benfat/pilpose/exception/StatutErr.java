package com.benfat.pilpose.exception;

import org.springframework.http.HttpStatus;

public class StatutErr {
	/**
	 * private constructor
	 */
	private StatutErr() {

	}

	public static HttpStatus getStatutErr(int pCodeErr) {

		HttpStatus vStatutErr = null;

		if (pCodeErr >= 20 && pCodeErr <= 28) {
			vStatutErr = HttpStatus.BAD_REQUEST;
		} else if (pCodeErr >= 40 && pCodeErr <= 42) {
			vStatutErr = HttpStatus.UNAUTHORIZED;
		} else if (pCodeErr >= 50 && pCodeErr <= 53) {
			vStatutErr = HttpStatus.FORBIDDEN;
		} else if (pCodeErr >= 5 && pCodeErr <= 56) {
			vStatutErr = HttpStatus.SERVICE_UNAVAILABLE;
		} else if (pCodeErr == 60) {
			vStatutErr = HttpStatus.NOT_FOUND;
		} else if (pCodeErr == 61) {
			vStatutErr = HttpStatus.METHOD_NOT_ALLOWED;
		} else if (pCodeErr == 62) {
			vStatutErr = HttpStatus.NOT_ACCEPTABLE;
		} else if (pCodeErr == 63) {
			vStatutErr = HttpStatus.REQUEST_TIMEOUT;
		} else if (pCodeErr == 64) {
			vStatutErr = HttpStatus.LENGTH_REQUIRED;
		} else if (pCodeErr == 65) {
			vStatutErr = HttpStatus.PRECONDITION_FAILED;
		} else if (pCodeErr == 1) {
			vStatutErr = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return vStatutErr;
	}
}