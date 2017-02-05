package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.HashMap;
import java.util.List;

/**
 * This class is primarily responsible for ...
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 05 Feb 2017
 */

public final class Controller implements Serializable {

	/** Automatically generated serial version UID. */
	private static final long serialVersionUID = -5223068223723547807L;
	/** The only instance of this Controller. */
	private static final Controller INSTANCE;
	/** The String path to the File which will store a serialized version of the only instance of this. */
	private static final String SAVE_PATH;
	/** A collection of users currently connected to the Controller. */
	private static final ArrayList<User> CURRENT_USERS;
	
	
	private final HashMap<String, User> userMap;
	private final HashMap<User, ArrayList<Park>> parkMap;
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
	 * Private constructor to prevent external instantiation of this Object.
	 */
	private Controller() {
		parkMap = new HashMap<>();
		userMap = new HashMap<>();
		parkList = new ArrayList<>();
	}
	
	private static final <T extends Serializable> String serialize(final T object) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return new String(Base64.getEncoder().encode(baos.toByteArray()));
	}
	
	private static final <T extends Serializable> T deserialize(final String serialized, final Class<T> type) throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(serialized.getBytes());
		final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		return type.cast(ois.readObject());
	}
	
	public static boolean authenticate(final String username) {
		if (INSTANCE.userMap.containsKey(username)) {
			CURRENT_USERS.add(INSTANCE.userMap.get(username));
			return true;
		} else {
			return false;
		}
	}
	
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
	
	public static boolean addPark(final String username, final Park park) {
		if (INSTANCE.parkList.contains(park)) {
			return false;
		} else {
			INSTANCE.parkList.add(park);
			INSTANCE.parkMap.get(INSTANCE.userMap.get(username)).add(park);
			return true;
		}
	}
	
	public static boolean addUser(final String username) {
		if (INSTANCE.userMap.containsKey(username)) {
			return false;
		} else {
			final User user = new User(username);
			INSTANCE.userMap.put(username, user);
			INSTANCE.parkMap.put(user, new ArrayList<>());
			return true;
		}
	}
	
	public static boolean removeUser(final String username) {
		if (INSTANCE.userMap.containsKey(username)) {
			final User user = INSTANCE.userMap.get(username);
			INSTANCE.userMap.remove(username);
			INSTANCE.parkMap.remove(user);
			return true;
		} else {
			return false;
		}
	}
	
}
