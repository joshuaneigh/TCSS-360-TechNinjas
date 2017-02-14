package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.ViewJobsMenu;

/**
 * Launch view of pending jobs for a Park Manager
 * @author Michael Loundagin
 */
public class ViewJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new ViewJobsMenu());
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType == UserType.ParkManager;
	}

	@Override
	public String getLabel() {
		return "View Jobs";
	}

}
