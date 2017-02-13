package view.menu.items;

import java.util.List;

import model.Controller;
import model.Job;
import model.UserType;

public class ViewMyJobsMenuItem implements MenuItem {

	@Override
	public void activate() {
		final List<Job> jobs = Controller.getUserJobs(Controller.getLoggedInUserName());
		for (final Job job : jobs) {
			System.out.printf("%s\n%s - %s\n%s\nPark %d - %s\n%s\n\n", job.getTitle(), job.getStartTime(),
					job.getEndTime(), job.getParkName(), job.getJobDescription());
		}
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return (Controller.getLoggedInUserName() == "administrator") 
				|| userType.equals(UserType.Volunteer);
	}

	@Override
	public String getLabel() {
		return "View My Jobs";
	}

}
