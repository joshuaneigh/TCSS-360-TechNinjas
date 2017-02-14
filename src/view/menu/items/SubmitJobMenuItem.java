package view.menu.items;

import java.util.Scanner;

import model.Controller;
import model.Job;
import model.UserType;
import model.exception.JobAlreadyAddedException;
import model.exception.MaximumJobsException;
import view.TextUI;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class SubmitJobMenuItem implements MenuItem {
	
	Scanner scanner = MenuUtils.getScannerInstance();

	@Override
	public void activate() {
		System.out.print("Are you sure you would like to submit this job (y/n)? ");
		if (scanner.next().equals("y")) {
			int jobsOnSameDay = 0;
			final Job newJob = CreateNewJobMenu.getJob();
			for (final Job job : Controller.getAllJobs()) {
				if (job.getStartTime().getDayOfMonth() == newJob.getStartTime().getDayOfMonth()
						|| job.getStartTime().getDayOfMonth() == newJob.getEndTime().getDayOfMonth()
						|| job.getEndTime().getDayOfMonth() == newJob.getStartTime().getDayOfMonth()
						|| job.getEndTime().getDayOfMonth() == newJob.getEndTime().getDayOfMonth()) {
					jobsOnSameDay++;
					if (jobsOnSameDay == 2) {
						break;
					}
				}
			}
			if (jobsOnSameDay == 2) {
				System.out.println("Cannot add job: Two jobs already occur within the days this job should occur. Please select different days.");
			} else {
				TextUI.selectPark(Controller.getPark(CreateNewJobMenu.getParkNumber()));
				try {
					TextUI.getSelectedPark().addJob(newJob);
					System.out.println("The job has been successfully submitted.");
					System.out.println();
					CreateNewJobMenu.clear();
				} catch (MaximumJobsException e) {
					System.out.println("Cannot add job. Maximum number of pending jobs reached.");
				} catch (JobAlreadyAddedException e) {
					System.out.println("Cannot add job. Job has been previously added.");
				}
			}
		} 
	}
	

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Submit Job";
	}
	
}
