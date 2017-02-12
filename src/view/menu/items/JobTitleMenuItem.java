package view.menu.items;

import model.UserType;

public class JobTitleMenuItem implements MenuItem {
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isAllowed(final UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Job Title: ";
	}



}
