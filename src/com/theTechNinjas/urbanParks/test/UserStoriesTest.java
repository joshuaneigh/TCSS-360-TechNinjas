package com.theTechNinjas.urbanParks.test;

import static org.junit.Assert.*;

import java.io.File;

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
	public static void init() throws NoSuchUserException, IllegalFormatException, ScheduleConflictException {
		new File(SERIALIZED_PATH).renameTo(new File(SERIALIZED_PATH + ".bak"));
		Controller.login("admin");
		Controller.addPark("Wright Park");
		Controller.addJob("Wright Park", "Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00");
		Controller.addUser("jneigh", "Volunteer");
		Controller.logout();
	}

	/**
	 * Removes the temporary serialized object.
	 */
	@AfterClass
	public static void post() {
		new File(SERIALIZED_PATH).delete();
		new File(SERIALIZED_PATH + ".bak").renameTo(new File(SERIALIZED_PATH));
	}
	
	@Test
	public void testAsAVolunteerIWantToSignUpForAJob() throws NoSuchUserException, InvalidUserTypeException, ScheduleConflictException {
		Controller.login("jneigh");
		assertFalse(Controller.getVolunteerJobs("jneigh").contains("Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00"));
		Controller.volunteerJob("jneigh", "Wright Park", "Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00");
		assertTrue(Controller.getVolunteerJobs("jneigh").contains("Trim Hedges\tThe hedges near the fountain need trimmed.\t2017-03-20 08:00\t2017-03-20 12:00"));
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
		assertFalse(Controller.getParkJobs("Wright Park").contains(JOB));
		Controller.addJob("Wright Park", JOB);
		assertTrue(Controller.getParkJobs("Wright Park").contains(JOB));
		Controller.logout();
	}

}
