package test;

import java.io.File;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Controller;
import model.Location;
import model.Park;
import model.UserType;
import model.exception.NoSuchUserException;

/**
 * The JUnit test class for the {@link Controller} class.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 07 Feb 2017
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest {

	private static final Park WRIGHT_PARK = new Park(new Location(98445), "Wright Park");
	private static final String ADMIN_USERNAME = "Administrator";
	private static final String TEMP_USERNAME = "tempUser";
	private static final String NONUSER_USERNAME = "i'm not logged in";
	private static final String SERIALIZED_PATH = "./data/data.ser";

	@BeforeClass
	public static void init() throws NoSuchUserException {
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

	@Test
	public void a_testAuthenticateUser() throws NoSuchUserException {
		assertTrue(Controller.authenticate(ADMIN_USERNAME));
	}

	@Test(expected = NoSuchUserException.class)
	public void b_testAuthenticateNonuser() throws NoSuchUserException {
		assertFalse(Controller.authenticate(""));
	}

	@Test
	public void c_testAddNewUser() {
		assertTrue(Controller.addUser(TEMP_USERNAME, UserType.Volunteer));
	}

	@Test(expected = IllegalStateException.class)
	public void d_testAddDuplicateUser() {
		assertFalse(Controller.addUser(ADMIN_USERNAME, UserType.ParkManager));
	}

	@Test
	public void e_testAddNewPark() throws IllegalStateException, NoSuchUserException {
		assertTrue(Controller.addPark(TEMP_USERNAME, WRIGHT_PARK));
	}

	@Test(expected = IllegalStateException.class)
	public void f_testAddDuplicatePark() throws IllegalStateException, NoSuchUserException {
		assertFalse(Controller.addPark(TEMP_USERNAME, WRIGHT_PARK));
	}

	@Test(expected = NoSuchUserException.class)
	public void g_testAddNewParkNonuser() throws IllegalStateException, NoSuchUserException {
		assertFalse(Controller.addPark(NONUSER_USERNAME, WRIGHT_PARK));
	}

	@Test
	public void h_testRemoveUser() throws NoSuchUserException {
		assertTrue(Controller.removeUser(TEMP_USERNAME));
	}

	@Test(expected = NoSuchUserException.class)
	public void i_testRemoveNonuser() throws NoSuchUserException {
		assertFalse(Controller.removeUser(NONUSER_USERNAME));
	}

	@Test(expected = NoSuchUserException.class)
	public void j_testDisconnectNonuser() throws NoSuchUserException {
		assertFalse(Controller.disconnect(NONUSER_USERNAME));
	}

	@Test
	public void k_testDisconnectUser() throws NoSuchUserException {
		assertTrue(Controller.disconnect(ADMIN_USERNAME));
	}

}
