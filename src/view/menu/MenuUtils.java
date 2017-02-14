package view.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Controller;
import model.UserType;
import view.menu.items.MenuItem;

/**
 * A class which contains all utility methods associated with a {@link Menu}.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public final class MenuUtils {
	
	private static final String PROGRAM_NAME;
	private static final String SEPARATOR;
	private static final Scanner SCANNER;
	
	static {
		PROGRAM_NAME = "ParkUtilities | TechNinjas";
		SEPARATOR = "-------------------------------------";
		SCANNER = new Scanner(System.in);
	}
	
	private MenuUtils() {}
	
	public static Scanner getScannerInstance() {
		return SCANNER;
	}
	
	public static void printHeader(final String title, final Object... e) {
		System.out.println(PROGRAM_NAME);
		System.out.printf(title, e);
		System.out.println(SEPARATOR);
	}
	
//	public static void clear() {
//		try {
//			if (System.getProperty("os.name").contains("Windows")) Runtime.getRuntime().exec("cls");
//			else Runtime.getRuntime().exec("clear");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Prints a standard menu to the console and handles the input.
	 * @param menu the {@link MenuEnum} to parse for printing
	 * @return the selected MenuItem
	 */
	public static MenuItem menu(final MenuEnum menu) {
//		clear();
		final UserType userType = Controller.getUserType(Controller.getLoggedInUserName());
		final List<MenuItem> items = new ArrayList<>();
		for (int i = 0, numMenuItems = 0; i < menu.getItems().length; i++)
			if (menu.getItems()[i].isAllowed(userType)) {
				numMenuItems++;
				items.add(menu.getItems()[i]);
				System.out.printf("%d)  %s\n", numMenuItems, menu.getItems()[i].getLabel());
			}
		do {
			try {
				System.out.print("\nPlease make a selection: ");
				final String input = input();
				final int result = Integer.parseInt(input);
				if (0 < result && result <= items.size()) return items.get(result - 1);
				else System.out.println("Selection is out of range.");
			} catch (final NumberFormatException e) {
				System.out.println("The input you specified is invalid.");
			}
		} while (true);
	}

	/**
	 * Gets a single line of input from the console.
	 * @return the input String
	 */
	public static String input() {
		String input = null;
		do {
			if (SCANNER.hasNextLine()) input = SCANNER.nextLine();
		} while (input == null || input.equals("") || input.equals("\n"));
		return input;
	}

}
