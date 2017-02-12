package view.menu.items;

import model.UserType;

/**
 * Each {@link MenuItem} is responsible for performing its own action.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public interface MenuItem {
	
	/**
	 * Perform the action which this {@link MenuItem} is intended to do.
	 */
	public void activate();

	/**
	 * Returns whether the passed UserType is allowed.
	 * 
	 * @param userType the type to check for access
	 * @return if the userType has access
	 */
	public boolean isAllowed(UserType userType);

	/**
	 * Returns the label to be used for this {@link MenuItem}.
	 * 
	 * @return the String label to be used
	 */
	public String getLabel();

}
