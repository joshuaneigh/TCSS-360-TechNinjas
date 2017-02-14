package view.menu;

import view.menu.items.MenuItem;

public class ListOfVolunteersMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader("List of Volunteers | Park %d | Job %s - %s");
		final MenuItem item = MenuUtils.menu(MenuEnum.LIST_OF_VOLUNTEERS);
		item.activate();		
	}

}
