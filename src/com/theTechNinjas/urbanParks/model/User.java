package com.theTechNinjas.urbanParks.model;

import java.io.Serializable;

final class User implements Serializable {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = -1258211426794017332L;
	
	public static final String VOLUNTEER;
	public static final String PARK_MANAGER;
	public static final String ADMINISTRATOR;
	
	static {
		VOLUNTEER = "Volunteer";
		PARK_MANAGER = "Park Manager";
		ADMINISTRATOR = "Administrator";
	}

	private final String name;
	
	public User(final String jobName) {
		name = jobName;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
