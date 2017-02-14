package view.menu.items;

import java.util.Scanner;

import model.Controller;
import model.Park;
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
			System.out.println();
			System.out.println("The job has been successfully submitted.");
			try {
				TextUI.getSelectedPark().addJob(CreateNewJobMenu.getJob());
			} catch (MaximumJobsException e) {
				System.out.println("Cannot add job. Maximum number of pending jobs reached.");
			} catch (JobAlreadyAddedException e) {
				System.out.println("Cannot add job. Job has been previously added.");
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
