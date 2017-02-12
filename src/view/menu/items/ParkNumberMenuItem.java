package view.menu.items;

import model.UserType;

public class ParkNumberMenuItem implements MenuItem {

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Park Number: ";
	}

}
