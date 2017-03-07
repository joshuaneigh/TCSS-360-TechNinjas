package com.theTechNinjas.urbanParks.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

/**
 * Tests Business Rule a of User Story #2
 * @rule A job cannot be longer than the maximum number of days, default of 3.
 *
 * @author Youcef Bennour | ybennour@uw.edu
 * @version 26 Feb 2017
 */
public class MaximumNumberOfDaysTest {
    
        private static final Path DATA_PATH = Paths.get("./data/data.ser");
    
        private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
	
    	@BeforeClass
    	public static void setUp() throws ScheduleConflictException, IOException  {
        	if (DATA_PATH.toFile().isFile()) {
            		Files.copy(DATA_PATH, BACKUP_PATH, StandardCopyOption.REPLACE_EXISTING);
                    Files.delete(DATA_PATH);
        	}
        	Controller.reset();
    	}
    
    	@AfterClass
    	public static void tearDown() throws IOException {
        	// if backup file was created, overwrite data file to its original state.
        	if (BACKUP_PATH.toFile().isFile()) {
            		Files.copy(BACKUP_PATH, DATA_PATH, StandardCopyOption.REPLACE_EXISTING);
            		Files.delete(BACKUP_PATH);
        	} // if it wasn't, delete new data file if it exists.
        	else {
            		Files.deleteIfExists(DATA_PATH);
        	}
    	}
	
	@Test (expected = ScheduleConflictException.class) 
	public void addJob_checkWhetherTheNumberOfDaysExceedsMaximum() throws ScheduleConflictException{
		String jobOne = "Visitor Center Volunteers\tSign Up the volunteers at the visitor Center\t2017-03-25 12:00\t2017-03-29 13:00";
		Controller.addPark("Jarrell Cove State Park");
		Controller.addJob("Jarrell Cove State Park", jobOne);
	}
	
	public void addJob_checkWhetherTheNumberOfDaysEqualsMaximum() throws IllegalFormatException, ScheduleConflictException{
		String jobTwo = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-27 13:00";
		Controller.addPark("Joemma Park");
		Controller.addJob("Joemma Park", jobTwo);
		
	}
	
	public void addJob_checkWhetherTheNumberOfDaysLessThanMaximum() throws IllegalFormatException, ScheduleConflictException{
		String jobThree = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-25 13:00";
		Controller.addPark("Beach Park");
		Controller.addJob("Beach Park", jobThree);
		
	}
	
	@Test (expected = IllegalFormatException.class) 
	public void addJob_checkWhetherTheNumberOfDaysLessThanTheCurrentDay() throws IllegalFormatException, ScheduleConflictException{
		String jobFour = "Public Safety\tWatch over the public just in case\t2017-03-25 12:00\t2017-03-24 13:00";
		Controller.addPark("Park");
		Controller.addJob("Park", jobFour);
		
	}
	
}
