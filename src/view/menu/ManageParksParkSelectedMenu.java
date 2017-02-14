package view.menu;

import view.menu.items.MenuItem;

/**
 * A park selected menu.
 * @author John Bako
 */
public class ManageParksParkSelectedMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.MANAGE_PARKS_PARK_SELECTED.getTitle());
		final MenuItem item = MenuUtils.menu(MenuEnum.MANAGE_PARKS_PARK_SELECTED);
		item.activate();
	}

}
