package view.menu;

import java.time.LocalDateTime;

import model.Job;
import view.TextUI;
import view.menu.Menu;
import view.menu.MenuEnum;
import view.menu.MenuUtils;
import view.menu.items.BackMenuItem;
import view.menu.items.ExitMenuItem;
import view.menu.items.JobDateMenuItem;
import view.menu.items.JobDescriptionMenuItem;
import view.menu.items.JobTitleMenuItem;
import view.menu.items.MenuItem;
import view.menu.items.NumVolAcceptedMenuItem;
import view.menu.items.ParkNumberMenuItem;
import view.menu.items.SubmitJobMenuItem;

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
		do {
			MenuUtils.printHeader("Create New Job");
			System.out.format("Make a selection to edit the field or navigate:\n");
			final MenuItem item = MenuUtils.menu(MenuEnum.CREATE_NEW_JOB);
			item.activate();
		} while (true);
	}
	
	
	public static String getJobTitle() {
		return jobTitle;	
	}
	
	public static void setJobTitle(final String theJobTitle) {
		jobTitle = theJobTitle;
	}
	
	
	public static LocalDateTime getJobDate() {
		return jobDate;	
	}
	
	
	public static void setJobDate(final LocalDateTime theJobDate) {
		jobDate = theJobDate;
	}
	
	
	public static String getJobDescription() {
		return jobDescription;
	}
	
	public static void setJobDescription(final String theJobDescription) {
		jobDescription = theJobDescription;
	}
	
	
	public static int getParkNumber() {
		return parkNumber;
	}
	
	public static void setParkNumber(final int theParkNumber) {
		parkNumber = theParkNumber;
	}
	
	
	public static Job getJob() {
		return new Job(jobTitle, jobDescription, TextUI.getSelectedPark());
	}
	
	
	public static int getNumVolAccepted() {
		return numVol;
	}
	
	
	public static void setNumVolAccepted(final int theNumVol) {
		numVol = theNumVol;
	}
}
