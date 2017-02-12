package view.menu;

import model.Controller;
import model.exception.NoSuchUserException;

public class LoginMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.LOGIN.getTitle());
		
		String username;
		do {
			System.out.print("Please enter your username: ");
			do {
				username = MenuUtils.input();
			} while (username == null);
			try {
				if (Controller.authenticate(username)) break;
			} catch (NoSuchUserException e) {
				System.out.println("Could not authenticate user.\n");
			}
		} while (true);

		if (Controller.getUserType(username) == null) throw new IllegalStateException("User does not have a type.");
		System.out.println("Successfully authenticated!\n");
	}

}
