package com.theTechNinjas.urbanParks.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.theTechNinjas.urbanParks.model.Controller;
import com.theTechNinjas.urbanParks.model.exception.InvalidUserTypeException;
import com.theTechNinjas.urbanParks.model.exception.IllegalFormatException;
import com.theTechNinjas.urbanParks.model.exception.NoSuchUserException;
import com.theTechNinjas.urbanParks.model.exception.ScheduleConflictException;

public final class TextUI {
	
	private static final String PROGRAM_NAME;
	private static final String SEPARATOR;
	private static final String JOB_FORMAT_STRING;
	private static final Scanner SCANNER;
	private static final Stack<Menu> MENUS;
	
	private static final Menu LOGIN;
	private static final Menu WELCOME;
	private static final Menu ADD_JOB;
	private static final Menu VOLUNTEER_JOB;
	private static final Menu VIEW_MY_JOBS;
	
	private static String SELECTED_PARK;
	private static String SELECTED_JOB;
	
	static {
		MENUS = new Stack<>();
		SCANNER = new Scanner(System.in);
		JOB_FORMAT_STRING = "%-25s%-60s%-20s%-20s\n";
		SEPARATOR = "======================================";
		PROGRAM_NAME = "ParkManagement 0.3";
		
		VIEW_MY_JOBS = () -> {
			printHeader("%s's Jobs", Controller.getLoggedInUser());
			final List<String> jobs = Controller.getVolunteerJobs(Controller.getLoggedInUser());
			if (jobs.isEmpty()) System.out.println("\nYou are not signed up for any jobs.");
			else System.out.printf(JOB_FORMAT_STRING, "Name", "Description", "Start", "End");
			for (final String job : jobs) {
				final String[] items = job.split("\t");
				System.out.printf(JOB_FORMAT_STRING, items[0], items[1], items[2], items[3]);
			}
			System.out.print("Press [Enter] to return.");
			pause();
			back();
		};
		
		VOLUNTEER_JOB = () -> {
			selectPark();
			selectJob();
			if (Controller.getVolunteerJobs(Controller.getLoggedInUser()).contains(SELECTED_JOB)) {
				System.out.println("You are already signed up for this Job.");
			} else {
				System.out.print("Are you sure you would like to volunteer for this job (y/n)? ");
				final String input = inputString();
				if (input.toLowerCase().startsWith("y")) {
					try {
						Controller.volunteerJob(Controller.getLoggedInUser(), SELECTED_PARK, SELECTED_JOB);
						System.out.println("\nYou have successfully volunteered for the job.");
					} catch (InvalidUserTypeException | NoSuchUserException | ScheduleConflictException e) {
						System.out.printf("\nCould not volunteer for Job: %s\n", e.getMessage());
					}
				}
			}
			System.out.print("Press [Enter] to return.");
			pause();
			back();
		};
		
		ADD_JOB = () -> {
			selectPark();
			printHeader("Add a new Job, %s", Controller.getLoggedInUser(), SELECTED_PARK);
			System.out.print("Please enter the Job name: ");
			final String name = inputString();
			System.out.print("Please enter the Job description: ");
			final String description = inputString();
			System.out.print("Please enter the Job start date and time (yyyy-MM-dd HH:mm): ");
			final String startTime = inputString();
			System.out.print("Please enter the Job end date and time (yyyy-MM-dd HH:mm): ");
			final String endTime = inputString();
			System.out.print("Are you sure you would like to submit this job (y/n)? ");
			final String input = inputString();
			if (input.toLowerCase().startsWith("y")) {
				final String jobName = name + '\t' + description + '\t' + startTime + '\t' + endTime;
				try {
					Controller.addJob(SELECTED_PARK, jobName);
					System.out.println("\nYou have successfully added the job.");
				} catch (ScheduleConflictException | IllegalFormatException e) {
					System.out.printf("\nJob could not be added: %s\n", e.getMessage());
				}
			}
			System.out.print("Press [Enter] to return.");
			pause();
			back();
		};
		
		WELCOME = () -> {
			printHeader("Welcome, %s", Controller.getLoggedInUser());
			final Map<String, MenuItem> map = new HashMap<>();
			map.put("Volunteer for a Job", () -> navigate(VOLUNTEER_JOB));
			map.put("View my Jobs", () -> navigate(VIEW_MY_JOBS));
			map.put("Add a new Job", () -> navigate(ADD_JOB));
			map.put("Exit", () -> exit());
			showMenu(map);
		};
		
		LOGIN = () -> {
			printHeader("Login");
			String userName;
			do {
				System.out.print("Please enter your username: ");
				userName = inputString();
				if (Controller.isUser(userName)) break;
				System.out.println("\nThere is no user with the specified username.");
			} while (true);
			Controller.login(userName);
			navigate(WELCOME);
		};
		
	}

