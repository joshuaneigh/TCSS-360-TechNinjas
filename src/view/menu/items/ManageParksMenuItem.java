package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.ManageParksMenu;

/**
 * A park manager menu item.
 * @author John Bako
 */
public class ManageParksMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new ManageParksMenu());	
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType.equals(UserType.ParkManager);
	}

	@Override
	public String getLabel() {
		return "Parks";
	}	
}
