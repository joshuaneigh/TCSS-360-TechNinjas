package model;

import java.io.Serializable;


/**
 * This class is the class that is responsible for 
 *
 * @author Joshua Neighbarger, @author Youcef Bennour
 * @version 06 Feb 2017
 */
public class User implements Serializable {

	private static final long serialVersionUID = -4866786063728560210L;

	private final String username;
	private final UserType type;
	private boolean blackballed;
	private boolean flagged;
	
	/*
	 * @author Youcef Bennour
	 * @param username represents the usernma of the user
	 * @param type represents the type of the user
	 */	
	public User(final String username, final UserType type) {
		this.username = username;
		this.type = type;
	}
	
	/*
	 * @author Youcef Bennour
	 * Gets the usernmae of the user.
	 * @return the user name of the current user.
	 */
	public String getUserName() {
		return username;
	}
	
	/*
	 * @author Youcef Bennour
	 * Return an enum type of the user.
	 * @return the type of the user.
	 */
	public UserType getType() {
		return type;
	}
	
	/*
	 * @author Youcef Bennour
	 * returns whether the user is blackballed or not
	 * @return a boolean representing whether the user is blackballed.
	 */
	public boolean isBlackballed() {
		return blackballed;
	}

	/*
	 * @author Youcef Bennour
	 * returns whether the user is flagged
	 * @return a boolean representing whether the user is flagged
	 */
	public boolean isFlagged() {
		return flagged;
	}
	
	/*
	 * @author Youcef Bennour
	 * tests whether the code is right
	 */
	@Override
    	public String toString() {
        	return username + ", " + type + ", " + blackballed + ", " + flagged;
	}

}
