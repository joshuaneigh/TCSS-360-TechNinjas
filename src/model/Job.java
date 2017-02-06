/*
 * Holds all information regarding a job.
 */
package model;

import java.util.List;
import java.io.Serializable;

/**
 * @author Jasmine Dacones
 *
 */
public class Job implements Serializable {
	int MAX_VOLUME = 30;
	int MAX_LENGTH = 2;

	private String desc;
	private Park park;
	private List<UserType> volunteersList;
	private DateAndTime start;
	private DateAndTime end;
	
	public Job(String theDesc, Park thePark, DateAndTime theStart, DateAndTime theEnd) {
		desc = theDesc;
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
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String theDesc) {
		desc = theDesc;
	}
	
	public Park getPark() {
		return park;
	}
	
	public void setPark(Park thePark) {
		park = thePark;
	}
	
	public DateAndTime getStartTime() {
		return start;
	}
	
	public void setStartTime(DateAndTime theStart) {
		start = theStart;
	}
	
	public DateAndTime getEndTime() {
		return end;
	}
	
	public void setEndTime(DateAndTime theEnd) {
		end = theEnd;
	}
	
	public String toString() {
		return String.format("Description: %s\nPark: %s", getDesc(), getPark());
	}
 }
