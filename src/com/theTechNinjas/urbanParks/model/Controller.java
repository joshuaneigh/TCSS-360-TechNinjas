package com.theTechNinjas.urbanParks.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.InvalidUserTypeException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

public final class Controller {

	private static String loggedInUser;
	
	public static void login(final String userName) throws NoSuchUserException {
		Objects.requireNonNull(userName);
		final DataStore data = DataStore.getInstance();
		// Every User has exactly one User role from among Park Manager and Volunteer
		if (data.isUser(userName)) {
			loggedInUser = userName;
		} else {
			// No User can carry out any other user story until he or she has logged in
			throw new NoSuchUserException(userName);
		}
	}
	
	public static void logout() {
		DataStore.getInstance().exit();
	}
	
	/**
	 * 
	 * @rule Not more than the maximum number of pending jobs (start or end date) at a time, default of 20 (system-wide) <br>
	 * @rule A job cannot be longer than the maximum number of days, default of 3 <br>
	 * @rule There can be no more than the maximum number of jobs scheduled on any given day, default of 4 <br>
	 * @rule A job cannot be scheduled more than the maximum allowed date into the future, default of 75 days added to the current date <br>
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link IllegalFormatException} if the Job's end date is before the start date
	 * @throws {@link ScheduleConflictException}
	 */
	public static void addJob(final String parkName, final String jobName) throws ScheduleConflictException, IllegalFormatException {
		Objects.requireNonNull(parkName);
		Objects.requireNonNull(jobName);
		final DataStore data = DataStore.getInstance();
		if (Job.getStart(jobName).isAfter(Job.getEnd(jobName))) throw new IllegalFormatException("Start date must be before end date.");
		else if (maxNumberPendingJobsExceeded(jobName)) throw new ScheduleConflictException("Maximum number of pending jobs reached.");
		else if (maxJobLengthExceeded(jobName)) throw new ScheduleConflictException("Job cannot be longer than " + data.getMaxJobLengthDays() + " days long.");
		else if (maxJobsPerDayExceeded(jobName)) throw new ScheduleConflictException("Maximum number of jobs per day met or exceeded.");
		else if (maxDaysFromNowExceeded(jobName)) throw new ScheduleConflictException("Job cannot be scheduled more than " + data.getMaxDaysFromNowAllowedInSchedule() + " days in advance.");
		else data.addJob(parkName, jobName);
	}
	
	/**
	 * 
	 * @rule A volunteer cannot sign up for more than one job on any given day. <br>
	 * @rule A volunteer may sign up only if the job is at least a minimum number of calendar days from the current date, default of 2. <br>
	 * @rule Not more than the maximum number of volunteers for any job, default of 10 <br>
	 * 
	 * @throws {@link NullPointerException} if any of the passed arguments are null
	 * @throws {@link NoSuchUserException} if the specified user does not exist
	 * @throws {@link InvalidUserTypeException} if the user is not of type Volunteer
	 * @throws {@link ScheduleConflictException} if the maximum number of volunteers has been reached
	 */
	public static void volunteerJob(final String userName, final String parkName, final String jobName) throws NoSuchUserException, InvalidUserTypeException, ScheduleConflictException {
		Objects.requireNonNull(userName);
		Objects.requireNonNull(parkName);
		Objects.requireNonNull(jobName);
		if (!DataStore.getInstance().isUser(userName)) throw new NoSuchUserException(userName);
		else if (DataStore.getInstance().getUserType(userName) != User.VOLUNTEER
				&& DataStore.getInstance().getUserType(userName) != User.ADMINISTRATOR) throw new InvalidUserTypeException("User must be of type " + User.VOLUNTEER + " but was of type " + DataStore.getInstance().getUserType(userName) + ".");
		else if (userAlreadySignedUpForJobOnDay(userName, jobName)) throw new ScheduleConflictException("Specified user is already signed up for a job on that date");
		else if (minimumNumberOfDaysFromNowToVolunteerViolated(jobName)) throw new ScheduleConflictException("Specified job must be greater than " + DataStore.getInstance().getMinDaysFromNowToVolunteer() + " days from now.");
		else if (maxVolunteersPerJobExceeded(parkName, jobName)) throw new ScheduleConflictException("The maximum number of volunteers has been reached for the job " + jobName + ".");
		else DataStore.getInstance().assignVolunteer(userName, parkName, jobName);
	}
	
