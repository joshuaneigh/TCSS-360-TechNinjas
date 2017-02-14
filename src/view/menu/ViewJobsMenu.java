package view.menu;

import view.menu.items.MenuItem;

/**
 * A job viewer menu.
 * @author John Bako
 */
public class ViewJobsMenu implements Menu {
	
	@Override
	public void activate() {
		MenuUtils.printHeader("View Jobs | Park %d | %s - %s");
		final MenuItem item = MenuUtils.menu(MenuEnum.VIEW_JOBS);
		item.activate();
	}
}
