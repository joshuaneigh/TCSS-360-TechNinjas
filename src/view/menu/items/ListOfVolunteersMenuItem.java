package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.ListOfVolunteersMenu;

public class ListOfVolunteersMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new ListOfVolunteersMenu());			
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType.equals(UserType.ParkManager) 
				|| userType.equals(UserType.UrbanPark);
	}

	@Override
	public String getLabel() {
		return "Volunteers";
	}

}
