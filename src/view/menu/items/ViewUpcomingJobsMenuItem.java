package view.menu.items;

import model.Calendar;
import model.Controller;
import model.UserType;
import view.menu.MenuEnum;
import view.menu.MenuUtils;

/**
 * Menu item to view the calendar of jobs for the upcoming month.
 * @author Michael Loundagin
 */
public class ViewUpcomingJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		final Calendar calendar = new Calendar(Controller.getAllJobs());
		System.out.println(calendar.toString());
		System.out.println();
		System.out.println("Press [Enter] to return...");
		MenuUtils.input();
		MenuUtils.menu(MenuEnum.WELCOME);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType == UserType.UrbanPark;
	}

	@Override
	public String getLabel() {
		return "Calendar of Upcoming Jobs";
	}

}
