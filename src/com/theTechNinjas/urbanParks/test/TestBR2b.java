package com.theTechNinjas.urbanParks.test;

import org.junit.Before;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 26 Feb 2017
 */
public class TestBR2b {
  //BR: Not more than the maximum number of volunteers for any job, default of 10.

	String jobOne;
	
	@Before
	public void setUp() throws ScheduleConflictException  {
		String volunteerOne = "Youcef Bennour";
		String volunteerTwo = "Joshua Neigh";
		String volunteerThree = "Yacine Lopez";
		String volunteerFour = "Ikram Neals";
		String volunteerFive = "Ben Smith";
		String volunteerSix = "Charles Brian";
		String volunteerSeven = "Xiung Tan";
		String volunteerEight = "Mohamed Ismael";
		jobOne = "Visitor Center Volunteer \tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-25 13:00";
		
		Controller.addPark("Jarrell Cove State Park");
		Controller.addJob("Jarrell Cove State Park", jobOne);
		Controller.addUser(volunteerOne, "Volunteer");
		Controller.addUser(volunteerTwo, "Volunteer");
		Controller.addUser(volunteerThree, "Volunteer");
		Controller.addUser(volunteerFour, "Volunteer");
		Controller.addUser(volunteerFive, "Volunteer");
		Controller.addUser(volunteerSix, "Volunteer");
		Controller.addUser(volunteerSeven, "Volunteer");
		Controller.addUser(volunteerEight, "Volunteer");

		
		Controller.volunteerJob(volunteerOne,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTwo,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerThree,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerFour,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerFive,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerSix,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerSeven,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerEight,"Jarrell Cove State Park", jobOne);
		
		
	}
		
	@Test (expected = NullPointerException.class)
	public void checkIfTheVolunteerExist() throws ScheduleConflictException{
		String volunteerOne = null;
		Controller.addUser(volunteerOne, "Volunteer");
		Controller.volunteerJob(volunteerOne,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void checkIfVolunteerLessThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Alvaro Garcia";
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void checkIfVolunteerEqualMaximum() throws ScheduleConflictException{
		String volunteerNine = "Melania Trump";
		String volunteerTen = "Donald Trump";
		
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.addUser(volunteerTen,"Volunteer");
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
	}
	
	@Test (expected = ScheduleConflictException.class) 
	public void checkIfVolunteersGreaterThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Barack Obama";
		String volunteerTen = "Bria Hanson";
		String volunteerEleven = "Joy Liz";
		
		
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.addUser(volunteerTen, "Volunteer");
		Controller.addUser(volunteerEleven, "Volunteer");
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerEleven,"Jarrell Cove State Park", jobOne);	
	}

}
