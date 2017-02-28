package com.theTechNinjas.urbanParks.test;

import org.junit.Before;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.User;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

public class TestBR2b {
/**
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 26 Feb 2017
 */
public class MaximumNumberOfVolunteers {
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
		Controller.addUser(volunteerOne, User.VOLUNTEER);
		Controller.addUser(volunteerTwo, User.VOLUNTEER);
		Controller.addUser(volunteerThree, User.VOLUNTEER);
		Controller.addUser(volunteerFour, User.VOLUNTEER);
		Controller.addUser(volunteerFive, User.VOLUNTEER);
		Controller.addUser(volunteerSix, User.VOLUNTEER);
		Controller.addUser(volunteerSeven, User.VOLUNTEER);
		Controller.addUser(volunteerEight, User.VOLUNTEER);

		
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
		Controller.addUser(volunteerOne, User.VOLUNTEER);
		Controller.volunteerJob(volunteerOne,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void checkIfVolunteerLessThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Alvaro Garcia";
		Controller.addUser(volunteerNine, User.VOLUNTEER);
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void checkIfVolunteerEqualMaximum() throws ScheduleConflictException{
		String volunteerNine = "Melania Trump";
		String volunteerTen = "Donald Trump";
		
		Controller.addUser(volunteerNine, User.VOLUNTEER);
		Controller.addUser(volunteerTen, User.VOLUNTEER);
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
	}
	
	@Test (expected = ScheduleConflictException.class) 
	public void checkIfVolunteersGreaterThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Barack Obama";
		String volunteerTen = "Bria Hanson";
		String volunteerEleven = "Joy Liz";
		
		
		Controller.addUser(volunteerNine, User.VOLUNTEER);
		Controller.addUser(volunteerTen, User.VOLUNTEER);
		Controller.addUser(volunteerEleven, User.VOLUNTEER);
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerEleven,"Jarrell Cove State Park", jobOne);	
	}

}
