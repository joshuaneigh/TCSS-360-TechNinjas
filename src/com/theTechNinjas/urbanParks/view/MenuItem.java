package com.theTechNinjas.urbanParks.view;

/**
 * Each {@link MenuItem} is responsible for performing its own action.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 21 Feb 2017
 */

@FunctionalInterface
interface MenuItem {
	
	/**
	 * Perform the action which this {@link MenuItem} is intended to do.
	 */
	public void activate();

}
