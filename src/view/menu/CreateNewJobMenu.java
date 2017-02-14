package view.menu;

import java.time.LocalDateTime;

import model.Job;
import view.TextUI;
import view.menu.Menu;
import view.menu.MenuEnum;
import view.menu.MenuUtils;

public class CreateNewJobMenu implements Menu {
	
	private static CreateNewJobMenu INSTANCE;
	
	private static String jobTitle;
	private static LocalDateTime jobDate;
	private static String jobDescription;
	private static int parkNumber;
	private static int numVol;

	@Override
	public void activate() {
		INSTANCE = this;
		MenuUtils.printHeader("Create New Job");
		System.out.format("Make a selection to edit the field or navigate:\n");
		final MenuItem item = MenuUtils.menu(MenuEnum.CREATE_NEW_JOB);
		item.activate();	
		
		int selection = Integer.parseInt(MenuUtils.input());
		
		if (selection == 1) {
			new JobTitleMenuItem().activate();	
		} else if (selection == 2) {
			new JobDateMenuItem().activate();
		} else if (selection == 3) {
			new ParkNumberMenuItem().activate();
		} else if (selection == 4) {
			new JobDescriptionMenuItem().activate();
		} else if (selection == 5) {
			new NumVolAcceptedMenuItem().activate();
		} else if (selection == 6) {
			new SubmitJobMenuItem().activate();
		} else if (selection == 7) {
			new BackMenuItem().activate();
		} else if (selection == 8) {
			new ExitMenuItem().activate();
		}
		
	}
	
	public static void setJobTitle(final String theJobTitle) {
		jobTitle = theJobTitle;
	}
	
	
	public static void setJobDate(final LocalDateTime theJobDate) {
		jobDate = theJobDate;
	}
	
	
	public static void setJobDescription(final String theJobDescription) {
		jobDescription = theJobDescription;
	}
	
	
	public static void setParkNumber(final int theParkNumber) {
		parkNumber = theParkNumber;
	}
	
	public static Job getJob() {
		return new Job(jobTitle, jobDescription, TextUI.getSelectedPark());
	}
	
	public static void setNumVolAccepted(final int theNumVol) {
		numVol = theNumVol;
	}
}
