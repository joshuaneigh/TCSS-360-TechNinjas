package model.exception;

/**
 * 
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @author John Bako | jsbako90@uw.edu
 * @version 06 Feb 2017
 */
public class MaximumJobsException extends Exception {

	/**
	 * Automatically generated serial version UID.
	 */
	private static final long serialVersionUID = 5608156046448200459L;
	
	/**
	 * Constructs a new {@link MaximumJobsException} with the passed message.
	 * @param message the message to associate with this {@link Exception}
	 */
	public MaximumJobsException(final String message) {
		super(message);
	}

}
