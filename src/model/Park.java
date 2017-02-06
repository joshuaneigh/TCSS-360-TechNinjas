package model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import model.exception.JobAlreadyAddedException;
import model.exception.MaximumJobsException;

/**
 * A park with a name, a location, and jobs.
 * @author John Bako
 */
public class Park implements Serializable {

    /**
     * The serial for this class.
     */
    private static final long serialVersionUID = 8924424660909193303L;

    private static int NUM_JOBS;
    private static int MAX_JOBS;
    
    /**
     * The location of this park.
     */
    private final Location parkLocation;

    /**
     * The name of this park.
     */
    private final String parkName;

    /**
     * The pending and past jobs for this park.
     */
    private final List<Job> jobs;
    
    /**
     * Creates a park with the given location and name.
     * The list of jobs is empty.
     * @param theParkLocation The location to give this park
     * @param theParkName The name to give this park
     */
    public Park(final Location theParkLocation, final String theParkName) {
        parkLocation = theParkLocation;
        parkName = theParkName;
        jobs = new ArrayList<>();
        
        if (MAX_JOBS == 0) {
        	MAX_JOBS = 30;
        	NUM_JOBS = 0;
        }
    }

    /**
     * Gets the location of this park.
     * @return The location of this park
     */
    public Location getLocation() {
        return parkLocation;
    }

    /**
     * Gets the name of this park.
     * @return the name of this park
     */
    public String getName() {
        return parkName;
    }

    /**
     * Gets the number of jobs in this park's list.
     * The number of jobs in the list
     */
    public int getNumOfJobs() {
        return jobs.size();
    }

    /**
     * Adds a job to this park's list if the maximum number of pending jobs
     * allowed across all parks has not been reached.
     * @param theJob The job to add to the list
     * @throws MaximumJobsException 
     * @throws JobAlreadyAddedException 
     */
    public void addJob(final Job theJob) throws MaximumJobsException, JobAlreadyAddedException {
        if (NUM_JOBS >= MAX_JOBS) {
            throw new MaximumJobsException("The maximum number of pending jobs has been reached.");
        } else if (jobs.contains(theJob)) {
        	throw new JobAlreadyAddedException("The job has already been added.");
        } else {
        	jobs.add(theJob);
        }
    }

    public String toString() {
        return "Name: " + parkName + "\tLocation: " + parkLocation
               + "\tNumber of Jobs: " + jobs.size();
    }
}
