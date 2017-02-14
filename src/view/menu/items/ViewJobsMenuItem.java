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
		final ViewJobsMenu viewJobsMenu = new ViewJobsMenu();
		TextUI.navigate(viewJobsMenu);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType == UserType.ParkManager;
	}

	@Override
	public String getLabel() {
		return "View jobs for this park.";
	}

}
