package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.ManageParksParkSelectedMenu;

/**
 * A park manager manages a job selected menu item.
 * @author John Bako
 */
public class ManageParksParkSelectedMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new ManageParksParkSelectedMenu());			
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
