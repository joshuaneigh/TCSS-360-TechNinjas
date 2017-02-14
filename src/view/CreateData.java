package view;

import model.Controller;
import model.Job;
import model.Location;
import model.Park;
import model.UserType;
import model.exception.JobAlreadyAddedException;
import model.exception.MaximumJobsException;
import model.exception.NoSuchUserException;

public class CreateData {

	public static void main(final String[] args) throws NoSuchUserException, MaximumJobsException, JobAlreadyAddedException {
		addUsers();
		createParks();
		createJobs();
		Controller.disconnect();
	}

	private static void createJobs() throws MaximumJobsException, JobAlreadyAddedException {
		Controller.getPark(223).addJob(new Job("Screaming", "The park need more crazy people to scream in the park.", Controller.getPark(223)));
		Controller.getPark(223).addJob(new Job("Burning Trees", "There are limbs in the park. We need more firewood to cook them.", Controller.getPark(223)));
		Controller.getPark(223).addJob(new Job("Decapitating Dandelions", "The dandelions are too long.", Controller.getPark(223)));
	}

	private static void createParks() throws IllegalStateException, NoSuchUserException {
		Controller.addPark("jToledo", new Park(new Location(97221), "Washington Park", 223));
		Controller.addPark("nCarta", new Park(new Location(66606), "Ward-Meade Park", 556));
		Controller.addPark("mDrew", new Park(new Location(23225), "Forest Hill Park", 762));
		Controller.addPark("tDuane", new Park(new Location(02116), "Boston Public Garden", 308));
		Controller.addPark("tDuane", new Park(new Location(87501), "Santa Fe River Park", 458));
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
		Controller.addUser("dBergdahl", UserType.Volunteer);
		Controller.addUser("mKnack", UserType.Volunteer);
		Controller.addUser("pLuckey", UserType.Volunteer);
		Controller.addUser("aAbaraham", UserType.Volunteer);
		Controller.addUser("rGarfield", UserType.Volunteer);
		Controller.addUser("zShaun", UserType.Volunteer);
		Controller.addUser("zHernandez", UserType.Volunteer);
		Controller.addUser("hThomas", UserType.Volunteer);
		Controller.addUser("kBrown", UserType.Volunteer);
		Controller.addUser("rNay", UserType.Volunteer);
		Controller.addUser("cDavis", UserType.Volunteer);
		Controller.addUser("tBob", UserType.Volunteer);
		Controller.addUser("mBolton", UserType.Volunteer);
		Controller.addUser("tGaster", UserType.Volunteer);
		Controller.addUser("dGould", UserType.Volunteer);
		Controller.addUser("dFowler", UserType.Volunteer);
		Controller.addUser("eChambers", UserType.Volunteer);
		Controller.addUser("tRez", UserType.Volunteer);
		Controller.addUser("bBell", UserType.Volunteer);
		Controller.addUser("kChapman", UserType.Volunteer);
		Controller.addUser("dRodgers", UserType.Volunteer);
		Controller.addUser("bDell", UserType.Volunteer);
		Controller.addUser("tBombadil", UserType.Volunteer);
		Controller.addUser("wWill", UserType.Volunteer);
		Controller.addUser("mUnderhill", UserType.Volunteer);
		Controller.addUser("bYale", UserType.Volunteer);
		Controller.addUser("anonymous", UserType.Volunteer);
		Controller.addUser("sWilliams", UserType.Volunteer);
		Controller.addUser("wWhite", UserType.Volunteer);
		Controller.addUser("pBrett", UserType.Volunteer);
		Controller.addUser("lSimpson", UserType.Volunteer);
		Controller.addUser("rCruise", UserType.Volunteer);
		Controller.addUser("cSanders", UserType.Volunteer);
		Controller.addUser("hDale", UserType.Volunteer);
		Controller.addUser("mSmithers", UserType.Volunteer);
		Controller.addUser("rYule", UserType.Volunteer);
		Controller.addUser("yBaron", UserType.Volunteer);
		Controller.addUser("sLauden", UserType.Volunteer);
		
		Controller.setBlackballed("munderhill", true);
	}

}
