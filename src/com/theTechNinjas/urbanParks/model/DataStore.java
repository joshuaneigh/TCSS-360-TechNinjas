package com.theTechNinjas.urbanParks.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.theTechNinjas.urbanParks.model.exception.DuplicateJobException;
import com.theTechNinjas.urbanParks.model.exception.DuplicateParkException;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchJobException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchParkException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.exception.ParkAlreadyAssignedException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Responsible for handling all data and persistent storage. This {@link Class}
 * follows the Singleton design pattern and should only exist in one instance.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 21 Feb 2017
 */
public final class DataStore implements Serializable {
	
	/** Automatically generated serial version unique ID. */
	private static final long serialVersionUID = -6890714915299935815L;

	private static final DataStore INSTANCE;
	private static final String SAVE_PATH;
	
	private Map<String, String> userTypeMap;
	private List<String> parkList;
	private List<String> jobList;
	private Map<String, List<String>> userParkMap;
	private Map<String, List<String>> parkJobMap;
	private Map<String, List<String>> jobUserMap;
	private int maxPendingJobs;
	private int maxVolunteersPerJob;
	private int maxJobLengthDays;
	private int maxJobsPerDay;
	private int maxDaysFromNowAllowedInSchedule;
	private int minDaysFromNowToVolunteer;

	/*
	 * This static block's sole responsibility is to deserialize the DataStore if it exists.
	 */
	static {
		SAVE_PATH = "./data/data.ser";
		
		final Path path = Paths.get(SAVE_PATH);
		if (path.toFile().isFile()) {
			DataStore deserialized = new DataStore();
			try {
				deserialized = deserialize(new String(Files.readAllBytes(path), StandardCharsets.UTF_8),
						DataStore.class);
			} catch (ClassNotFoundException | IOException e) { // Exists but could not be loaded
				deserialized = new DataStore();
				e.printStackTrace();
			}
			INSTANCE = deserialized; // Exists and successful
		} else {
			INSTANCE = new DataStore(); // Doesn't exist
		}
	}
	
	private DataStore() {
		userTypeMap = new HashMap<>();
		parkList = new ArrayList<>();
		jobList = new ArrayList<>();
		userParkMap = new HashMap<>();
		parkJobMap = new HashMap<>();
		jobUserMap = new HashMap<>();
		maxPendingJobs = 20;
		maxVolunteersPerJob = 10;
		maxJobLengthDays = 3;
		maxJobsPerDay = 4;
		maxDaysFromNowAllowedInSchedule = 75;
		minDaysFromNowToVolunteer = 2;
		
		addUser("admin", User.ADMINISTRATOR);
	}
	
	/**
	 * Returns the only instance of this {@link DataStore} {@link Object}.
	 * 
	 * @return
	 */
	public static DataStore getInstance() {
		return INSTANCE;
	}
	
	public void exit() {
		try {
			final Path path = Paths.get(SAVE_PATH);
			final String serialized = serialize(this);
			if (path.toFile().isFile()) {
				// TODO: Sleep Thread and wait for response of whether to override file.
			} else {
				path.toFile().getParentFile().mkdirs();
				path.toFile().createNewFile();
			}
			final PrintStream out = new PrintStream(new FileOutputStream(SAVE_PATH));
			out.print(serialized);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tries to serialize the passed {@link Object} into a {@link String}.
	 * 
	 * @param object the {@link Object} to serialize
	 * @return the {@link String} serialization of the passed {@link Object}
	 * @throws IOException if the passed {@link Object} is not {@link Serializable}
	 */
	private static final <T extends Serializable> String serialize(final T object) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return new String(Base64.getEncoder().encode(baos.toByteArray()));
	}

	/**
	 * Tries to deserialize the passed {@link String} into the passed
	 * {@link Object} type.
	 * 
	 * @param serialized a {@link String} which contains a serialized {@link Object}
	 * @param type a reference to the class which will be used to cast
	 * @return the deserialized Object of passed type from the passed {@link String}
	 * @throws IOException if the passed {@link String} could not be deserialized
	 * @throws ClassNotFoundException if the passed class is invalid or if mismatched with the deserialized {@link Object}
	 */
	private static final <T extends Serializable> T deserialize(final String serialized, final Class<T> type)
			throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(serialized.getBytes());
		final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		return type.cast(ois.readObject());
	}
	
	public void addUser(final String userName, final String userType) throws IllegalFormatException {
		Objects.requireNonNull(userName);
		userTypeMap.put(userName, userType);
		userParkMap.put(userName, new ArrayList<>());
	}
	
	/**
	 * Adds the specified {@link Park} to the system.
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link IllegalFormatException} if the passed {@link Park} is not in the specified format
	 * @throws {@link DuplicateParkException} if the passed {@link Park} is already added
	 */
	public void addPark(final String parkName) throws IllegalFormatException {
		Objects.requireNonNull(parkName);
		if (parkList.contains(parkName)) throw new DuplicateParkException(parkName);
		parkList.add(parkName);
		parkJobMap.put(parkName, new ArrayList<>());
		return;
	}
	
	/**
	 * Adds the specified {@link Job} to the system.
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link IllegalFormatException} if the passed {@link Job} is not in the specified format
	 * @throws {@link NoSuchParkException} if the passed {@link Park} is not already in the system
	 * @throws {@link DuplicateJobException} if the passed {@link Job} is already added
	 */
	public void addJob(final String parkName, final String jobName) {
		Objects.requireNonNull(parkName);
		Objects.requireNonNull(jobName);
		if (!parkList.contains(parkName)) throw new NoSuchParkException(parkName);
		if (parkJobMap.get(parkName).contains(jobName)) throw new DuplicateJobException(jobName);
		parkJobMap.get(parkName).add(jobName);
		jobUserMap.put(jobName, new ArrayList<>());
		jobList.add(jobName);
	}
	
