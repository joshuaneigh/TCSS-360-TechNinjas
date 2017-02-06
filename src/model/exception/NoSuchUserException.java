package model.exception;

/**
 * Instances of this type are thrown in the event that the client requests an
 * operation where the specified User does not exist or is not associated with
 * the requested action or its parameters.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 06 Feb 2017
 */
public class NoSuchUserException extends Exception {

	/**
	 * Automatically generated serial version UID.
	 */
	private static final long serialVersionUID = 7798895937263760400L;

	/**
	 * Constructs a new {@link NoSuchUserException} with the passed message.
	 * @param message the message to associate with this {@link Exception}
	 */
	public NoSuchUserException(final String message) {
		super(message);
	}
	
}
