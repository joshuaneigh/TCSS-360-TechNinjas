package model;

import java.io.Serializable;

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

	public String getUserName() {
		return username;
	}
	
	public UserType getType() {
		return type;
	}
	
	public boolean isBlackballed() {
		return blackballed;
	}

	public boolean isFlagged() {
		return flagged;
	}

}
