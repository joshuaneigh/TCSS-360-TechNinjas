package view.menu;

import model.Controller;
import model.UserType;
import view.menu.items.MenuItem;

/**
 * A park manager menu.
 * @author John Bako
 */
public class ManageParksMenu implements Menu {

	@Override
	public void activate() {
		final UserType type = Controller.getUserType(Controller.getLoggedInUserName());
		if (type == null) {
			throw new IllegalStateException("Logged in but UserType is null.");	
		} else {
			MenuUtils.printHeader(MenuEnum.MANAGE_PARKS.getTitle());
			final MenuItem item = MenuUtils.menu(MenuEnum.MANAGE_PARKS);
			item.activate();
		}
	}
}
