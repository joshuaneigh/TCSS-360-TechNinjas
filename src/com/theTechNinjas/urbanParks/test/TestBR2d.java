package com.theTechNinjas.urbanParks.test;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.DataStore;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;
public class TestBR2d {

	private static final String VOLUNTEER_ONE = "jocelynRider";
	private static final String SERIALIZED_PATH = "./data/data2.ser";
	private static final String parkOne = "Wright Park";
	private static final String parkTwo = "Point Defiance Park";
	private static final String jobOne = "Clean Up the Park\tRake the leaves.\t2017-03-25 05:00\t2017-03-25 06:00";
	private static final String jobTwo = "Gardening Day\tPlant new flowers and trees in the new garden\t2017-03-25 12:00\t2017-03-25 13:00";
	private static final String jobThree = "Trash Day\tPick up trash\t2017-03-25 15:00\t2017-03-25 16:00";
	private static final String jobFour = "Boats Festival\tHelp with the sign in's\t2017-03-25 09:00\t2017-03-25 16:00";
	private static final String jobFive = "Tree Removal\tThere are limbs in the park which pose a safety hazard. The trees are to be removed.\t2017-03-25 09:00\t2017-03-25 16:00";


	private static final Path DATA_PATH = Paths.get("./data/data.ser");
	private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {    
		//create backup file if data exists
		if (DATA_PATH.toFile().isFile()) {
			Files.copy(DATA_PATH, BACKUP_PATH);
		}
		
		Controller.addUser("jocelynRider", "Volunteer");
		Controller.addPark(parkOne);
		Controller.addPark(parkTwo);
		Controller.addJob(parkOne, jobOne);
		Controller.addJob(parkTwo, jobTwo);
		Controller.addJob(parkTwo, jobThree);
		Controller.addJob(parkOne, jobFour);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {       
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
	public void testMaximumJobsInOneDay_MaxReached() throws ScheduleConflictException {
		Controller.addJob(parkTwo, jobFive);
	}
	
	@Test(expected = ScheduleConflictException.class)
	public void testMaximumJobsInOneDay_MaxNotReached()throws ScheduleConflictException {
		Controller.addJob(parkTwo, jobFive);
	}
}

