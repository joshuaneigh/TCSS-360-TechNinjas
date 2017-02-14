package view.menu;

import java.util.List;

import model.Controller;
import model.Park;
import view.TextUI;
import view.menu.items.MenuItem;

/**
 * A park manager menu.
 * @author John Bako
 */
public class ManageParksMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader("Manage Parks");
		String parkName = Controller.getLoggedInUserName();
		List<Park> parks = Controller.getUserParks(parkName);
		
		int menuIndex = 0;
		for (final Park park : parks) {
			menuIndex++;
			System.out.printf("%d) Park %s", menuIndex, 7); // park.getNumber());
		}
		for (final MenuItem item : MenuEnum.MANAGE_PARKS.getItems()) {
			menuIndex++;
			System.out.printf("%d) %s", menuIndex, item.getLabel());
		}
		System.out.print("Please make your selection: ");
		final String input = MenuUtils.input();
		int result = Integer.parseInt(input);
		if (0 < result && result <= menuIndex + 1) {
			if (result - 1 < parks.size()) {
				TextUI.selectPark(parks.get(result));
				TextUI.navigate(new ManageParksParkSelectedMenu());
			} else {
				result -= parks.size();
				MenuEnum.MANAGE_PARKS.getItems()[result].activate();
			}
		}
		
	}
	
}