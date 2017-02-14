package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.exception.NoSuchUserException;

/**
 * This class is primarily responsible for handling the storage of data. Each of
 * the {@link User} {@link Object}s and {@link Park} {@link Object}s are
 * directly accessible through this class. This class implements the Singleton
 * design pattern, and can only exist in one instance as a result. All method
 * calls must be by static reference. Data within this class is automatically
 * loaded from persistent storage if it exists, and automatically saves the data
 * to a serialized version of this type.
 *
 * This class must be compiled with Java 8 or newer. This class is thread safe.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 07 Feb 2017
 */

public final class Controller implements Serializable {

	/**
	 * Automatically generated serial version UID.
	 */
	private static final long serialVersionUID = -5223068223723547807L;

	/**
	 * The only instance of this {@link Controller}.
	 */
	private static final Controller INSTANCE;

	/**
	 * The {@link String} {@link Path} to the {@link File} which will store a
	 * serialized version of the only instance of this.
	 */
	private static final String SAVE_PATH;

	/**
	 * Standard exception message for a {@link NoSuchUserException}.
	 */
	private static final String NO_SUCH_USER;

	/**
	 * The {@link User} that is currently connected to the {@link Controller}.
	 */
	private static User CURRENT_USER;

	/**
	 * A {@link Map} of {@link String} usernames to their associated
	 * {@link User} {@link Object}s.
	 */
	private final Map<String, User> userMap;

	/**
	 * A {@link Map} of {@link User}s to a {@link Collection} containing their
	 * associated {@link Park}s.
	 */
	private final Map<User, ArrayList<Park>> parkMap;

	/**
	 * A {@link Map} of {@link Park} {@link Object}s, intended for persistence. If a
	 * {@link Park} is disassociated with a {@link User}, it is imperative that
	 * the {@link Park} is not destroyed by the GarbageCollector.
	 */
	private final Map<Integer, Park> parkNumberMap;

	static {
		SAVE_PATH = "./data/data.ser";
		NO_SUCH_USER = "The specified username is not associated with any User Object.";
		CURRENT_USER = null;

		final Path path = Paths.get(SAVE_PATH);
		if (path.toFile().isFile()) {
			Controller deserialized = new Controller();
			try {
				deserialized = deserialize(new String(Files.readAllBytes(path), StandardCharsets.UTF_8),
						Controller.class);
			} catch (ClassNotFoundException | IOException e) {
				deserialized = new Controller();
				e.printStackTrace();
			}
			INSTANCE = deserialized;
		} else {
			INSTANCE = new Controller();
		}
	}

	/**
	 * Private constructor to prevent external instantiation of this
	 * {@link Object}.
	 */
	private Controller() {
		parkMap = new HashMap<>();
		userMap = new HashMap<>();
		parkNumberMap = new HashMap<>();
		// There must be at least one user that is logged in.
		final String admin = "administrator";
		userMap.put(admin, new User(admin, UserType.ParkManager));
		CURRENT_USER = userMap.get(admin);
	}

