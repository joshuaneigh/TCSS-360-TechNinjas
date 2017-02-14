package view.menu.items;

import model.Controller;
import model.UserType;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class ParkNumberMenuItem implements MenuItem {

	@Override
	public void activate() {
		System.out.println("Please enter the new value: ");
		final String input = MenuUtils.input();
		CreateNewJobMenu.setParkNumber(Integer.parseInt(input));
		Controller.getUserParks(input);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Park Number: " + CreateNewJobMenu.getParkNumber() == null ? "" : "Park Number: ";
	}
}
