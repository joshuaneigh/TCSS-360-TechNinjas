package com.theTechNinjas.urbanParks.test;

import com.theTechNinjas.urbanParks.model.exception.NoSuchParkException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.Controller;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests the methods in Controller not related to any specific business rule.
 */
public class ControllerGettersTest {
    
    private static final Path DATA_PATH = Paths.get("./data/data.ser");
    
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
    
    private static final String NONEXISTENT_PARK = "This is a park name that likely will never exist.";
    
    private static final String NONEXISTENT_USER = "This is a username that likely will never exist.";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	Controller.reset();
    	//create backup file if data exists
        if (DATA_PATH.toFile().isFile()) {
            Files.copy(DATA_PATH, BACKUP_PATH, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(DATA_PATH);
        }
        
        Controller.login("admin");
        
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    	
        //if backup file was created, overwrite data file to its original state.
        if (BACKUP_PATH.toFile().isFile()) {
            Files.copy(BACKUP_PATH, DATA_PATH, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(BACKUP_PATH);
        } // if it wasn't, delete new data file if it exists.
        else {
            Files.deleteIfExists(DATA_PATH);
        }
        
    }

    /**
     * @author Michael Loundagin
     */
    @Test (expected = NullPointerException.class)
    public void getParkJobs_parkNameIsNull_nullPointerExceptionThrown() {
        
        Controller.getParkJobs(null);
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test (expected = NoSuchParkException.class)
    public void getParkJobs_parkForParkNameDoesNotExist_noSuchParkExceptionThrown() {
        
        Controller.getParkJobs(NONEXISTENT_PARK);
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test
    public void getVolunteerJobs_userForUsernameExistsButUserHasNotSignedUpForAnyJob_emptyListReturned() {
        
        final String usernameWithNoJobs = "dreemc";
        Controller.addUser(usernameWithNoJobs, "Volunteer");
        assertEquals(new ArrayList<String>(), Controller.getVolunteerJobs(usernameWithNoJobs));
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test (expected = NullPointerException.class)
    public void getVolunteerJobs_usernameIsNull_nullPointerExceptionThrown() {
        
        Controller.getVolunteerJobs(null);
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test (expected = NoSuchUserException.class)
    public void getParkJobs_userForUsernameDoesNotExist_noSuchUserExceptionThrown() {
        
        Controller.getVolunteerJobs(NONEXISTENT_USER);
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test (expected = NullPointerException.class)
    public void isUser_usernameIsNull_nullPointerExceptionThrown() {
        
        Controller.isUser(null);
        
    }
    
    /**
     * @author Michael Loundagin
     */
    @Test
    public void isUser_userForUsernameDoesNotExist_falseReturned() {
        
        assertFalse(Controller.isUser(NONEXISTENT_USER));
        
    }

}