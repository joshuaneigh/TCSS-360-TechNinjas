package view;

import model.Controller;
import model.Location;
import model.Park;
import model.UserType;
import model.exception.NoSuchUserException;

public class CreateData {

	public static void main(final String[] args) throws NoSuchUserException {
		addUsers();
		createParks();
		Controller.disconnect();
	}

	private static void createParks() throws IllegalStateException, NoSuchUserException {
		Controller.addPark("jToledo", new Park(new Location(97221), "Washington Park"));
		Controller.addPark("nCarta", new Park(new Location(66606), "Ward-Meade Park"));
		Controller.addPark("mDrew", new Park(new Location(23225), "Forest Hill Park"));
		Controller.addPark("tDuane", new Park(new Location(02116), "Boston Public Garden"));
		Controller.addPark("tDuane", new Park(new Location(87501), "Santa Fe River Park"));
	}

	private static void addUsers() {
		Controller.addUser("jToledo", UserType.ParkManager);
		Controller.addUser("nCarta", UserType.ParkManager);
		Controller.addUser("mDrew", UserType.ParkManager);
		Controller.addUser("tDuane", UserType.ParkManager);
		Controller.addUser("aPrado", UserType.UrbanPark);
		Controller.addUser("sCorrin", UserType.UrbanPark);
		Controller.addUser("jJones", UserType.UrbanPark);
		Controller.addUser("jMars", UserType.UrbanPark);
		Controller.addUser("cNate", UserType.UrbanPark);
		Controller.addUser("yKanu", UserType.Volunteer);
		Controller.addUser("dMcArthur", UserType.Volunteer);
		Controller.addUser("mKnack", UserType.Volunteer);
		Controller.addUser("aAbaraham", UserType.Volunteer);
		Controller.addUser("zShaun", UserType.Volunteer);
		Controller.addUser("hThomas", UserType.Volunteer);
		Controller.addUser("rNay", UserType.Volunteer);
		Controller.addUser("tBob", UserType.Volunteer);
		Controller.addUser("tGaster", UserType.Volunteer);
		Controller.addUser("dFowler", UserType.Volunteer);
		Controller.addUser("tRez", UserType.Volunteer);
		Controller.addUser("kChapman", UserType.Volunteer);
		Controller.addUser("bDell", UserType.Volunteer);
		Controller.addUser("wWill", UserType.Volunteer);
		Controller.addUser("bYale", UserType.Volunteer);
		Controller.addUser("sWilliams", UserType.Volunteer);
		Controller.addUser("pBrett", UserType.Volunteer);
		Controller.addUser("rCruise", UserType.Volunteer);
		Controller.addUser("hDale", UserType.Volunteer);
		Controller.addUser("jJones", UserType.Volunteer);
		Controller.addUser("rYule", UserType.Volunteer);
		Controller.addUser("yBaron", UserType.Volunteer);
		Controller.addUser("sLauden", UserType.Volunteer);
	}

}
