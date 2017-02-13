package view.menu.items;

import model.Controller;
import model.UserType;
import model.exception.NoSuchUserException;

/**
 * Exits the application.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public class ExitMenuItem implements MenuItem {
	
	@Override
	public void activate() {
		try {
			Controller.disconnect();
			System.exit(0);
		} catch (final NoSuchUserException e) {
			System.out.println("Could not log off successfully.");
		}
	}

	@Override
	public boolean isAllowed(final UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Exit";
	}
	
}