	/**
	 * Assigns the passed {@link Park} to the corresponding {@link User}.
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link NoSuchUserException} if the passed {@link User} is not already in the system
	 * @throws {@link NoSuchParkException} if the passed {@link Park} is not already in the system
	 */
	public void assignPark(final String userName, final String parkName) {
		Objects.requireNonNull(userName);
		Objects.requireNonNull(parkName);
		if (!userTypeMap.containsKey(userName)) throw new NoSuchUserException(userName);
		if (!parkList.contains(parkName)) throw new NoSuchParkException(parkName);
		if (userParkMap.containsKey(userName)) {
			if (userParkMap.get(userName).contains(parkName)) throw new ParkAlreadyAssignedException(parkName);
			else userParkMap.get(userName).add(parkName);
		} else {
			userParkMap.put(userName, Arrays.asList(parkName));
		}
	}
	
	/**
	 * Assigns the passed {@link User} to the corresponding {@link Job}.
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link NoSuchUserException} if the passed {@link User} is not already in the system
	 * @throws {@link NoSuchParkException} if the passed {@link Park} is not already in the system
	 * @throws {@link NoSuchJobException} if the passed {@link Job} is not already in the system
	 * @throws {@link ScheduleConflictException} if the {@link User} has already volunteered for the specified {@link Job}
	 */
	public void assignVolunteer(final String userName, final String parkName, final String jobName) {
		Objects.requireNonNull(userName);
		Objects.requireNonNull(parkName);
		Objects.requireNonNull(jobName);
		if (!userTypeMap.containsKey(userName)) throw new NoSuchUserException(userName);
		else if (!parkList.contains(parkName)) throw new NoSuchParkException(parkName);
		else if (!jobList.contains(jobName)) throw new NoSuchJobException(jobName);
		else if (userParkMap.containsKey(userName) && !userParkMap.get(userName).contains(parkName)) { // User not already associated with park but is added to map
			userParkMap.get(userName).add(parkName);
		} else {
			userParkMap.put(userName, Arrays.asList(parkName));
		}
		jobUserMap.get(jobName).add(userName);
		return;
	}
	
	public List<String> getUpcomingJobs() {
		final List<String> jobs = new ArrayList<>();
		final LocalDateTime now = LocalDateTime.now();
		for (final String s : jobList) {
			if (now.compareTo(Job.getStart(s)) < 0 || now.compareTo(Job.getEnd(s)) < 0) jobs.add(s);
		}
		return jobs;
	}
	
	// inclusive
	public Map<LocalDateTime, List<String>> getJobsOnDates(final LocalDateTime start, final LocalDateTime end) {
		final Map<LocalDateTime, List<String>> jobs = new HashMap<>();
		long numDays = ChronoUnit.DAYS.between(start, end);
		LocalDateTime day = start;
		do {
			for (final String job : jobList) {
				if (day.isAfter(Job.getStart(job)) || day.isBefore(Job.getEnd(job))) {
					if (!jobs.containsKey(day)) {
						jobs.put(day, new ArrayList<>());
					}
					jobs.get(day).add(job);
				}
			}
			day.plusDays(1);
			numDays--;
		} while (numDays > 0);
		return jobs;
	}
	
	public List<String> getVolunteerJobs(final String userName) {
		Objects.requireNonNull(userName);
		if (!userTypeMap.containsKey(userName)) throw new NoSuchUserException(userName);
		final List<String> jobs = new ArrayList<>();
		for (final String park : userParkMap.get(userName)) {
			for (final String job : parkJobMap.get(park)) {
				if (jobUserMap.get(job).contains(userName)) {
					jobs.add(job);
				}
			}
		}
		return jobs;
	}
	
	public List<String> getVolunteers(final String parkName, final String jobName) {
		Objects.requireNonNull(parkName);
		Objects.requireNonNull(jobName);
		if (!parkList.contains(parkName)) throw new NoSuchParkException(parkName);
		if (!parkJobMap.get(parkName).contains(jobName)) throw new NoSuchJobException(jobName);
		return parkJobMap.get(parkName);
	}
	
	public List<String> getParks() {
		return new ArrayList<>(parkList);
	}
	
	public List<String> getParkJobs(final String parkName) {
		return parkJobMap.get(parkName);
	}
	
	public boolean isUser(final String userName) {
		Objects.requireNonNull(userName);
		return userTypeMap.containsKey(userName);
	}
	
	public String getUserType(final String userName) {
		Objects.requireNonNull(userName);
		if (!userTypeMap.containsKey(userName)) throw new NoSuchUserException(userName);
		return userTypeMap.get(userName);
	}
	
	public int getMaxPendingJobs() {
		return maxPendingJobs;
	}
	
	public int getMaxJobLengthDays() {
		return maxJobLengthDays;
	}
	
	public int getMaxJobPerDay() {
		return maxJobsPerDay;
	}
	
	public int getMaxVolunteersPerJob() {
		return maxVolunteersPerJob;
	}
	
	public int getMaxDaysFromNowAllowedInSchedule() {
		return maxDaysFromNowAllowedInSchedule;
	}
	
	public int getMinDaysFromNowToVolunteer() {
		return minDaysFromNowToVolunteer;
	}
	
}
