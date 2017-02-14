package view.menu;

import java.util.List;

import model.Controller;
import model.Job;
import view.menu.items.BackMenuItem;

public class ViewMyJobsMenu implements Menu {

	@Override
	public void activate() {
		MenuUtils.printHeader("View my Jobs");
		final List<Job> jobs = Controller.getUserJobs(Controller.getLoggedInUserName());
		if (jobs == null || jobs.isEmpty())
			System.out.println("There are no jobs to display.");
		else
			for (final Job job : jobs)
				System.out.printf("%s\n%s - %s\n%s\nPark %d - %s\n%s\n\n", job.getJobTitle(), job.getStartTime(),
						job.getEndTime(), job.getPark(), job.getJobDescription());
		System.out.println("\nPress [Enter] to return...");
		MenuUtils.input();
		new BackMenuItem().activate();
	}

}
