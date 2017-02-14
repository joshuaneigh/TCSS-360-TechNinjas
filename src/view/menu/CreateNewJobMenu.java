package view.menu;

import view.menu.Menu;
import view.menu.items.MenuItem;

/**
 * The menu for creating a new job.
 * @author Michael Loundagin
 */
public class CreateNewJobMenu implements Menu {

	@Override
	public void activate() {
			MenuUtils.printHeader("Create New Job");
			final MenuItem item = MenuUtils.menu(MenuEnum.CREATE_NEW_JOB);
			item.activate();
	}

}
