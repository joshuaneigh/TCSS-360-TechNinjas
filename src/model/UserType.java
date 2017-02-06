/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author John Bako
 *
 */
public enum UserType implements Serializable {
	
	UrbanPark,
	ParkManager,
	Volunteer;
	
	/**
	 * The name associated with the user.
	 */
	private String userName;
	
	/**
     * Default constructor.
     */
    UserType() {}
    
	/**
	 * Initializes the user type enum.
	 */
	UserType(final String theUserName) {
		userName = theUserName;
	}
	
	/**
     * @return the name associated with the user.
     */
    public String getUserName() {
        return userName;
    }
}

