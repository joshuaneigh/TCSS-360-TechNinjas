package com.theTechNinjas.urbanParks.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.InvalidUserTypeException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

public class TestBR3c {
	// As a Volunteer I want to volunteer for a job.
	// BR: Every User has exactly one User role from among Park Manager and Volunteer
	
	private static final String SERIALIZED_PATH;
	private static final String PARK_NAME;
	private static final String JOB_NAME_1;
	private static final String JOB_NAME_2;
	private static final String USER_NAME_1;
	private static final String USER_NAME_2;
	
	static {
		SERIALIZED_PATH = "./data/data.ser";
		USER_NAME_1 = "Volunteer";
		USER_NAME_2 = "Park Manager";
		PARK_NAME = "theTestPark";
		JOB_NAME_1 = "Janitor\tCleaning\t2017-03-20 12:30\t2017-03-20 13:30";
		JOB_NAME_2 = "Raking\tCleaning\t2017-03-20 12:30\t2017-03-20 13:30";
	}
	
	// assigning the values
	@BeforeClass
	public static void init() {
		new File(SERIALIZED_PATH).renameTo(new File(SERIALIZED_PATH + ".bak"));
		Controller.reset();
		Controller.login("admin");
		Controller.addUser(USER_NAME_1, USER_NAME_1);
		Controller.addUser(USER_NAME_2, USER_NAME_2);
		Controller.addPark(PARK_NAME);
		Controller.logout();
	}
	
	@AfterClass
	public static void post() {
		new File(SERIALIZED_PATH).delete();
		new File(SERIALIZED_PATH + ".bak").renameTo(new File(SERIALIZED_PATH));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
		
	@Test
	public void testEveryUserMustHaveUniqueRoles() throws IllegalFormatException, InvalidUserTypeException, ScheduleConflictException {
		assertEquals(Controller.getUserType(USER_NAME_1), "Volunteer");
		assertEquals(Controller.getUserType(USER_NAME_2), "Park Manager");
		
		Controller.login(USER_NAME_1);
		Controller.addJob(PARK_NAME, JOB_NAME_1);
		Controller.volunteerJob(USER_NAME_1, PARK_NAME, JOB_NAME_1);
		assertEquals(Controller.getLoggedInUser(), USER_NAME_1);
		Controller.logout();
		
		thrown.expect(InvalidUserTypeException.class);
		thrown.expectMessage("User must be of type Volunteer but was of type " + Controller.getUserType(USER_NAME_2) + ".");
		Controller.login(USER_NAME_2);
		Controller.addJob(PARK_NAME, JOB_NAME_2);
		Controller.volunteerJob(USER_NAME_2, PARK_NAME, JOB_NAME_2);
		assertEquals(Controller.getLoggedInUser(), USER_NAME_2);
	}

}
