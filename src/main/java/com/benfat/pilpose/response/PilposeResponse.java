package com.benfat.pilpose.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class PilposeResponse extends org.springframework.http.ResponseEntity<Object> {

	/**
	 * Create a new {@code ExampleResponse} with the given status code, and no body
	 * nor headers.
	 *
	 * @param status the status code
	 */
	public PilposeResponse(HttpStatus status) {
		super(null, null, status);
	}

	/**
	 * Create a new {@code ExampleResponse} with the given body and status code, and
	 * no headers.
	 *
	 * @param body   the entity body
	 * @param status the status code
	 */
	public PilposeResponse(Object body, HttpStatus status) {
		super(body, null, status);
	}

	/**
	 * Create a new {@code HttpEntity} with the given headers and status code, and
	 * no body.
	 *
	 * @param headers the entity headers
	 * @param status  the status code
	 */
	public PilposeResponse(MultiValueMap<String, String> headers, HttpStatus status) {
		super(headers, status);
	}

	/**
	 * Create a new {@code HttpEntity} with the given body, headers, and status
	 * code.
	 *
	 * @param body    the entity body
	 * @param headers the entity headers
	 * @param status  the status code
	 */
	public PilposeResponse(Object body, MultiValueMap<String, String> headers, HttpStatus status) {
		super(body, headers, status);
	}
}
