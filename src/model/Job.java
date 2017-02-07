/*
 * Holds all information regarding a job.
 */
package model;

import java.util.List;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	
	/*
	 * @author Jasmine Dacones
	 * @param theJobDescription description of the job
	 * @param thePark name of the park where the job will take place
	 * @param theStart when the job will start
	 * @param theEnd when the job will end
	 */	
	public Job(String theJobDescription, Park thePark, LocalTime theStart, LocalTime theEnd) {
		jobDescription = theJobDescription;
		park = thePark;
		start = theStart;
		end = theEnd;
	}
	
	/*
	 * @author Jasmine Dacones
	 *
	 * Adds a volunteer to the list of volunteers for a job.
	 * @param volunteer a worker for the job
	 * @param volunteer worker for a job
	 */
	public void addVolunteer(UserType volunteer) {
		volunteersList.add(volunteer);	
	}
	
	/*
	 * @author Jasmine Dacones
	 *
	 * Returns a list of volunteers for a job.
	 */
	public List<UserType> getVolunteers() {
		return volunteersList;
	}

	/*
	 * @author Youcef Bennour
	 * Gets the description of this job.
	 * @return the description of this job
	 */
	public String getJobDescription(){
		return jobDescription;
	}
	
	/*
	 * @author Jasmine Dacones
	 * Gets the park where the job is located.
	 * @return the park where the job is located
	 */
	public Park getPark(){
		return park;
	}
		
	/*
	 * @author Youcef Bennour
	 * Gets the start time of this job.
	 * @return the start time of this job
	 */
	public LocalTime getStartTime(){
		return start;
	}
	
	/*
	 * @author Jasmine Dacones
	 * Gets the end time of this job.
	 * @return the end time of this job
	 */
	public LocalTime getEndTime(){
		return end;
	}
	
	/* 
	 * @author Jasmine Dacones
	 *
	 * Returns a string of all information regarding a job.
	 * @return a string of all information regarding a job.
	 */ 
	public String toString() {
		return String.format("Description: %s\nPark: %s", getJobDescription(), getPark());
	}
 }
