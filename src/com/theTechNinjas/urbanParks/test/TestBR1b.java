package com.theTechNinjas.urbanParks.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestBR1b {
	
    private static final Path DATA_PATH = Paths.get("./data/data.ser");
    private static final Path BACKUP_PATH = Paths.get("./data/backup.ser");
	
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
    

}
