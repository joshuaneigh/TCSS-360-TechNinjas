/**
 * 
 */
package test;

import org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Before;
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
	private static UserType volunteerOne;
	private static final Park PD_PARK = new Park(new Location(98407), "Point Defiance Park");

	@BeforeClass
	public static void setUp() {
		jobOne = new Job("Rake the leaves.", PD_PARK);	
		volunteerOne = UserType.Volunteer;	
		jobOne.addVolunteer(volunteerOne);
	}
	
	@Test
	public void testAddVolunteer() {
		assertEquals(UserType.Volunteer, jobOne.getVolunteers().get(0));
	}
	
	@Test
	public void testToString() {
		assertEquals("Description: Rake the leaves.\nPark: Point Defiance Park", jobOne.toString());
	}
}