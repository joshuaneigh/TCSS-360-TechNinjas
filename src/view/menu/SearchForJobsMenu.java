package view.menu;

import view.TextUI;
import view.menu.items.MenuItem;

public class SearchForJobsMenu implements Menu {

	@Override
	public void activate() {
		System.out.println("---------------------------------------------------------------------");
		MenuUtils.printHeader("Please enter a location, park name, "
				+ "city, zip code, or date to search for a job:");
		final MenuItem item = MenuUtils.menu(MenuEnum.SEARCH_FOR_JOBS);
		item.activate();
		if (MenuUtils.input().equals(TextUI.getSelectedPark())) {
			MenuUtils.printHeader("Available jobs near: " + MenuUtils.input() + "\n" + TextUI.getSelectedJob());
		} 
	}
}
