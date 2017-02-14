package view.menu.items;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import model.Controller;
import model.Job;
import model.UserType;
import model.exception.NoSuchUserException;
import view.TextUI;
import view.menu.MenuEnum;
import view.menu.MenuUtils;

/**
 * Menu item to sign up for a job.
 * @author Michael Loundagin
 */
public class SignUpForJobMenuItem implements MenuItem {
	
	/**
	 * The minimum number of days before job's start date when a user is allowed to sign up.
	 */
	private static final int MIMIMUM_DAYS_FROM_JOB_DATE_ALLOWED = 3;

	@Override
	public void activate() {
		final Job selectedJob = TextUI.getSelectedJob();
		final LocalDateTime currentDate = LocalDateTime.now();
		final LocalDateTime jobStartDate = selectedJob.getStartTime();
		final LocalDateTime jobEndDate = selectedJob.getEndTime();
		final String username = Controller.getLoggedInUserName();
		//check if job date is less than three days away
		if (currentDate.until(selectedJob.getStartTime(), ChronoUnit.DAYS) < MIMIMUM_DAYS_FROM_JOB_DATE_ALLOWED) {
			System.out.println("Could not sign up for the job: Job's starting date is less than three days away.");
		} else if (Controller.isBlackballed(username)) {
			System.out.println("Could not sign up for the job: You are blackballed. Please contact Urban Parks staff for more information.");
		} else if (Controller.isUserSignedUpForJobOnDate(username, jobStartDate)
				|| Controller.isUserSignedUpForJobOnDate(username, jobEndDate)) {
			System.out.println("Could not sign up for job: You are already signed up for a job which happens at the same time as this job.");
		} else {
			Controller.volunteerForJob(username, selectedJob);
			try {
				Controller.addPark(username, TextUI.getSelectedPark());	
			} catch (final NoSuchUserException e) {
				System.err.println("FATAL ERROR: User does not exist in the persistent storage!");
			}
			System.out.println("Job successfully added!");
			System.out.println("Press [Enter] to return...");
			MenuUtils.input();
			MenuUtils.menu(MenuEnum.WELCOME);
		}
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return Controller.getLoggedInUserName().equals("administrator") || userType == UserType.Volunteer;
	}

	@Override
	public String getLabel() {
		return "Sign up for this job";
	}

}
