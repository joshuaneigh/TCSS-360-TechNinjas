package com.theTechNinjas.urbanParks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;

final class Job implements Serializable {

	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = 7391413874896904288L;

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static String getName(final String jobName) {
		final String[] items = jobName.split("\t");
		return items[0];
	}
	
	public static String getDescription(final String jobName) {
		final String[] items = jobName.split("\t");
		return items[1];
	}
	
	public static LocalDateTime getStart(final String jobName) throws IllegalFormatException {
		final String[] items = jobName.split("\t");
		try {
			return LocalDateTime.parse(items[2], DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		} catch (final DateTimeParseException e) {
			throw new IllegalFormatException("The specified Job is not of proper format.");
		}
	}
	
	public static LocalDateTime getEnd(final String jobName) throws IllegalFormatException {
		final String[] items = jobName.split("\t");
		try {
			return LocalDateTime.parse(items[3], DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		} catch (final DateTimeParseException e) {
			throw new IllegalFormatException("The specified Job is not of proper format.");
		}
	}
	
}
