package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.User;
import model.UserType;

/**
 * Tests the User class.
 *
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 12 Feb 2017
 */
public class TestUser {
	private static User userOne, userTwo, userThree, userFour, userFive;
	private static UserType typeOne = UserType.Volunteer, typeTwo = UserType.UrbanPark, 
			typeThree = UserType.ParkManager;
	private static boolean blackballed = false;
	private static boolean flagged = false;
	
	@BeforeClass
	public static void setUp(){
		userOne = new User("Youcef Bennour", typeOne);
		userTwo = new User("Ezra Miller", typeTwo);
		userThree = new User("Melissa", typeThree);
		userFour = new User("", typeOne);
		userFive = new User("Youcef", null);
	}
	
	@Test
	public void testToStringWithAllInformation(){
		assertEquals("Youcef Bennour, Volunteer, false, false", userOne.toString());
		assertEquals("Ezra Miller, UrbanPark, false, false", userTwo.toString());
		assertEquals("Melissa, ParkManager, false, false", userThree.toString());
	}
	
	@Test
	public void testToStringWithNoName(){
		assertEquals(", Volunteer, false, false", userFour.toString());
	}

	@Test
	public void testToStringWithNoType(){
		assertEquals("Youcef, null, false, false", userFive.toString());
	}

	@Test
	public void testGetUsername(){
		assertEquals("Youcef Bennour", userOne.getUserName());
	}
	
	@Test
	public void testGetUserType(){
		assertEquals(typeOne, userOne.getType());
	}
	
	@Test
	public void testBlackballed(){
		assertEquals(blackballed, userOne.isBlackballed());
	}
	
	@Test
	public void testFlagged(){
		assertEquals(flagged, userOne.isFlagged()); 
	}
}
