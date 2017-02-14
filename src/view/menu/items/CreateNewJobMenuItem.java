package view.menu.items;

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
		return true;
	}

	@Override
	public String getLabel() {
		return "Create New Job";
	}
}
