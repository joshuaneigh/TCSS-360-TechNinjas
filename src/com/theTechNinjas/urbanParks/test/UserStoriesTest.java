package com.theTechNinjas.urbanParks.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.InvalidUserTypeException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests all of the User Stories for base functionality.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 27 Feb 2017
 */
public class UserStoriesTest {

	private static final String SERIALIZED_PATH = "./data/data.ser";
	private static final String JOB = "Mow Lawn\tThe lawn needs mowed by the park benches.\t2017-04-17 08:00\t2017-04-17 12:00";
	
	@BeforeClass
	public static void init() throws NoSuchUserException, IllegalFormatException, ScheduleConflictException, IOException {
		new File(SERIALIZED_PATH).renameTo(new File(SERIALIZED_PATH + ".bak"));
		
		Controller.login("admin");
		Controller.addPark("Washington Park");
		Controller.addJob("Washington Park", "Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00");
		Controller.addUser("jneigh", "Volunteer");
		Controller.logout();
	}

	/**
	 * Removes the temporary serialized object.
	 * @throws IOException 
	 */
	@AfterClass
	public static void post() throws IOException {
		new File(SERIALIZED_PATH).delete();
		new File(SERIALIZED_PATH + ".bak").renameTo(new File(SERIALIZED_PATH));
	}
	
	@Test
	public void testAsAVolunteerIWantToSignUpForAJob() throws NoSuchUserException, InvalidUserTypeException, ScheduleConflictException {
		final String job = "Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00";
		Controller.login("jneigh");
		assertFalse(Controller.getVolunteerJobs("jneigh").contains(job));
		Controller.volunteerJob("jneigh", "Washington Park", job);
		assertTrue(Controller.getVolunteerJobs("jneigh").contains(job));
		Controller.logout();
	}
	
	@Test
	public void testAsAUserIWantToLogin() {
		Controller.login("jneigh");
		Controller.logout();
	}

	@Test
	public void testAsAParkManagerIWantToSubmitANewJob() throws IllegalFormatException, ScheduleConflictException {
		Controller.login("admin");
		assertFalse(Controller.getParkJobs("Washington Park").contains(JOB));
		Controller.addJob("Washington Park", JOB);
		assertTrue(Controller.getParkJobs("Washington Park").contains(JOB));
		Controller.logout();
	}

}
