/*
 * Holds all information regarding a job.
 */
package model;

import java.util.List;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @author Jasmine Dacones, @author Youcef Bennour
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
	
	public Job(String theJobDescription, Park thePark, DateAndTime theStart, DateAndTime theEnd) {
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

	public String getJobDescription(){
		return jobDescription;
	}
	
	public Park getPark(){
		return park;
	}
	
	public DateAndTime getStartTime(){
		return start;
	}
	
	public DateAndTime getEndTime(){
		return end;
	}
	
	public void setJobDescription(String theJobDescription){
		jobDescription = theJobDescription;
	}
	
	public void setPark(Park thePark){
		park = thePark;
	}
	
	public void setStartTime(DateAndTime theStart){
		start = theStart;
	}
	
	public void setEndTime(DateAndTime theEnd){
		end = theEnd;
	}
	
	public String toString() {
		return String.format("Description: %s\nPark: %s", getDesc(), getPark());
	}
 }
