/**
 * 
 */
package view.menu;

import view.TextUI;
import view.menu.items.MenuItem;

/**
 * Menu for when a job is selected from the park manager's park.
 * @author Michael Loundagin
 */
public class ViewJobsJobSelectedMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.VIEW_JOBS_JOB_SELECTED.getTitle(), TextUI.getSelectedPark().getNumber(), TextUI.getSelectedJob().getJobTitle());
		final MenuItem item = MenuUtils.menu(MenuEnum.VIEW_JOBS_JOB_SELECTED);
		item.activate();
	}

}
