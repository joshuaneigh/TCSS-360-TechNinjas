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
    private Location myParkLocation;

    /**
     * The name of this park.
     */
    private String myParkName;

    /**
     * The pending and past jobs for this park.
     */
    private ArrayList<Job> myJobs = new ArrayList<Job>();

    /**
     * Creates a park with the given location and name.
     * The list of jobs is empty.
     * @param theParkLocation The location to give this park
     * @param theParkName The name to give this park
     */
    public Park(final Location theParkLocation, final String theParkName) {
        myParkLocation = theParkLocation;
        myParkName = theParkName;
    }

    /**
     * Gets the location of this park.
     * @return The location of this park
     */
    public Location getParkLocation() {
        return myParkLocation;
    }

    /**
     * Gets the name of this park.
     * @return the name of this park
     */
    public String getParkName() {
        return myParkName;
    }

    /**
     * Checks if this park has any jobs in its list.
     * @return If there are jobs in the list
     */
    public boolean hasJob() {
        return !(myJobs.isEmpty());
    }

    /**
     * Gets the number of jobs in this park's list.
     * The number of jobs in the list
     */
    public int getNumOfJobs() {
        return myJobs.size();
    }

    /**
     * Sets the location of this park.
     * @param theParkLocation The location to give this park
     */
    public void setParkLocation(final Location theParkLocation) {
        myParkLocation = theParkLocation;
    }

    /**
     * Sets the name of this park.
     * @param theParkName The name to give this park
     */
    public void setParkName(final String theParkName) {
        myParkName = theParkName;
    }
    
    /**
     * Gets the list of jobs for this park.
     * If the park is for a specific user, the jobs are only jobs
     * associated with that user.
     * @return The list of jobs for this park
     */
    public ArrayList<Job> getJobs() {
        return myJobs;
    }

    /**
     * Adds a job to this park's list.
     * @param theJob The job to add to the list
     * @throw JobException If the number of pending jobs across all parks
     *                     has reached the maximum allowed.
     */
    public void addJob(final Job theJob) {
        if (Controller.getNumJobs() < Controller.getMaxJobs()) {
            myJobs.add(theJob);
        } else {
            throw new JobException("Maximum pending job limit reached.");
        }
    }

    public String toString() {
        return "Name: " + myParkName + "\tLocation: " + myParkLocation
               + "\tNumber of Jobs: " + getNumOfJobs();
    }
}
