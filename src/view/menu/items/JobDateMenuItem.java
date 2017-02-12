package view.menu.items;

import model.UserType;

public class JobDateMenuItem implements MenuItem {

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAllowed(UserType userType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Date: ";
	}

}
