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
    
    public Location(final int zipcode) {
    	
    }
    
    public Location(final String address, final String city, final String state) {
    	
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
	
	// returns string representation of this location
	@Override
    public String toString() {
        return locName + ", " + city + ", " + state + ", " + zipcode;
    }

}
