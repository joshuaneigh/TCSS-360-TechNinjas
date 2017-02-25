package com.theTechNinjas.urbanParks.model.exception;

public class DuplicateInstanceException extends RuntimeException {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = -681957268072677746L;
	
	public DuplicateInstanceException() {
		super();
		printStackTrace();
		System.exit(0);
	}

	public DuplicateInstanceException(final String message) {
		super(message);
		printStackTrace();
		System.exit(0);
	}
	
}