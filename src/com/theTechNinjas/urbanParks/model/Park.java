package com.theTechNinjas.urbanParks.model;

import java.io.Serializable;

final class Park implements Serializable {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = 4244022243223407116L;

	private final String name;
	
	public Park(final String parkName) {
		name = parkName;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
