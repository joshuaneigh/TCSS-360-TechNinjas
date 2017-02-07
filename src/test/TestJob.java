/**
 * Tests the Job class.
 */
package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Job;
import model.Location;
import model.Park;
import model.UserType;


/**
 * @author Jasmine Dacones
 *
 */
public class TestJob {
	
	private static Job jobOne;
	private static Job jobTwo;
	private static UserType volunteerOne, volunteerTwo;
	private static final Park PD_PARK = new Park(new Location(98407), "Point Defiance Park");
	private static final Park EMPTY_PARK = new Park(new Location(98407), "");

	@BeforeClass
	public static void setUp() {
		jobOne = new Job("Rake the leaves.", PD_PARK);	
		jobTwo = new Job("Rake the leaves.", EMPTY_PARK);
		volunteerOne = UserType.Volunteer;	
		volunteerTwo = UserType.Volunteer;	
		jobOne.addVolunteer(volunteerOne);
		jobOne.addVolunteer(volunteerTwo);
	}
	
	@Test
	public void testAddVolunteer_OneVolunteer() {
		assertEquals(UserType.Volunteer, jobOne.getVolunteers().get(0));
	}
	
	@Test
	public void testAddVolunteer_MultipleVolunteers() {
		assertEquals(UserType.Volunteer, jobOne.getVolunteers().get(0));
		assertEquals(UserType.Volunteer, jobOne.getVolunteers().get(1));
	}
	
	@Test
	public void testToString_AllInfoGiven() {
		assertEquals("Description: Rake the leaves.\nPark: Point Defiance Park", jobOne.toString());
	}
	
	@Test
	public void testToString_MissingInfo() {
		assertEquals("Description: Rake the leaves.\nPark: ", jobTwo.toString());
	}
}