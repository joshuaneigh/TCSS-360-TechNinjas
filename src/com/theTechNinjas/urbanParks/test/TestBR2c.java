package com.theTechNinjas.urbanParks.test;

import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 26 Feb 2017
 */
public class MaximumNumberOfDays {
	//BR: A job cannot be longer than the maximum number of days, default of 3.

	
	@Test (expected = ScheduleConflictException.class) 
	public void checkWhetherTheNumberOfDaysExceedsMaximum() throws ScheduleConflictException{
		String jobOne = "Visitor Center Volunteers\tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-29 13:00";
		Controller.addPark("Jarrell Cove State Park");
		Controller.addJob("Jarrell Cove State Park", jobOne);
	}
	
	public void checkWhetherTheNumberOfDaysEqualsMaximum() throws IllegalFormatException, ScheduleConflictException{
		String jobTwo = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-27 13:00";
		Controller.addPark("Joemma Park");
		Controller.addJob("Joemma Park", jobTwo);
		
	}
	
	public void checkWhetherTheNumberOfDaysLessThanMaximum() throws IllegalFormatException, ScheduleConflictException{
		String jobThree = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Beach Park");
		Controller.addJob("Beach Park", jobThree);
		
	}
	
	@Test (expected = IllegalFormatException.class) 
	public void checkWhetherTheNumberOfDaysLessThanTheCurrentDay() throws IllegalFormatException, ScheduleConflictException{
		String jobFour = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-24 13:00";
		Controller.addPark("Park");
		Controller.addJob("Park", jobFour);
		
	}
	
}
