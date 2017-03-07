package com.theTechNinjas.urbanParks.model.exception;

public class DuplicateAuthenticationException extends RuntimeException {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = -2203617042412423181L;

	public DuplicateAuthenticationException() {
		super();
	}

	public DuplicateAuthenticationException(final String message) {
		super(message);
	}
	
}