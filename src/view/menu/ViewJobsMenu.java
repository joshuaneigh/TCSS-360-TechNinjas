package view.menu;

import java.util.List;

import model.Controller;
import model.Job;
import view.TextUI;
import view.menu.items.MenuItem;

/**
 * A job viewer menu.
 * @author John Bako
 */
public class ViewJobsMenu implements Menu {
	
	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.VIEW_JOBS.getTitle(), TextUI.getSelectedPark().getNumber());
		int menuIndex = 0;
		final List<Job> jobs = Controller.getUserJobs(Controller.getLoggedInUserName());
		for (final Job job : jobs) {
			menuIndex++;
			System.out.printf("%d) Park %s\n", menuIndex, job.getJobTitle());
		}
		for (final MenuItem item : MenuEnum.VIEW_JOBS.getItems()) {
			menuIndex++;
			System.out.printf("%d) %s\n", menuIndex, item.getLabel());
		}
		do {
			try {
				System.out.print("\nPlease make your selection: ");
				final String input = MenuUtils.input();
				int result = Integer.parseInt(input);
				if (0 < result && result <= menuIndex + 1) {
					if (result - 1 < jobs.size()) {
						TextUI.selectJob(jobs.get(result - 1));
						TextUI.navigate(new ViewJobsJobSelectedMenu());
					} else {
						result -= jobs.size();
						MenuEnum.VIEW_JOBS.getItems()[result - 1].activate();
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
