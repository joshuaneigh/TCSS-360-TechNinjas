package view.menu;

import model.Controller;
import model.UserType;

/**
 * The first menu within the UI; launches all other menus.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public class WelcomeMenu implements Menu {

	@Override
	public void activate() {
		final UserType type = Controller.getUserType(Controller.getLoggedInUserName());
		if (type == null) {
			throw new IllegalStateException("Logged in but UserType is null.");
		} else {
			MenuUtils.printHeader("Welcome, " + Controller.getLoggedInUserName() + '.');
			MenuUtils.menu(MenuEnum.WELCOME);
		}
	}

}
