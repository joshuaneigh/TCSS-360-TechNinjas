/**
 * 
 */
package view.menu.items;

import model.UserType;
import view.TextUI;

/**
 * Menu item for displaying volunteers for a job.
 * @author Michael Loundagin
 *
 */
public class ViewVolunteersMenuItem implements MenuItem {

	@Override
	public void activate() {
		final ListOfVolunteersMenu listMenu = new ListOfVolunteersMenu();
		TextUI.navigate(listMenu);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return false;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
