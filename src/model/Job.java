/*
 * Holds all information regarding a job.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * LocalDateTime newDate = LocalDateTime.of(2017, Month.MAY, 25, 4, 30);
 * System.out.println("Date: " + newDate.getMonth() + " " + newDate.getDayOfMonth() + " " + newDate.getYear());
 *
 */

[TestLink](#test-link)
public class Job implements Serializable {
	
	private static final long serialVersionUID = 812488421841214L;
	
	int MAX_VOLUME = 30;
	int MAX_LENGTH = 2;
		
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	LocalDateTime newDate;
	
	private String jobDescription;
	private Park park;
	private List<UserType> volunteersList = new ArrayList<UserType>();
	private String start;
	private String end;
	
	/*
	 * @author Jasmine Dacones
	 * @param theJobDescription description of the job
	 * @param thePark name of the park where the job will take place
	 * @param theStart when the job will start
	 * @param theEnd when the job will end
	 */	
	public Job(String theJobDescription, Park thePark) {
		jobDescription = theJobDescription;
		park = thePark;
	}
	
	/*
	 * @author Jasmine Dacones
	 * Adds a volunteer to the list of volunteers for a job.
	 * @param volunteer a worker for the job
	 * @param volunteer worker for a job
	 */
	public void addVolunteer(UserType volunteer) {
		volunteersList.add(volunteer);	
	}
	
	/*
	 * @author Jasmine Dacones
	 * Returns a list of volunteers for a job.
	 */
	 ## getVolunteers <a id="test-link"></a>
	public List<UserType> getVolunteers() {
		return volunteersList;
	}

	/*
	 * @author Jasmine Dacones
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
	public String getPark(){
		return park.getName();
	}
	
		
	/*
	 * @author Jasmine Dacones
	 * Gets the start time of this job.
	 * @return the start time of this job
	 */
	public String getStartTime(){
		return start;
	}
	
	
	/*
	 * @author Jasmine Dacones
	 *
	 * Gets the end time of this job.
	 * @return the end time of this job
	 */
	public String getEndTime(){
		return end;
	}
		
	
	/*
	 * @author Youcef Bennour
	 * Sets the time when the job starts
	 * @param the time when the job starts
	 */
	public void setStartTime(String start){
		start = now.format(formatter);
		
	}
	
	/*
	 * @author Youcef Bennour
	 * Sets the time when the job ends
	 * @param the time when the job ends
	 */
	public void setEndTime(String end){
		end = now.format(formatter);
	}
	
	/* 
	 * @author Jasmine Dacones
	 * Returns a string of all information regarding a job.
	 * @return a string of all information regarding a job.
	 */ 
	public String toString() {
		return String.format("Description: %s\nPark: %s", getJobDescription(), getPark());
	}
 }