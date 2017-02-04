/*
 * Holds all information regarding a job.
 */
package model;

import java.util.List;

/**
 * @author Jasmine Dacones
 *
 */
public class Job {
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
 }
