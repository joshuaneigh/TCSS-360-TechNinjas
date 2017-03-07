package com.theTechNinjas.urbanParks.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.DuplicateAuthenticationException;
import com.theTechNinjas.urbanParks.model.exception.DuplicateJobException;
import com.theTechNinjas.urbanParks.model.exception.InvalidUserTypeException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchJobException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchParkException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests all of the User Stories for base functionality.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 7 Mar 2017
 */
public class UserStoriesTest {

	private static final String SERIALIZED_PATH;
	private static final String VOLUNTEER;
	private static final String PARK_MANAGER;
	private static final String USER_NO_EXIST;
	private static final String TEST_PARK;
	private static final String PARK_NO_EXIST;
	private static final String JOB_1;
	private static final String JOB_2;
	private static final String JOB_NEW;
	private static final String JOB_NO_EXIST;
	
	static {
		SERIALIZED_PATH = "./data/data.ser";
		VOLUNTEER = "volunteerOfThePark";
		PARK_MANAGER = "managerOfThePark";
		USER_NO_EXIST = "thisUserDoesNotExist!";
		TEST_PARK = "Washington Park";
		PARK_NO_EXIST = "thisParkDoesNotExist!";
		JOB_1 = "Mow Lawn\tThe lawn needs mowed by the park benches.\t2017-04-17 08:00\t2017-04-17 12:00";
		JOB_2 = "Trim Hedges\tThe hedges near the garden need trimmed.\t2017-04-17 13:00\t2017-04-17 14:00";
		JOB_NEW = "Water Garden\tThe roses need watered. Please ask for instructions.\t2017-03-31 17:00\t2017-03-31 19:00";
		JOB_NO_EXIST = "Planting Flowers\tFlowers need planted around the new fountain.\t2017-03-15 12:00\t2017-03-15 17:00";
	}

	@BeforeClass
	public static void init() {
		new File(SERIALIZED_PATH).renameTo(new File(SERIALIZED_PATH + ".bak"));
	}

	/**
	 * Removes the temporary serialized object.
	 */
	@AfterClass
	public static void post() {
		new File(SERIALIZED_PATH).delete();
		new File(SERIALIZED_PATH + ".bak").renameTo(new File(SERIALIZED_PATH));
	}
	
	@Before
	public void setup() throws ScheduleConflictException {
		Controller.reset();
		Controller.login("admin");
		Controller.addPark(TEST_PARK);
		Controller.addJob(TEST_PARK, JOB_1);
		Controller.addJob(TEST_PARK, JOB_2);
		Controller.addUser(VOLUNTEER, "Volunteer");
		Controller.addUser(PARK_MANAGER, "Park Manager");
		Controller.logout();
		Controller.login(VOLUNTEER);
	}
	
	@After
	public void tearDown() {
		Controller.logout();
	}
	
	/* ============= As a User I want sign in. ============= */
	
	@Test
	public void testAsAUserIWantToSignIn_userExists() {
		Controller.logout();
		Controller.login(VOLUNTEER);
		Controller.logout();
		Controller.login(PARK_MANAGER);
	}
	
	@Test (expected=DuplicateAuthenticationException.class)
	public void testAsAUserIWantToSignIn_userExistsAlreadyLoggedIn_expectedDuplicateAuthenticationException() {
		Controller.login(VOLUNTEER);
	}
	
	@Test (expected=NoSuchUserException.class)
	public void testAsAUserIWantToSignIn_userDoesNotExist_expectedNoSuchUserException() {
		Controller.logout();
		Controller.login(USER_NO_EXIST);
	}
	
	/* ============= As a Volunteer I want to sign up for a Job. ============= */
	
	@Test
	public void testAsAVolunteerIWantToSignUpForAJob_userIsVolunteerAndJobExistsAndNoOtherJobsOnDate() throws ScheduleConflictException {
		assertFalse(Controller.getVolunteerJobs(VOLUNTEER).contains(JOB_1));
		Controller.volunteerJob(VOLUNTEER, TEST_PARK, JOB_1);
		assertTrue(Controller.getVolunteerJobs(VOLUNTEER).contains(JOB_1));
	}
	
	@Test (expected=NoSuchUserException.class)
	public void testAsAVolunteerIWantToSignUpForAJob_userDoesNotExist_expectNoSuchUserException() throws ScheduleConflictException {
		Controller.volunteerJob(USER_NO_EXIST, TEST_PARK, JOB_1);
	}
	
	@Test (expected=InvalidUserTypeException.class)
	public void testAsAVolunteerIWantToSignUpForAJob_userIsNotTypeVolunteer_expectInvalidUserTypeException() throws ScheduleConflictException {
		Controller.volunteerJob(PARK_MANAGER, TEST_PARK, JOB_1);
	}
	
	@Test (expected=NoSuchParkException.class)
	public void testAsAVolunteerIWantToSignUpForAJob_parkNotInSystem_expectNoSuchParkException() throws ScheduleConflictException {
		Controller.volunteerJob(VOLUNTEER, PARK_NO_EXIST, JOB_1);
	}
	
	@Test (expected=NoSuchJobException.class)
	public void testAsAVolunteerIWantToSignUpForAJob_jobNotAddedToPark_expectNoSuchJobException() throws ScheduleConflictException {
		Controller.volunteerJob(VOLUNTEER, TEST_PARK, JOB_NO_EXIST);
	}
	
	@Test (expected=ScheduleConflictException.class)
	public void testAsAVolunteerIWantToSignUpForAJob_userSignedForJobOnSameDay_expectScheduleConflictException() throws ScheduleConflictException {
	    Controller.volunteerJob(VOLUNTEER, TEST_PARK, JOB_1);
		Controller.volunteerJob(VOLUNTEER, TEST_PARK, JOB_2);
	}
	
	/* ============= As a Park Manager I want to submit a new Job. ============= */
	
	@Test
	public void testAsAParkManagerIWantToSubmitANewJob_jobNotPreviouslySubmittedAndParkExistsAndUserIsParkManager() throws ScheduleConflictException {
		Controller.logout();
		Controller.login(PARK_MANAGER);
		assertFalse(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.addJob(TEST_PARK, JOB_NEW);
		assertTrue(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.logout();
	}

	@Test (expected=NoSuchParkException.class)
	public void testAsAParkManagerIWantToSubmitANewJob_parkNotInSystem_expectNoSuchParkException() throws ScheduleConflictException {
		Controller.logout();
		Controller.login(PARK_MANAGER);
		assertFalse(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.addJob(PARK_NO_EXIST, JOB_NEW);
		assertTrue(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.logout();
	}
	
	@Test (expected=DuplicateJobException.class)
	public void testAsAParkManagerIWantToSubmitANewJob_jobAlreadyAdded_expectDuplicateJobException() throws ScheduleConflictException {
		Controller.logout();
		Controller.login(PARK_MANAGER);
		assertFalse(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.addJob(TEST_PARK, JOB_1);
		assertTrue(Controller.getParkJobs(TEST_PARK).contains(JOB_NEW));
		Controller.logout();
	}
	
}
