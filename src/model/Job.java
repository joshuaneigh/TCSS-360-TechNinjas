/*
 * Holds all information regarding a job.
 */
package model;

import java.util.List;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @author Jasmine Dacones, Youcef Bennour
 *
 */
public class Job implements Serializable {
	private static final long serialVersionUID = 812488421841214L;
	
	int MAX_VOLUME = 30;
	int MAX_LENGTH = 2;
	
	LocalTime time = LocalTime.now();
	
	private String jobDescription;
	private Park park;
	private List<UserType> volunteersList;
	private DateAndTime start;
	private DateAndTime end;
	
	public Job(String theJobDescription, Park thePark, LocalTime theStart, LocalTime theEnd) {
		jobDescription = theJobDescription;
		park = thePark;
		start = theStart;
		end = theEnd;
	}
	
	/*
	 * Adds a volunteer to the list of volunteers for a job.
	 */
	public void addVolunteer(UserType volunteer) {
		volunteersList.add(volunteer);	
	}
	
	/*
	 * Returns a list of volunteers for a job.
	 */
	public List<UserType> getVolunteers() {
		return volunteersList;
	}

	/*
	 * Gets the description of this job.
	 * @return the description of this job
	 */
	public String getJobDescription(){
		return jobDescription;
	}
	
	/*
	 * Gets the park where the job is located.
	 * @return the park where the job is located
	 */
	public Park getPark(){
		return park;
	}
		
	/*
	 * Gets the start time of this job.
	 * @return the start time of this job
	 */
	public LocalTime getStartTime(){
		return start;
	}
	
	/*
	 * Gets the end time of this job.
	 * @return the end time of this job
	 */
	public LocalTime getEndTime(){
		return end;
	}
	
	/*
	 * Sets the description of this job.
	 * @param theJobDescription the description of the job
	 */
	public void setJobDescription(String theJobDescription){
		jobDescription = theJobDescription;
	}
	
	/*
	 * Sets the name of the park for this job.
	 * @param thePark the name of the park for this job
	 */
	public void setPark(Park thePark){
		park = thePark;
	}
	
	/* Sets the start time of this job.
	 * @param theStart the start time of this job
	 */ 
	public void setStartTime(LocalTime theStart){
		start = theStart;
	}
	
	/* Sets the end time of this job.
	 * @param theEnd the end time of this job
	 */ 
	public void setEndTime(LocalTime theEnd){
		end = theEnd;
	}
	
	/* Returns a string of all information regarding a job.
	 * @return a string of all information regarding a job.
	 */ 
	public String toString() {
		return String.format("Description: %s\nPark: %s", getDesc(), getPark());
	}
 }
