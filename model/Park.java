/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author John Bako
 *
 */
public class Park implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8924424660909193303L;

	static Location parkLocation;
	
	private static String parkName;
	
	private static boolean hasAjob;
	
	private static int numOfJobs; 
	
	public Park() {
		this(parkLocation, parkName, hasAjob, numOfJobs);
	}

	public Park(Location theParkLocation, String theParkName, boolean theHasAjob, int theNumOfJobs) {
		parkLocation = theParkLocation;
		parkName = theParkName;
		hasAjob = theHasAjob;
		numOfJobs = theNumOfJobs;
	}
	
	public Location getParkLocation() {
		return parkLocation;
	}
	
	public String getParkName() {
		return parkName;
	}
	
	public boolean getHasAjob() {
		return hasAjob;
	}
	
	public int getNumOfJobs() {
		return numOfJobs;
	}
	
	public void setParkLocation(Location theParkLocation) {
		parkLocation = theParkLocation;
	}
	
	public void setParkName(String theParkName) {
		parkName = theParkName;
	}
	
	public void setHasAjob(boolean theHasAjob) {
		hasAjob = theHasAjob;
	}
	
	public void setNumOfJobs(int theNumOfJobs) {
		numOfJobs = theNumOfJobs;
	}
	
	public String toString() {
		return "Park Name: " + parkName + "\t" + "Number of Jobs: " + numOfJobs;
	}
}
