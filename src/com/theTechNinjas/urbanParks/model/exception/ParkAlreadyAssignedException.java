package com.theTechNinjas.urbanParks.model.exception;

public class ParkAlreadyAssignedException extends IllegalStateException {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = 8359226190974143084L;

	public ParkAlreadyAssignedException(final String message) {
		super(message);
	}

}
