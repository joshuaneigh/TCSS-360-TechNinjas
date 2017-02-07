/**
 * 
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
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
	private static Location location = new Location("5472", "Tacoma", "WA");

	public static void main(String[] args) {
		jobOne = new Job("Rake the leaves.", new Park(location, "Point Defiance Park"));	
		volunteerOne = UserType.Volunteer;	
	}
	
	@Test
	public void testAddVolunteer() {
		jobOne.addVolunteer(volunteerOne);
		assertEquals(UserType.Volunteer, jobOne.getVolunteers().get(0));
	}	
}