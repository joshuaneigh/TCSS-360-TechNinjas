/**
 * 
 */
package model;

/**
 * @author John Bako
 *
 */
public enum UserType {
	
	UrbanPark,
	ParkManager,
	Volunteer;
	
	/**
	 * The name associated with the user.
	 */
	private String myName;
	
	/**
     * Default constructor.
     */
    UserType() {}
    
	/**
	 * Initializes the user type enum.
	 */
	UserType(final String theName) {
		myName = theName;
	}
	
	/**
     * @return the name associated with the user.
     */
    public String getStr() {
        return myName;
    }
}

