/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author John Bako
 *
 */
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6562744950368502216L;
	
	private static String locName;
	
	private static String city;
	
    private static String state;
    
    private static int zipcode; 
    
    public Location() {
    	this(locName, city, state, zipcode);
    }

	public Location(String theLocName, String theCity, String theState, int theZipcode) {
		locName = theLocName;
		city = theCity;
		state = theState;
		zipcode = theZipcode;
	}
	
	public String getLocName() {
		return locName;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public int getZipcode() {
		return zipcode;
	}
	
	public void setLocName(String theLocName) {
		locName = theLocName;
	}
	
	public void setCity(String theCity) {
		city = theCity;
	}
	
	public void steState(String theState) {
		state = theState;
	}
	
	public void setZipcode(int theZipcode) {
		zipcode = theZipcode;
	}
	
	// returns string representation of this location
    public String toString() {
        return locName + ", " + city + ", " + state + ", " + zipcode;
    }

}
