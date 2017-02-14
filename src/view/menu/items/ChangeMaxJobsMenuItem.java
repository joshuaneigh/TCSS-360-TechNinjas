package view.menu.items;

import model.Controller;
import model.Park;
import model.UserType;
import view.TextUI;
import view.menu.MenuUtils;
import view.menu.WelcomeMenu;

/**
 * Menu item to allow an Ubran Parks staff member to change the maximum number of allowed pending jobs.
 * @author Michael Loundagin
 */
public class ChangeMaxJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		MenuUtils.printHeader("Change Maximum Pending Jobs");
		System.out.print("Enter the new maximum number of allowed pending jobs (currently " + Park.getMaxJobs() + "): ");
		final String newMax = MenuUtils.input();
		Park.setMaxJobs(Integer.parseInt(newMax));
		System.out.println("New maximum set.");
		TextUI.navigate(new WelcomeMenu());
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType == UserType.UrbanPark;
	}

	@Override
	public String getLabel() {
		return "Change maximum number of pending jobs";
	}

}
