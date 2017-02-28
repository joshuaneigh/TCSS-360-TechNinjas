package com.theTechNinjas.urbanParks.test;

import org.junit.Before;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;


public class PendingJobTest {
  //BR: Not more than the maximum number of pending jobs at a time, default of 20.

	
	@Before
	public void setUp() throws Exception {
		String jobOne = "Visitor Center Volunteers\tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Jarrell Cove State Park");
		Controller.addJob("Jarrell Cove State Park", jobOne);
		
		String jobTwo = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Joemma Beach State Park");
		Controller.addJob("Joemma Beach State Park", jobTwo);
		
		String jobThree = "Park Operations\tIn charge of helping out with the park operations\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Ashford County Park");
		Controller.addJob("Ashford County Park", jobThree);
		
		String jobFour = "Park Equipment Maintenance\tIn charge of maintaining the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Bresemann Forest");
		Controller.addJob("Bresemann Forest", jobFour);
		
		String jobFive = "Park Administration\tHelp adminstritate the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Chambers Creek Regional Park");
		Controller.addJob("Chambers Creek Regional Park", jobFive);
		
		String jobSix = "Natural Resource Protection\tProtect the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Dawson Playfield");
		Controller.addJob("Dawson Playfield", jobSix);
		
		String jobSeven = "Cultural Resource Protection\tProtect the park cultural sides\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Frontier Park");
		Controller.addJob("Frontier Park", jobSeven);
		
		String jobEight = "Greeter\tGreete the public that comes into the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Gonyea Play Field");
		Controller.addJob("Gonyea Play Field", jobEight);
		
		String jobNine = "Interpretive Assistant\tTell the public about the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Heritage Recreation Center");
		Controller.addJob("Heritage Recreation Center", jobNine);
		
		String jobTen = "Interpretive Guide\tGuide and interprete the different places\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Lake Tapps Park");
		Controller.addJob("Lake Tapps Park", jobTen);
		
		String jobEleven = "Historical Assistant\tTell the visitors about the history of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Lakewood Community Center");
		Controller.addJob("Lakewood Community Center", jobEleven);
		
		String jobTwelve = "Maintenance Assistant\tProtect the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Mayfair Playfield");
		Controller.addJob("Mayfair Playfield", jobTwelve);
		
		String jobThirteen = "Cleaning the park\tClean the park and make sure it's ready\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Meridian Habitat Park & Community Center");
		Controller.addJob("Meridian Habitat Park & Community Center", jobThirteen);
		
		String jobFourteen = "Park Library Assistant\tMake sure that the library of the \t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Parkland Prairie");
		Controller.addJob("Parkland Prairie", jobFourteen);
		
		String jobFiften = "Clerical Assistant\tAssist the clerk\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Purdy Sand Spit");
		Controller.addJob("Purdy Sand Spit", jobFiften);
		
		String jobSixteen = "Park Photographer\tTakes picture of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Riverside Park & BMX");
		Controller.addJob("Riverside Park & BMX", jobSixteen);
		
		String jobSeventeen = "Maintenance Caretaker\tTake care of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Seeley Lake Park");
		Controller.addJob("Seeley Lake Park", jobSeventeen);
		
		String jobEighteen = "Maintenance Preservation Assistant\tHelp with the preservation of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("South Hill Community Park");
		Controller.addJob("South Hill Community Park", jobEighteen);
		
		String jobNinteen = "HISTORIC GARDEN VOLUNTEER\tHelp maintain the history of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Spanaway Park");
		Controller.addJob("Spanaway Park", jobNinteen);
		
		
	}

	
	@Test (expected = ScheduleConflictException.class) 
	public void addJob_checkWhetherTheNumberOfJobsExceedsMaximum() throws ScheduleConflictException{
		String jobTwenty = "Maintenance Volunteer\tMaintain the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Mount Rainier Trail");
		Controller.addJob("Mount Rainier Trail", jobTwenty);
		
		String jobTwentyOne = "Park Host \tHost the visitors of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Lower Granite Lock & Dam-Park Host Swallow Park");
		Controller.addJob("Lower Granite Lock & Dam-Park Host Swallow Park", jobTwentyOne);
		
		Controller.addPark("Lower");
		Controller.addJob("Lower", jobTwentyOne);
	}
	
	@Test 
	public void addJob_checkWhetherTheNumberOfJobsEqualMaximum() throws ScheduleConflictException{
		String jobNinteen = "HISTORIC GARDEN VOLUNTEER\tHelp maintain the history of the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Bellevue Park");
		Controller.addJob("Bellevue Park", jobNinteen);
		
		String jobTwenty = "Maintenance Volunteer\tMaintain the park\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Kent Trail");
		Controller.addJob("Kent Trail", jobTwenty);

	}
	
	@Test 
	public void checkWhetherTheNumberOfJobsLessThanMaximum() throws ScheduleConflictException{
		String jobOne = "Visitor Center Volunteers\tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Dash Point State Park");
		Controller.addJob("Dash Point State Park", jobOne);
	}

}
