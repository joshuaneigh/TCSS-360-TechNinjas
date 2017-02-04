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
	private char myName;
	
	/**
     * Default constructor.
     */
    UserType() {}
    
	/**
	 * Initializes the user type enum.
	 */
	UserType(final char theName) {
		myName = theName;
	}
	
	/**
     * @return the name associated with the user.
     */
    public char getChar() {
        return myName;
    }
}

