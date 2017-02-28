package com.theTechNinjas.urbanParks.test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests Business Rule a of User Story #1
 * @rule A volunteer cannot sign up for more than one job on any given day. 
 *
 * @author Jasmine Dacones | jazzyd25@uw.edu
 * @version 27 Feb 2017
 */
public class TestBR1a {

	private static final String VOLUNTEER_ONE = "jocelynRider";
	private static final String parkOne = "Wright Park";
	private static final String parkTwo = "Point Defiance Park";
	private static final String jobOne = "Clean Up the Park\tRake the leaves.\t2017-03-25 05:00\t2017-03-25 06:00";
	private static final String jobTwo = "Gardening Day\tPlant new flowers and trees in the new garden\t2017-03-27 12:00\t2017-03-27 13:00";
	private static final String jobThree = "Trash Day\tPick up trash\t2017-03-25 05:00\t2017-03-25 06:00";


    private static final Path DATA_PATH = Paths.get("./data/data.ser");
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {    
        // create backup file if data exists
        if (DATA_PATH.toFile().isFile()) {
            Files.copy(DATA_PATH, BACKUP_PATH);
        }
        
		Controller.addUser(VOLUNTEER_ONE, "Volunteer");
		Controller.addPark(parkOne);
		Controller.addPark(parkTwo);
		Controller.addJob(parkOne, jobOne);
		Controller.addJob(parkTwo, jobTwo);
		Controller.addJob(parkTwo, jobThree);
    }

    @AfterClass
    public static void tearDownAfterClass() throws IOException {       
        // if backup file was created, overwrite data file to its original state.
        if (BACKUP_PATH.toFile().isFile()) {
            Files.copy(BACKUP_PATH, DATA_PATH);
            Files.delete(BACKUP_PATH);
        } // if it wasn't, delete new data file if it exists.
        else {
            Files.deleteIfExists(DATA_PATH);
        }            

    }
    
	@Test(expected = ScheduleConflictException.class)
	public void testVolunteerSignUpForMoreThanOneJobOnSameDay_ScheduleConflictException() 
			throws ScheduleConflictException {
		Controller.volunteerJob(VOLUNTEER_ONE, parkOne, jobOne);
		Controller.volunteerJob(VOLUNTEER_ONE, parkOne, jobThree);
	}
}
