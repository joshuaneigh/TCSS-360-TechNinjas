package view.menu.items;

import java.util.List;

import model.Controller;
import model.Job;
import model.UserType;
import view.menu.MenuUtils;

public class ViewMyJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		final List<Job> jobs = Controller.getUserJobs(Controller.getLoggedInUserName());
		if (jobs == null || jobs.isEmpty())
			System.out.println("There are no jobs to display.");
		else
			for (final Job job : jobs)
				System.out.printf("%s\n%s - %s\n%s\nPark %d - %s\n%s\n\n", job.getTitle(), job.getStartTime(),
						job.getEndTime(), job.getParkName(), job.getJobDescription());
		System.out.println("\nPress [Enter] to return...");
		MenuUtils.input();
		new BackMenuItem().activate();
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") || userType.equals(UserType.Volunteer);
	}

	@Override
	public String getLabel() {
		return "View My Jobs";
	}

}
