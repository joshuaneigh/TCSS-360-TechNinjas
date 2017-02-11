package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
/*
 * @author Youcef Bennour
 */
public class TestUser {
	private static User userOne, userTwo, userThree, userFour, userFive;
	private static UserType typeOne = UserType.Volunteer, typeTwo = UserType.UrbanPark, typeThree = UserType.ParkManager;
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
		assertEquals("Name : Youcef Bennour\nType : Volunteer\nBlackballed : false\nFlagged : false", userOne.toString());
		assertEquals("Name : Ezra Miller\nType : UrbanPark\nBlackballed : false\nFlagged : false", userTwo.toString());
		assertEquals("Name : Melissa\nType : ParkManager\nBlackballed : false\nFlagged : false", userThree.toString());
	}
	
	@Test
	public void testToStringWithNoName(){
		assertEquals("Name : \nType : Volunteer\nBlackballed : false\nFlagged : false", userFour.toString());
	}

	@Test
	public void testToStringWithNoType(){
		assertEquals("Name : Youcef\nType : null\nBlackballed : false\nFlagged : false", userFive.toString());
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
