package com.theTechNinjas.urbanParks.test;

import com.theTechNinjas.urbanParks.model.exception.NoSuchParkException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.Controller;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the methods in Controller not related to any specific business rule.
 * @author Michael Loundagin
 */
public class ControllerTest {
    
    private static final Path DATA_PATH = Paths.get("./data/data.ser");
    
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
    
    private static final String NONEXISTENT_PARK = "This is a park name that likely will never exist.";
    
    private static final String NONEXISTENT_USER = "This is a username that likely will never exist.";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        //create backup file if data exists
        if (DATA_PATH.toFile().isFile()) {
            Files.copy(DATA_PATH, BACKUP_PATH);
        }
        
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

    @Test (expected = NullPointerException.class)
    public void getParkJobs_parkNameIsNull_NullPointerExceptionThrown() {
        
        Controller.getParkJobs(null);
        
    }
    
    @Test (expected = NoSuchParkException.class)
    public void getParkJobs_parkDoesNotExist_NoSuchParkExceptionThrown() {
        
        Controller.getParkJobs(NONEXISTENT_PARK);
        
    }
    
    @Test (expected = NullPointerException.class)
    public void getVolunteerJobs_usernameIsNull_NullPointerExceptionThrown() {
        
        Controller.getVolunteerJobs(null);
        
    }
    
    @Test (expected = NoSuchUserException.class)
    public void getParkJobs_userDoesNotExist_NoSuchUserExceptionThrown() {
        
        Controller.getVolunteerJobs(NONEXISTENT_USER);
        
    }
    
    @Test (expected = NullPointerException.class)
    public void isUser_usernameIsNull_NullPointerExceptionThrown() {
        
        Controller.isUser(null);
        
    }
    
    @Test
    public void isUser_userDoesNotExist_falseReturned() {
        
        assertFalse(Controller.isUser(NONEXISTENT_USER));
        
    }

}
