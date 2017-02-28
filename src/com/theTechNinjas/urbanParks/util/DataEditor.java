package com.theTechNinjas.urbanParks.util;

import java.io.File;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

public class DataEditor {

	public static void main(final String[] args) {
		System.out.println("Deleting .ser file...");
		new File("./data/data.ser").delete();
		System.out.println("Deleted!");
		
		Controller.login("admin");
		System.out.println("Adding users...");
		Controller.addUser("jneigh", "Park Manager");
		Controller.addUser("jasmined", "Park Manager");
		Controller.addUser("jsbako90", "Park Manager");
		Controller.addUser("yoshiweegee", "Park Manager");
		Controller.addUser("ybennou", "Park Manager");
		Controller.addUser("aPrado", "Volunteer");
		Controller.addUser("sCorrin", "Volunteer");
		Controller.addUser("jJones", "Volunteer");
		Controller.addUser("jMars", "Volunteer");
		Controller.addUser("cNate", "Volunteer");
		Controller.addUser("yKanu", "Volunteer");
		Controller.addUser("dMcArthur", "Volunteer");
		Controller.addUser("mKnack", "Volunteer");
		Controller.addUser("aAbaraham", "Volunteer");
		Controller.addUser("zShaun", "Volunteer");
		Controller.addUser("hThomas", "Volunteer");
		Controller.addUser("rNay", "Volunteer");
		Controller.addUser("tBob", "Volunteer");
		Controller.addUser("tGaster", "Volunteer");
		Controller.addUser("dFowler", "Volunteer");
		Controller.addUser("tRez", "Volunteer");
		Controller.addUser("kChapman", "Volunteer");
		Controller.addUser("bDell", "Volunteer");
		Controller.addUser("wWill", "Volunteer");
		Controller.addUser("bYale", "Volunteer");
		Controller.addUser("sWilliams", "Volunteer");
		Controller.addUser("pBrett", "Volunteer");
		Controller.addUser("rCruise", "Volunteer");
		Controller.addUser("hDale", "Volunteer");
		Controller.addUser("jJones", "Volunteer");
		Controller.addUser("rYule", "Volunteer");
		Controller.addUser("yBaron", "Volunteer");
		Controller.addUser("sLauden", "Volunteer");
		
		System.out.println("Adding parks...");
		Controller.addPark("Washington Park");
		Controller.addPark("Ward-Meade Park");
		Controller.addPark("Forest Hill Park");
		Controller.addPark("Boston Public Garden");
		Controller.addPark("Santa Fe River Park");
		
		System.out.println("Adding jobs...");
		try {
			Controller.addJob("Washington Park", "Trim Hedges\tThe hedges near the garden need trimmed.\t2017-04-02 12:00\t2017-04-02 14:00");
			Controller.addJob("Washington Park", "Mow Lawn\tPlease mow the lawn by the picnic area.\t2017-03-12 12:00\t2017-03-12 13:30");
			Controller.addJob("Washington Park", "Flyer Distro\tAdvertise for the upcoming concert!\t2017-04-23 08:00\t2017-04-23 16:00");
			Controller.addJob("Ward-Meade Park", "Trash Pickup\tThe trash bins around the park need emptied.\t2017-03-20 08:00\t2017-03-20 12:00");
			Controller.addJob("Ward-Meade Park", "Boat Rental\tSomeone needed to help facilitate boat rental.\t2017-04-20 12:00\t2017-04-21 12:00");
			Controller.addJob("Ward-Meade Park", "Voter Registration\tHelp register voters and pour coffee.\t2017-04-01 10:00\t2017-04-01 12:00");
			Controller.addJob("Ward-Meade Park", "5k Run Setup\tTables and water stations need set up.\t2017-03-28 12:00\t2017-03-28 16:00");
			Controller.addJob("Forest Hill Park", "Rake Leaves\tThe leaves need raked into piles for the kids.\t2017-04-19 11:00\t2017-04-19 14:00");
			Controller.addJob("Forest Hill Park", "Planting Flowers\tFlowers need planted around the new fountain.\t2017-03-15 12:00\t2017-03-15 17:00");
			Controller.addJob("Forest Hill Park", "Dig Holes\tPost holes need dug for the new dog park.\t2017-03-07 06:00\t2017-03-07 18:00");
			Controller.addJob("Forest Hill Park", "Water Garden\tThe roses need watered. Please ask for instructions.\t2017-03-31 17:00\t2017-03-31 19:00");
			Controller.addJob("Boston Public Garden", "Lifeguard\tThe city lake party needs lifeguard support.\t2017-04-16 10:00\t2017-04-16 17:00");
			Controller.addJob("Boston Public Garden", "Apple Picking\tThe apples need picked from the orchard for the bakeoff.\t2017-05-01 06:00\t2017-05-01 08:00");
			Controller.addJob("Boston Public Garden", "Paint Shed\tThe tool shed needs fresh paint.\t2017-04-27 14:00\t2017-04-27 18:00");
			Controller.addJob("Boston Public Garden", "Clean Latrines\tThe female latrine has been clogged and needs fixed.\t2017-03-04 06:00\t2017-03-04 08:00");
			Controller.addJob("Santa Fe River Park", "Restock Fish\tThe live fish need stocked from the local fishery.\t2017-03-08 08:00\t2017-03-08 12:00");
			Controller.addJob("Santa Fe River Park", "Birthday Cleanup\tLocals had a birthday party and left trash behind.\t2017-03-08 14:00\t2017-03-08 18:00");
			Controller.addJob("Santa Fe River Park", "Ticket Booth\tTickets will be sold for the LARP event.\t2017-04-27 08:00\t2017-04-27 10:00");
			Controller.addJob("Santa Fe River Park", "Concessions Stand\tConcessions will be sold during the LARP event.\t2017-04-27 11:00\t2017-04-27 13:00");
		} catch (IllegalFormatException | ScheduleConflictException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finishing up...");
		Controller.assignPark("jneigh", "Washington Park");
		Controller.assignPark("jasmined", "Ward-Meade Park");
		Controller.assignPark("jsbako90", "Forest Hill Park");
		Controller.assignPark("yoshiweegee", "Boston Public Garden");
		Controller.assignPark("ybennou", "Santa Fe River Park");
		Controller.logout();
		
		System.out.println("Done!");
	}
	
}
