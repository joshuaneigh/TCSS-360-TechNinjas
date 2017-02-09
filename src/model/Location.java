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
	
	private static String address;
    
    public Location(final int theZipcode) {
    	zipcode = theZipcode;
    }
    
    public Location(final String theAddress, final String theCity, final String theState) {
    	address = theAddress;
		city = theCity;
		state = theState;
    }

	public Location(String theLocName, String theCity, String theState, int theZipcode) {
		locName = theLocName;
		city = theCity;
		state = theState;
		zipcode = theZipcode;
	}
	
	/*
	 * Returns the name of the location.
	 * @return name of the location
	 */
	public String getLocName() {
		return locName;
	}
	
	/*
	 * Returns the name of the city.
	 * @return name of the city
	 */
	public String getCity() {
		return city;
	}
	
	/*
	 * Returns the state of the location.
	 * @return the state of the location.
	 */
	public String getState() {
		return state;
	}
	
	/*
	 * Returns the zipcode of the location.
	 * @return the zipcode of the location.
	 */
	public int getZipcode() {
		return zipcode;
	}
	
	/*
	 * Returns a string representation of a location.
	 * @return a string representation of a location.
	 */
	@Override
    public String toString() {
        return locName + ", " + city + ", " + state + ", " + zipcode;
    }

}
