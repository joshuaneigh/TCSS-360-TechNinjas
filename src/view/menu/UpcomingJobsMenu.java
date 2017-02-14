package view.menu;

import view.menu.items.MenuItem;

public class UpcomingJobsMenu implements Menu {
	
	@Override
	public void activate() {
		MenuUtils.printHeader("Upcoming Jobs");
		final MenuItem item = MenuUtils.menu(MenuEnum.UPCOMING_JOBS);
		item.activate();
	}
}
