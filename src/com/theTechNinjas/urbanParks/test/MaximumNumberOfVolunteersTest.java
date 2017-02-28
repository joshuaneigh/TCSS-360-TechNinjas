package com.theTechNinjas.urbanParks.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests Business Rule a of User Story #2
 * @rule Not more than the maximum number of volunteers for any job, default of 10.
 *
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 26 Feb 2017
 */
public class MaximumNumberOfVolunteersTest {
	
	private static final Path DATA_PATH = Paths.get("./data/data.ser");
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
	
	String jobOne;
	
	@Before
	public void setUp() throws ScheduleConflictException, IOException  {
		if (DATA_PATH.toFile().isFile()) {
            Files.copy(DATA_PATH, BACKUP_PATH);
        }
		
		String volunteerOne = "Youcef Bennour";
		String volunteerTwo = "Joshua Neigh";
		String volunteerThree = "Yacine Lopez";
		String volunteerFour = "Ikram Neals";
		String volunteerFive = "Ben Smith";
		String volunteerSix = "Charles Brian";
		String volunteerSeven = "Xiung Tan";
		String volunteerEight = "Mohamed Ismael";
		jobOne = "Visitor Center Volunteer \tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-25 13:00";
		
		Controller.addPark("Jarrell Cove State Park");
		
		Controller.addJob("Jarrell Cove State Park", jobOne);
		
		Controller.addUser(volunteerOne, "Volunteer");
		Controller.addUser(volunteerTwo, "Volunteer");
		Controller.addUser(volunteerThree, "Volunteer");
		Controller.addUser(volunteerFour, "Volunteer");
		Controller.addUser(volunteerFive, "Volunteer");
		Controller.addUser(volunteerSix, "Volunteer");
		Controller.addUser(volunteerSeven, "Volunteer");
		Controller.addUser(volunteerEight, "Volunteer");

		
		Controller.volunteerJob(volunteerOne,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTwo,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerThree,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerFour,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerFive,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerSix,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerSeven,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerEight,"Jarrell Cove State Park", jobOne);
		
	}
	
	@After
	public void tearDown() throws IOException {
		Controller.reset();
    	// if backup file was created, overwrite data file to its original state.
    	if (BACKUP_PATH.toFile().isFile()) {
            Files.copy(BACKUP_PATH, DATA_PATH);
            Files.delete(BACKUP_PATH);
        } // if it wasn't, delete new data file if it exists.
        else {
            Files.deleteIfExists(DATA_PATH);
        } 
	}
		
	@Test (expected = NullPointerException.class)
	public void volunteerJob_checkIfTheVolunteerExist() throws ScheduleConflictException{
		String volunteerOne = null;
		Controller.addUser(volunteerOne, "Volunteer");
		Controller.volunteerJob(volunteerOne,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void volunteerJob_checkIfNumberOfVolunteersLessThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Alvaro Garcia";
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
	}
	
	@Test
	public void volunteerJob_checkIfNumberOfVolunteersEqualMaximum() throws ScheduleConflictException{
		String volunteerNine = "Melania Trump";
		String volunteerTen = "Donald Trump";
		
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.addUser(volunteerTen,"Volunteer");
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
	}
	
	@Test (expected = ScheduleConflictException.class) 
	public void checkIfVolunteersGreaterThanMaximum() throws ScheduleConflictException{
		String volunteerNine = "Barack Obama";
		String volunteerTen = "Bria Hanson";
		String volunteerEleven = "Joy Liz";
		
		
		Controller.addUser(volunteerNine, "Volunteer");
		Controller.addUser(volunteerTen, "Volunteer");
		Controller.addUser(volunteerEleven, "Volunteer");
		
		Controller.volunteerJob(volunteerNine,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerTen,"Jarrell Cove State Park", jobOne);
		Controller.volunteerJob(volunteerEleven,"Jarrell Cove State Park", jobOne);	
	}

}
