package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.ViewMyJobsMenu;

public class ViewMyJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new ViewMyJobsMenu());
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType.equals(UserType.Volunteer);
	}

	@Override
	public String getLabel() {
		return "View My Jobs";
	}

}
