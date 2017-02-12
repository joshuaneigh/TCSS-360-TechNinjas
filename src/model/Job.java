package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Holds all information regarding a job.
 *
 * @author Jasmine Dacones | jazzyd25@uw.edu
 * @version 12 Feb 2017
 */
public class Job implements Serializable {
	
	private static final long serialVersionUID = 812488421841214L;
	
	int MAX_VOLUME = 30;
	int MAX_LENGTH = 2;
		
	private String jobDescription;
	private Park park;
	private List<UserType> volunteersList;
	private LocalDateTime start;
	private LocalDateTime end;
	
	/**
	 * @param theJobDescription description of the job
	 * @param thePark name of the park where the job will take place
	 * @param theStart when the job will start
	 * @param theEnd when the job will end
	 */	
	public Job(String theJobDescription, Park thePark) {
		jobDescription = theJobDescription;
		park = thePark;
		volunteersList = new ArrayList<UserType>();
	}
	
	/**
	 * Adds a volunteer to the list of volunteers for a job.
	 * 
	 * @param volunteer a worker for the job
	 * @param volunteer worker for a job
	 */
	public void addVolunteer(UserType volunteer) {
		volunteersList.add(volunteer);	
	}
	
	/**
	 * Returns a list of volunteers for a job.
	 */
	public List<UserType> getVolunteers() {
		return volunteersList;
	}

	/**
	 * Gets the description of this job.
	 * 
	 * @return the description of this job
	 */
	public String getJobDescription(){
		return jobDescription;
	}
	
	/**
	 * Gets the park where the job is located.
	 * 
	 * @return the park where the job is located
	 */
	public String getPark(){
		return park.getName();
	}
	
		
	/**
	 * Gets the start time of this job.
	 * 
	 * @return the start time of this job
	 */
	public LocalDateTime getStartTime(){
		return start;
	}
	
	
	/**
	 * Gets the end time of this job.
	 * 
	 * @return the end time of this job
	 */
	public LocalDateTime getEndTime(){
		return end;
	}
		
	
	/**
	 * Sets the time when the job starts
	 * 
	 * @param the time when the job starts
	 */
	public void setStartTime(final LocalDateTime start){
		this.start = start;
	}
	
	/**
	 * Sets the time when the job ends
	 * 
	 * @param the time when the job ends
	 */
	public void setEndTime(final LocalDateTime end){
		this.end = end;
	}
	
	/**
	 * Returns a string of all information regarding a job.
	 * 
	 * @return a string of all information regarding a job.
	 */ 
	@Override
	public String toString() {
		return String.format("Description: %s\nPark: %s", getJobDescription(), getPark());
	}
 }