	public static void assignPark(final String userName, final String parkName) {
		DataStore.getInstance().assignPark(userName, parkName);
	}
	
	public static void addUser(final String userName, final String userType) {
		DataStore.getInstance().addUser(userName, userType);
	}
	
	public static void addPark(final String parkName) {
		DataStore.getInstance().addPark(parkName);
	}
	
	/*====================== Getters ======================*/
	
	public static boolean isUser(final String userName) {
		Objects.requireNonNull(userName);
		return DataStore.getInstance().isUser(userName);
	}
	
	public static String getLoggedInUser() {
		return loggedInUser;
	}
	
	public static String getUserType(final String userName) {
		return DataStore.getInstance().getUserType(userName);
	}
	
	public static List<String> getParkJobs(final String parkName) {
		Objects.requireNonNull(parkName);
		return DataStore.getInstance().getParkJobs(parkName);
	}
	
	public static List<String> getVolunteerJobs(final String userName) {
		Objects.requireNonNull(userName);
		return DataStore.getInstance().getVolunteerJobs(userName);
	}

	public static List<String> getParks() {
		return DataStore.getInstance().getParks();
	}
	
	public static List<String> getUserParks(final String userName) {
		return DataStore.getInstance().getUserParks(userName);
	}
	
	/*====================== Business Rules ======================*/
	
	private static boolean maxNumberPendingJobsExceeded(final String jobName) {
		return DataStore.getInstance().getMaxPendingJobs() <= DataStore.getInstance().getUpcomingJobs().size();
	}
	
	private static boolean maxJobLengthExceeded(final String jobName) {
		return ChronoUnit.DAYS.between(Job.getStart(jobName), Job.getEnd(jobName)) > DataStore.getInstance().getMaxJobLengthDays();
	}
	
	private static boolean maxJobsPerDayExceeded(final String jobName) {
		Objects.requireNonNull(jobName);
		final DataStore data = DataStore.getInstance();
		final Map<LocalDateTime, List<String>> jobs = data.getJobsOnDates(Job.getStart(jobName), Job.getEnd(jobName));
		for (final LocalDateTime date : jobs.keySet()) {
			if (jobs.get(date).size() >= data.getMaxJobPerDay()) return true;
		}
		return false;
	}
	
	private static boolean maxDaysFromNowExceeded(final String jobName) {
		return Job.getEnd(jobName).isAfter(LocalDateTime.now().plusDays(DataStore.getInstance().getMaxDaysFromNowAllowedInSchedule()));
	}
	
	private static boolean userAlreadySignedUpForJobOnDay(final String userName, final String jobName) {
		final DataStore data = DataStore.getInstance();
		final List<String> jobs = data.getVolunteerJobs(userName);
		for (final String job : jobs) {
			if (! (Job.getEnd(job).isBefore(Job.getStart(jobName)) 
					|| Job.getStart(job).isAfter(Job.getEnd(jobName)))) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean minimumNumberOfDaysFromNowToVolunteerViolated(final String jobName) {
		return ChronoUnit.DAYS.between(LocalDateTime.now(), Job.getStart(jobName)) < DataStore.getInstance().getMinDaysFromNowToVolunteer();
	}
	
	private static boolean maxVolunteersPerJobExceeded(final String parkName, final String jobName) {
		return DataStore.getInstance().getVolunteers(parkName, jobName).size() + 1 >= DataStore.getInstance().getMaxVolunteersPerJob();
	}
	
}
