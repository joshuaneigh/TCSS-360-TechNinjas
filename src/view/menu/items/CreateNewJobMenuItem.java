package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.CreateNewJobMenu;

public class CreateNewJobMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new CreateNewJobMenu());
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType == UserType.ParkManager;
	}

	@Override
	public String getLabel() {
		return "Create New Job";
	}
}
