package model;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * A park with a name, a location, and jobs.
 * @author John Bako, Michael Loundagin
 */
public class Park implements Serializable {
    
    /**
     * The serial for this class.
     */
    private static final long serialVersionUID = 8924424660909193303L;
    
    /**
     * The location of this park.
     */
    private Location parkLocation;
    
    /**
     * The name of this park.
     */
    private String parkName;
    
    /**
     * The pending and past jobs for this park.
     */
    private ArrayList<Job> jobs = new ArrayList<Job>();
    
    /**
     * Creates a park with the given location and name. The list of jobs is empty.
     * @param theParkLocation The location to give this park
     * @param theParkName The name to give this park
     */
    public Park(final Location theParkLocation, final String theParkName) {
        parkLocation = theParkLocation;
        parkName = theParkName;
    }
    
    /**
     * Gets the location of this park.
     * @return The location of this park
     */
    public Location getParkLocation() {
        return parkLocation;
    }
    
    /**
     * Gets the name of this park.
     * @return the name of this park
     */
    public String getParkName() {
        return parkName;
    }
    
    /**
     * Checks if this park has any jobs in its list.
     * @return If there are jobs in the list
     */
    public boolean hasJob() {
        return !(jobs.isEmpty());
    }
    
    /**
     * Gets the number of jobs in this park's list.
     * The number of jobs in the list
     */
    public int getNumOfJobs() {
        return jobs.size();
    }
    
    /**
     * Sets the location of this park.
     * @param theParkLocation The location to give this park
     */
    public void setParkLocation(final Location theParkLocation) {
        parkLocation = theParkLocation;
    }
    
    /**
     * Sets the name of this park.
     * @param theParkName The name to give this park
     */
    public void setParkName(final String theParkName) {
        parkName = theParkName;
    }
    
    /**
     * Adds a job to this park's list if the maximum number of pending jobs allowed across all parks has not been reached.
     * @param theJob The job to add to the list
     * @throw JobException If the number of pending jobs across all parks has reached the maximum allowed.
     */
    public void addJob(final Job theJob) {
        if (Controller.numJobs < Controller.maxJobs) {
            jobs.add(theJob);
        } else {
            throw new JobException("Maximum pending job limit reached.");
        }
    }
    
}
