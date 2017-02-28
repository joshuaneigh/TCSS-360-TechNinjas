package com.theTechNinjas.urbanParks.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests Business Rule b of User Story #1
 * @rule A volunteer may sign up only if the job is at least a minimum 
 * number of calendar days from the current date, default of 2.
 *
 * @author Jasmine Dacones | jazzyd25@uw.edu
 * @version 27 Feb 2017
 */
public class TestBR1b {
	
    private static final Path DATA_PATH = Paths.get("./data/data.ser");
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
    
	private static final String VOLUNTEER_ONE = "jocelynRider";
	private static final String parkOne = "Dukes Park";
	private static final String parkTwo = "Homestead Park";
	private static final String jobOne = "Clean Up the Park\tRake the leaves.\t2017-03-25 05:00\t2017-03-25 06:00";
	private static final String jobTwo = "Gardening Day\tPlant new flowers and trees in the new garden\t2017-03-27 12:00\t2017-03-27 13:00";
	private static final String jobThree = "Trash Day\tPick up trash\t2017-02-28 05:00\t2017-02-28 06:00";

	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        //create backup file if data exists
        if (DATA_PATH.toFile().isFile()) {
            Files.copy(DATA_PATH, BACKUP_PATH);
        }
        
        Controller.login("admin");
		Controller.addUser("jocelynRider", "Volunteer");
		Controller.addPark(parkOne);
		Controller.addPark(parkTwo);
		Controller.addJob(parkOne, jobOne);
		Controller.addJob(parkTwo, jobTwo);
		Controller.addJob(parkTwo, jobThree);
		Controller.logout();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    	Controller.reset();
    	
    	//if backup file was created, overwrite data file to its original state.
        if (BACKUP_PATH.toFile().isFile()) {
            Files.copy(BACKUP_PATH, DATA_PATH);
            Files.delete(BACKUP_PATH);
        } // if it wasn't, delete new data file if it exists.
        else {
            Files.deleteIfExists(DATA_PATH);
        }    
    }
    
    @Test(expected = ScheduleConflictException.class)
    public void testMinimumNumberOfDaysToSignUp_ScheduleConflictException() throws ScheduleConflictException {
    	Controller.volunteerJob(VOLUNTEER_ONE, parkTwo, jobThree);
    }  
}
