package view.menu;

import view.TextUI;
import view.menu.items.MenuItem;

/**
 * A park selected menu.
 * @author John Bako
 */
public class ManageParksParkSelectedMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.MANAGE_PARKS_PARK_SELECTED.getTitle(), TextUI.getSelectedPark().getNumber(),
				TextUI.getSelectedPark().getLocation().getCity(), TextUI.getSelectedPark().getLocation().getState());
		final MenuItem item = MenuUtils.menu(MenuEnum.MANAGE_PARKS_PARK_SELECTED);
		item.activate();
	}

}
