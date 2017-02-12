package view;

import java.util.Scanner;
import java.util.Stack;

import model.Controller;
import model.UserType;
import model.exception.NoSuchUserException;
import view.menu.Menu;
import view.menu.MenuEnum;
import view.menu.MenuItemEnum;
import view.menu.WelcomeMenu;

public class TextUI {
	
	private static final String PROGRAM_NAME;
	private static final String SEPARATOR;
	
	private final Stack<Menu> menus;
	private final Scanner scanner;
	private UserType userType;
	private String username;
	
	static {
		PROGRAM_NAME = "ParkUtilities | TechNinjas";
		SEPARATOR = "-------------------------------------";
	}
	
	public TextUI() {
		menus = new Stack<>();
		scanner = new Scanner(System.in);
	}
	
	public void launch() {
		login();
		welcome();
	}
	
	private static void printHeader(final String title) {
		System.out.println(PROGRAM_NAME);
		System.out.println(title);
		System.out.println(SEPARATOR);
	}
	
	private String input() {
		String input = null;
		do {
			if (scanner.hasNextLine()) input = scanner.nextLine();
		} while (input == null);
		return input;
	}
	
	private MenuItemEnum menu(final MenuEnum menu) {
		
		int numMenuItems = 0;
		switch (menu) {
		default:
			for (int i = 0; i < menu.getItems().length; i++)
				if (menu.getItems()[i].isAllowed(userType)) {
					numMenuItems++;
					System.out.printf("%d)  %s\n", numMenuItems, menu.getItems()[i].getText());
				}
			break;
		}

		switch (menu) {
		default:
			do {
				try {
					System.out.print("\nPlease make a selection: ");
					final String input = input();
					final int result = Integer.parseInt(input);
					if (0 < result && result <= numMenuItems) return menu.getItems()[result];
					else System.out.println("Selection is out of range.");
				} catch (final NumberFormatException e) {
					System.out.println("The input you specified is invalid.");
				}
			} while (true);
		}

	}
	
	Scanner getScannerInstance() {
		return scanner;
	}
	
	private void login() {
		printHeader(MenuEnum.LOGIN.getTitle());
		
		do {
			System.out.print("Please enter your username: ");
			do {
				username = input();
			} while (username == null);
			try {
				if (Controller.authenticate(username)) break;
			} catch (NoSuchUserException e) {
				System.out.println("Could not authenticate user.\n");
			}
		} while (true);

		userType = Controller.getUserType(username);
		if (userType == null) throw new IllegalStateException("User does not have a type.");
		System.out.println("Successfully authenticated!\n");
		menus.add(new WelcomeMenu());
	}
	
	private void welcome() {
		final UserType type = Controller.getUserType(username);
		if (type == null) {
			throw new IllegalStateException("Logged in but UserType is null.");
		} else {
			printHeader("Welcome, " + username + '.');
			menu(MenuEnum.WELCOME);
		}
	}
	
}
