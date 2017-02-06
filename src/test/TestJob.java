/**
 * 
 */
package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Jasmine Dacones
 *
 */
public class TestJob {

	public static void main(String[] args) {
		Job jobOne = new Job();	
		UserType volunteer1 = new UserType("Alyssa");	
	}
	
	@Test
	public void testAddVolunteer() {
		assertTrue(jobOne.addVolunteer(volunteer1), getVolunteers().get(0));
	}	
}