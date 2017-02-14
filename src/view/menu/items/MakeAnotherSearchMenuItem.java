package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;

/**
 * Menu item to make another search for pending jobs.
 * @author Michael Loundagin
 */
public class MakeAnotherSearchMenuItem implements MenuItem {

	@Override
	public void activate() {
		final UpcomingJobsMenu upcomingJobsMenu = new UpcomingJobsMenu();
		TextUI.navigate(upcomingJobsMenu);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType == UserType.Volunteer;
	}

	@Override
	public String getLabel() {
		return "Make another search";
	}

}
