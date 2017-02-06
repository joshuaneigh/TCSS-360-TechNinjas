package model.exception;

/**
 * 
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @author John Bako | jsbako90@uw.edu
 * @version 06 Feb 2017
 */
public class JobAlreadyAddedException extends Exception {

	/**
	 * Automatically generated serial version UID.
	 */
	private static final long serialVersionUID = -3343732792645932537L;

	/**
	 * Constructs a new {@link JobAlreadyAddedException} with the passed message.
	 * @param message the message to associate with this {@link Exception}
	 */
	public JobAlreadyAddedException(final String message) {
		super(message);
	}

}
