package view.menu.items;

import model.UserType;
import view.TextUI;
import view.menu.Menu;

/**
 * Asks the TextUI to go back one {@link Menu}.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public class BackMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.back();
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Back";
	}

}
