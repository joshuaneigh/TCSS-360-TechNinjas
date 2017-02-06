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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exception.NoSuchUserException;

/**
 * This class is primarily responsible for ...
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 06 Feb 2017
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
	 * The {@link String} {@link Path} to the {@link File} which will store a serialized version of the
	 * only instance of this.
	 */
	private static final String SAVE_PATH;
	
	/** 
	 * A collection of {@link User}s currently connected to the {@link Controller}. 
	 */
	private static final ArrayList<User> CURRENT_USERS;
	
	/** 
	 * A {@link Map} of {@link String} usernames to their associated {@link User} {@link Object}s. 
	 */
	private final HashMap<String, User> userMap;
	
	/** 
	 * A {@link Map} of {@link User}s to a {@link Collection} containing their associated {@link Park}s. 
	 */
	private final HashMap<User, ArrayList<Park>> parkMap;
	
	/**
	 * A List of {@link Park} {@link Object}s, intended for persistence. If a {@link Park} is
	 * disassociated with a {@link User}, it is imperative that the {@link Park} is not destroyed
	 * by the GarbageCollector.
	 */
	private final List<Park> parkList;
	
	static {
		SAVE_PATH = "./data/data.ser";
		CURRENT_USERS = new ArrayList<>();
		
		final Path path = Paths.get(SAVE_PATH);
		if (path.toFile().isFile()) {
			Controller deserialized = new Controller();
			try {
				deserialized = deserialize(new String(Files.readAllBytes(path), StandardCharsets.UTF_8), Controller.class);
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
	 * Private constructor to prevent external instantiation of this {@link Object}.
	 */
	private Controller() {
		parkMap = new HashMap<>();
		userMap = new HashMap<>();
		parkList = new ArrayList<>();
	}
	
	/**
	 * Tries to serialize the passed {@link Object} into a {@link String}.
	 * 
	 * @param object the {@link Object} to serialize
	 * @return the {@link String} serialization of the passed {@link Object}
	 * @throws IOException if the passed {@link Object} is not {@link Serializable}
	 */
	private static final <T extends Serializable> String serialize(final T object) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return new String(Base64.getEncoder().encode(baos.toByteArray()));
	}
	
	/**
	 * Tries to deserialize the passed {@link String} into the passed {@link Object} type.
	 * 
	 * @param serialized a {@link String} which contains a serialized {@link Object}
	 * @param type a reference to the class which will be used to cast
	 * @return the deserialized Object of passed type from the passed {@link String}
	 * @throws IOException if the passed {@link String} could not be deserialized
	 * @throws ClassNotFoundException if the passed class is invalid or if mismatched with the deserialized {@link Object}
	 */
	private static final <T extends Serializable> T deserialize(final String serialized, final Class<T> type) throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(serialized.getBytes());
		final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		return type.cast(ois.readObject());
	}
	
	/**
	 * Authenticates the client, which is attempting to login with the passed username.
	 * 
	 * @param username the name which the client is attempting to login with
	 * @return if the login was successful
	 * @throws NoSuchUserException if the specified username is not associated with any {@link User} {@link Object}
	 */
	public static boolean authenticate(final String username) throws NoSuchUserException {
		if (INSTANCE.userMap.containsKey(username)) {
			CURRENT_USERS.add(INSTANCE.userMap.get(username));
			return true;
		} else {
			throw new NoSuchUserException("The specified username is not associated with any User Object.");
		}
	}
	
	/**
	 * Tries to disconnect the passed User from this {@link Controller}. The state of
	 * this {@link Controller} will be saved into a serialized {@link Object} upon successful
	 * disconnect.
	 * 
	 * @param username the name of the {@link User} which wishes to disconnect
	 * @return if the disconnection was successful
	 */
	public static boolean disconnect(final String username) {
		try {
			final Path path = Paths.get(SAVE_PATH);
			final String serialized = serialize(INSTANCE);
			if (path.toFile().isFile()) {
				// TODO: Sleep Thread and wait for response of whether to override file.
			} else {
				final PrintStream out = new PrintStream(new FileOutputStream(SAVE_PATH));
				out.print(serialized);
				out.close();
			}
			CURRENT_USERS.remove(INSTANCE.userMap.get(username));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Adds the passed {@link Park} to the master list and associates that {@link Park} with the
	 * {@link User} with the passed username.
	 * 
	 * @param username the username to associate with the passed {@link Park} {@link Object}
	 * @param park the {@link Park} to add to each {@link Collection}
	 * @return if the operation was successful
	 * @throws NoSuchUserException if the specified username is not associated with any {@link User} {@link Object}
	 * @throws IllegalStateException if the passed {@link Park} has already been added
	 */
	public static boolean addPark(final String username, final Park park) throws NoSuchUserException, IllegalStateException {
		if (INSTANCE.userMap.get(username) == null) {
			throw new NoSuchUserException("The specified username is not associated with any User Object.");
		} else if (INSTANCE.parkList.contains(park)) {
			throw new IllegalStateException("The specified Park has not been added or created.");
		} else {
			INSTANCE.parkList.add(park);
			INSTANCE.parkMap.get(INSTANCE.userMap.get(username)).add(park);
			return true;
		}
	}
	
	/**
	 * Adds a {@link User} with the passed username and UserType to the appropriate {@link Collection}.
	 * 
	 * @param username the name to be associated with the {@link User} to add
	 * @return if the {@link User} was successfully added to the appropriate {@link Collection}
	 * @throws IllegalStateException if the {@link User} has already been added
	 */
	public static boolean addUser(final String username, final UserType type) throws IllegalStateException {
		if (INSTANCE.userMap.containsKey(username)) {
			return false;
		} else {
			final User user = new User(username, type);
			INSTANCE.userMap.put(username, user);
			INSTANCE.parkMap.put(user, new ArrayList<>());
			return true;
		}
	}
	
	/**
	 * Tries to remove the {@link User} associated with the passed username from
	 * the appropriate {@link Collection}. 
	 * 
	 * @param username the name of the {@link User} to remove
	 * @return if the {@link User} was successfully removed
	 * @throws NoSuchUserException if there is no {@link User} already in the {@link Collection}.
	 */
	public static boolean removeUser(final String username) throws NoSuchUserException {
		if (INSTANCE.userMap.containsKey(username)) {
			final User user = INSTANCE.userMap.get(username);
			INSTANCE.userMap.remove(username);
			INSTANCE.parkMap.remove(user);
			return true;
		} else {
			throw new NoSuchUserException("The specified username is not associated with any User Object.");
		}
	}
	
}