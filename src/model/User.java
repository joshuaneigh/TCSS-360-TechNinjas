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
	
	public User(final String username, final UserType type) {
		this.username = username;
		this.type = type;
	}
	
	/*
	 * Gets the usernmae of the user.
	 * @return the user name of the current user.
	 */
	public String getUserName() {
		return username;
	}
	
	/*
	 * Return an enum type of the user.
	 * @return the type of the user.
	 */
	public UserType getType() {
		return type;
	}
	
	/*
	 * returns whether the user is blackballed or not
	 * @return a boolean representing whether the user is blackballed.
	 */
	public boolean isBlackballed() {
		return blackballed;
	}

	/*
	 * returns whether the user is flagged
	 * @return a boolean representing whether the user is flagged
	 */
	public boolean isFlagged() {
		return flagged;
	}

}
