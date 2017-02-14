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
     * The serial for this class.
     */
	private static final long serialVersionUID = 6562744950368502216L;
	
	/**
	 * The location name.
	 */
	private String locName;
	
	/**
	 * The city.
	 */
	private String city;
	
	/**
	 * The state.
	 */
    private String state;
    
    /**
     * The zipcode.
     */
    private int zipcode; 
	
    /**
     * The address.
     */
	private String address;
    
	/**
	 * The constructor for zipcode.
	 * @param theZipcode
	 */
    public Location(final int theZipcode) {
    	zipcode = theZipcode;
    }
    
    /**
     * The second constructor.
     * @param theAddress The address for a location.
     * @param theCity The city for a location.
     * @param theState The state for a location.
     */
    public Location(final String theAddress, final String theCity, final String theState) {
    	address = theAddress;
		city = theCity;
		state = theState;
    }
    
    /**
     * The third constructor.
     * @param theLocName The location name.
     * @param theCity The city for a location.
     * @param theState The state for a location.
     * @param theZipcode The zipcode for a location.
     */
	public Location(String theLocName, String theCity, String theState, int theZipcode) {
		locName = theLocName;
		city = theCity;
		state = theState;
		zipcode = theZipcode;
	}
	
	/**
	 * Getter for the address.
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Getter for the name of the location.
	 * @return name of the location
	 */
	public String getLocName() {
		return locName;
	}
	
	/**
	 * Getter for the name of the city.
	 * @return name of the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Getter for the state of the location.
	 * @return the state of the location.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Getter for the zipcode of the location.
	 * @return the zipcode of the location.
	 */
	public int getZipcode() {
		return zipcode;
	}
	
	/**
	 * Method that overrides to string.
	 * @return a string representation of a location.
	 */
	@Override
    public String toString() {
        return locName + ", " + city + ", " + state + ", " + zipcode;
    }
}