	/**
	 * Tries to serialize the passed {@link Object} into a {@link String}.
	 * 
	 * @param object
	 *            the {@link Object} to serialize
	 * @return the {@link String} serialization of the passed {@link Object}
	 * @throws IOException
	 *             if the passed {@link Object} is not {@link Serializable}
	 */
	private static final <T extends Serializable> String serialize(final T object) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return new String(Base64.getEncoder().encode(baos.toByteArray()));
	}

	/**
	 * Tries to deserialize the passed {@link String} into the passed
	 * {@link Object} type.
	 * 
	 * @param serialized
	 *            a {@link String} which contains a serialized {@link Object}
	 * @param type
	 *            a reference to the class which will be used to cast
	 * @return the deserialized Object of passed type from the passed
	 *         {@link String}
	 * @throws IOException
	 *             if the passed {@link String} could not be deserialized
	 * @throws ClassNotFoundException
	 *             if the passed class is invalid or if mismatched with the
	 *             deserialized {@link Object}
	 */
	private static final <T extends Serializable> T deserialize(final String serialized, final Class<T> type)
			throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(serialized.getBytes());
		final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		return type.cast(ois.readObject());
	}

	/**
	 * Authenticates the client, which is attempting to login with the passed
	 * username.
	 * 
	 * @param username
	 *            the name which the client is attempting to login with
	 * @return if the login was successful
	 * @throws NoSuchUserException
	 *             if the specified username is not associated with any
	 *             {@link User} {@link Object}
	 */
	public static boolean authenticate(final String username) throws NoSuchUserException {
		Objects.requireNonNull(username);
		if (INSTANCE.userMap.containsKey(username.toLowerCase())) {
			CURRENT_USER = (INSTANCE.userMap.get(username.toLowerCase()));
			return true;
		} else {
			throw new NoSuchUserException(NO_SUCH_USER);
		}
	}

	public static boolean disconnect() throws NoSuchUserException {
		return disconnect(CURRENT_USER.getUserName());
	}
	
	/**
	 * Tries to disconnect the passed User from this {@link Controller}. The
	 * state of this {@link Controller} will be saved into a serialized
	 * {@link Object} upon successful disconnect.
	 * 
	 * @param username
	 *            the name of the {@link User} which wishes to disconnect
	 * @return if the disconnection was successful
	 * @throws NoSuchUserException
	 *             if the specified User is not already logged in
	 */
	public static boolean disconnect(final String username) throws NoSuchUserException {
		Objects.requireNonNull(username);
		if (!CURRENT_USER.equals(INSTANCE.userMap.get(username.toLowerCase()))) {
			throw new NoSuchUserException("The specified User is not logged in.");
		} else {
			try {
				final Path path = Paths.get(SAVE_PATH);
				final String serialized = serialize(INSTANCE);

				if (path.toFile().isFile()) {
					// TODO: Sleep Thread and wait for response of whether to
					// override file.
				} else {
					path.toFile().getParentFile().mkdirs();
					path.toFile().createNewFile();
				}

				final PrintStream out = new PrintStream(new FileOutputStream(SAVE_PATH));
				out.print(serialized);
				out.close();
				CURRENT_USER = null;
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	/**
	 * Adds the passed {@link Park} to the master list and associates that
	 * {@link Park} with the {@link User} with the passed username.
	 * 
	 * @param username
	 *            the username to associate with the passed {@link Park}
	 *            {@link Object}
	 * @param park
	 *            the {@link Park} to add to each {@link Collection}
	 * @return if the operation was successful
	 * @throws NoSuchUserException
	 *             if the specified username is not associated with any
	 *             {@link User} {@link Object}
	 * @throws IllegalStateException
	 *             if the passed {@link Park} has already been added
	 */
	public static boolean addPark(final String username, final Park park)
			throws NoSuchUserException, IllegalStateException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(park);
		if (CURRENT_USER == null) {
			throw new IllegalStateException("You are not logged in.");
		} else if (INSTANCE.userMap.get(username.toLowerCase()) == null) {
			throw new NoSuchUserException(NO_SUCH_USER);
		} else if (INSTANCE.parkNumberMap.containsValue(park)) {
			throw new IllegalStateException("The specified Park has already been added.");
		} else {
			INSTANCE.parkNumberMap.put(park.getNumber(), park);
			INSTANCE.parkMap.get(INSTANCE.userMap.get(username.toLowerCase())).add(park);
			return true;
		}
	}

	/**
	 * Adds a {@link User} with the passed username and UserType to the
	 * appropriate {@link Collection}.
	 * 
	 * @param username
	 *            the name to be associated with the {@link User} to add
	 * @return if the {@link User} was successfully added to the appropriate
	 *         {@link Collection}
	 * @throws IllegalStateException
	 *             if the {@link User} has already been added
	 */
	public static boolean addUser(final String username, final UserType type) throws IllegalStateException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(type);
		if (CURRENT_USER == null) {
			throw new IllegalStateException("You are not logged in.");
		} else if (INSTANCE.userMap.containsKey(username.toLowerCase())) {
			throw new IllegalStateException("The passed User is already added.");
		} else {
			final User user = new User(username.toLowerCase(), type);
			INSTANCE.userMap.put(username.toLowerCase(), user);
			INSTANCE.parkMap.put(user, new ArrayList<>());
			return true;
		}
	}
	
	protected static User getUser(final String username) {
		return INSTANCE.userMap.get(username);
	}
	
	protected static boolean associateUser(final String username, final Park park) {
		if (!INSTANCE.parkNumberMap.containsValue(park)) throw new IllegalStateException("Park not added to system.");
		if (!INSTANCE.parkMap.containsKey(INSTANCE.userMap.get(username))) INSTANCE.parkMap.put(INSTANCE.userMap.get(username), new ArrayList<>());
		INSTANCE.parkMap.get(INSTANCE.userMap.get(username)).add(park);
		return true;
	}
	
	public static List<Job> getAllJobs() {
		final List<Job> jobs = new ArrayList<>();
		for (final Park p : INSTANCE.parkNumberMap.values()) jobs.addAll(p.getJobs());
		return jobs;
	}

	/**
	 * Tries to remove the {@link User} associated with the passed username from
	 * the appropriate {@link Collection}.
	 * 
	 * @param username
	 *            the name of the {@link User} to remove
	 * @return if the {@link User} was successfully removed
	 * @throws NoSuchUserException
	 *             if there is no {@link User} already in the
	 *             {@link Collection}.
	 */
	public static boolean removeUser(final String username) throws NoSuchUserException {
		Objects.requireNonNull(username);
		if (CURRENT_USER == null) {
			throw new IllegalStateException("You are not logged in.");
		} else if (INSTANCE.userMap.containsKey(username.toLowerCase())) {
			final User user = INSTANCE.userMap.get(username.toLowerCase());
			INSTANCE.userMap.remove(username.toLowerCase());
			INSTANCE.parkMap.remove(user);
			return true;
		} else {
			throw new NoSuchUserException(NO_SUCH_USER);
		}
	}

	public static UserType getUserType(final String username) {
		Objects.requireNonNull(username);
		final User user = INSTANCE.userMap.get(username.toLowerCase());
		return user.getType();
	}
	
	public static UserType getLoggedInUserType() {
		return CURRENT_USER.getType();
	}
	
	public static String getLoggedInUserName() {
		return CURRENT_USER.getUserName();
	}
	
	public static List<Job> getUserJobs(final String username) {
		final List<Job> jobs = new ArrayList<>();
		final User user = INSTANCE.userMap.get(username);
		if (INSTANCE.parkMap.get(INSTANCE.userMap.get(username)) == null) return null;
		for (final Park park : INSTANCE.parkMap.get(INSTANCE.userMap.get(username))) {
			if (INSTANCE.userMap.get(username).getType().equals(UserType.ParkManager))
				for (final Job job : park.getJobs()) jobs.add(job);
			else 
				for (final Job job : park.getJobs()) if (job.hasVolunteer(user)) jobs.add(job);
		}
		return jobs;
	}
	
	public static List<Park> getUserParks(final String username) {
		return INSTANCE.parkMap.get(INSTANCE.userMap.get(username));
	}
	
	public static boolean isBlackballed(final String username) {
		return INSTANCE.userMap.get(username).isBlackballed();
	}
	
	public static void setBlackballed(final String username, final boolean blackballed) {
		INSTANCE.userMap.get(username).setBlackballed(blackballed);
	}
	
	public static boolean isUserSignedUpForJobOnDate(final String username, final LocalDateTime date) {
		List<Job> jobs = getUserJobs(username);
		for (final Job job : jobs)
			if (job.getStartTime().compareTo(date) <= 0 || job.getEndTime().compareTo(date) >= 0)
				return true;
		return false;
	}
	
	public static boolean volunteerForJob(final String username, final Job job) {
		job.addVolunteer(INSTANCE.userMap.get(username));
		return true;
	}
	
	public static Park getPark(final Integer number) {
		return INSTANCE.parkNumberMap.get(number);
	}

}