	private TextUI() {}
	
	public static void launch() {
		LOGIN.activate();
	}
	
	private static void navigate(final Menu menu) {
		MENUS.push(menu);
		menu.activate();
	}
	
	private static void back() {
		if (MENUS.isEmpty()) {
			System.err.println("Stack is empty");
			exit();
		} else {
			MENUS.pop();
			navigate(MENUS.peek());
		}
	}
	
	private static void exit() {
		MENUS.removeAll(MENUS);
		Controller.logout();
		System.exit(0);		// TODO: Fix bug that makes this line required.
	}
	
	private static void printHeader(final String title, final Object... e) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(PROGRAM_NAME);
		System.out.printf(title + '\n', e);
		System.out.println(SEPARATOR);
	}
	
	private static void showMenu(final Map<String, MenuItem> items) {
		int i = 0;
		if (items.isEmpty()) return;
		for (final String label : items.keySet()) {
			System.out.printf("%4d)\t%s\n", ++i, label);
		}
		
		int input = -1;
		do {
			System.out.print("Please make a selection: ");
			input = inputInt();
			if (input >= 0 && input < items.size()) break;
			System.out.println("The input you specified is invalid.");
		} while (true);
		new ArrayList<MenuItem>(items.values()).get(input).activate();
	}
	
	private static String inputString() {
		String input = null;
		do {
			if (SCANNER.hasNextLine()) input = SCANNER.nextLine();
		} while (input == null || input.equals("\n") || input.equals(""));
		return input;
	}
	
	private static int inputInt() {
		try{
			return Integer.parseInt(inputString()) - 1;
		} catch (final NumberFormatException e) {
			return -1;
		}
	}
	
	private static String pause() {
		String input = null;
		do {
			if (SCANNER.hasNextLine()) input = SCANNER.nextLine();
		} while (input == null);
		return input;
	}
	
	private static void selectPark() {
		printHeader("Please select a Park", Controller.getLoggedInUser());
		final List<String> parks = Controller.getParks();
		if (parks.isEmpty()) {
			System.out.println("\nThere are no Parks in the system.");
			System.out.print("Press [Enter] to return.");
			pause();
			back();
			return;
		}
		
		final Map<String, MenuItem> parkMap = new HashMap<>();
		for (final String park : parks) {
			parkMap.put(park, () -> {
				SELECTED_PARK = park;
				System.out.println(park);
			});
		}
		showMenu(parkMap);
	}
	
	private static void selectJob() {
		printHeader("Please select a Job", Controller.getLoggedInUser());
		final List<String> jobs = Controller.getParkJobs(SELECTED_PARK);
		if (jobs.isEmpty()) {
			System.out.println("\nThere are no Jobs in the system for this Park.");
			System.out.print("Press [Enter] to return.");
			pause();
			back();
		} else {
			System.out.printf("\t" + JOB_FORMAT_STRING, "Name", "Description", "Start", "End");
			final Map<String, MenuItem> jobMap = new HashMap<>();
			for (final String job : jobs) {
				final String[] items = job.split("\t");
				jobMap.put(String.format(JOB_FORMAT_STRING, items[0], items[1], items[2], items[3]), () -> {
					SELECTED_JOB = job;
				});
			}
			showMenu(jobMap);
		}
	}

}
