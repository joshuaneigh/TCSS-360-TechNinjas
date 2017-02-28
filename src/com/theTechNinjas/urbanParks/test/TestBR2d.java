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
 * Tests Business Rule d of User Story #2
 * @rule There can be no more than the maximum number 
 * of jobs scheduled on any given day, default of 4.
 *
 * @author Jasmine Dacones | jazzyd25@uw.edu
 * @version 27 Feb 2017
 */
public class TestBR2d {

	private static final String VOLUNTEER_ONE = "jocelynRider";
	
	private static final String parkOne = "Wright Park";
	private static final String parkTwo = "Point Defiance Park";
	
	private static final String jobOne = "Clean Up the Park\tRake the leaves.\t2017-03-25 05:00\t2017-03-25 06:00";
	private static final String jobTwo = "Gardening Day\tPlant new flowers and trees in the new garden\t2017-03-25 12:00\t2017-03-25 13:00";
	private static final String jobThree = "Trash Day\tPick up trash\t2017-03-25 15:00\t2017-03-25 16:00";
	private static final String jobFour = "Boats Festival\tHelp with the sign in's\t2017-03-25 09:00\t2017-03-25 16:00";
	private static final String jobFive = "Tree Removal\tThere are limbs in the park which pose a safety hazard. The trees are to be removed.\t2017-03-25 09:00\t2017-03-25 16:00";
	private static final String jobSix = "Mow Lawns\tThe main lawn needs to be mowed.\t2017-03-25 09:00\t2017-03-25 18:00";



	private static final Path DATA_PATH = Paths.get("./data/data.ser");
	private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {    
		//create backup file if data exists
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
	public void testMaximumJobsInOneDay_MaxNotReached_ScheduleConflictException()
			throws ScheduleConflictException {
		Controller.addJob(parkTwo, jobSix);
	}
	

	@Test(expected = ScheduleConflictException.class)
	public void testMaximumJobsInOneDay_MaxReached_ScheduleConflictException() 
			throws ScheduleConflictException {
		Controller.addJob(parkOne, jobFour);
		Controller.addJob(parkTwo, jobFive);
	}
}

