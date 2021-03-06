package view.menu.items;

import model.Controller;
import model.UserType;
import view.TextUI;
import view.menu.SearchForJobsMenu;

/**
 * A job searcher menu item.
 * @author John Bako
 */
public class SearchForJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		TextUI.navigate(new SearchForJobsMenu());		
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType.equals(UserType.Volunteer);
	}

	@Override
	public String getLabel() {
		return "Search for Jobs";
	}

}
