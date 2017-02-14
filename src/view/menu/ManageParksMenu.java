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
		MenuUtils.printHeader(MenuEnum.MANAGE_PARKS.getTitle());
		String parkName = Controller.getLoggedInUserName();
		List<Park> parks = Controller.getUserParks(parkName);
		
		int menuIndex = 0;
		for (final Park park : parks) {
			menuIndex++;
			System.out.printf("%d) Park %s\n", menuIndex, park.getNumber());
		}
		for (final MenuItem item : MenuEnum.MANAGE_PARKS.getItems()) {
			menuIndex++;
			System.out.printf("%d) %s\n", menuIndex, item.getLabel());
		}
		do {
			try {
				System.out.print("\nPlease make your selection: ");
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
				} else {
					System.out.println("Selection is out of range.");
				}
			} catch (final NumberFormatException e) {
				System.out.println("The input you specified is invalid.");
			}
		} while (true);
		
	}
	
}