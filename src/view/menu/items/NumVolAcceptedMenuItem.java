package view.menu.items;

import model.UserType;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class NumVolAcceptedMenuItem implements MenuItem {

	@Override
	public void activate() {
		System.out.println("Please enter the new value: ");
		final String input = MenuUtils.input();
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Number of volunteers accepted: " + CreateNewJobMenu.getNumVolAccepted();
	}

}
