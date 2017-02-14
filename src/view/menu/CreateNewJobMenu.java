package view.menu;

import java.time.LocalDateTime;

import model.Controller;
import model.Job;
import view.TextUI;
import view.menu.Menu;
import view.menu.MenuEnum;
import view.menu.MenuUtils;
import view.menu.items.MenuItem;

public class CreateNewJobMenu implements Menu {
	
	private static CreateNewJobMenu INSTANCE;
	
	private String jobTitle;
	private LocalDateTime jobStartDate;
	private LocalDateTime jobEndDate;
	private String jobDescription;
	private int parkNumber;
	private int numVol;
	private boolean flag;

	@Override
	public void activate() {
		INSTANCE = this;
		flag = true;
		do {
			MenuUtils.printHeader(MenuEnum.CREATE_NEW_JOB.getTitle());
			System.out.format("Make a selection to edit the field or navigate:\n");
			final MenuItem item = MenuUtils.menu(MenuEnum.CREATE_NEW_JOB);
			item.activate();
		} while (flag);
		TextUI.back();
	}
	
	public static void clear() {
		
		INSTANCE.flag = false;
	}
	
	public static String getJobTitle() {
		return INSTANCE.jobTitle;	
	}
	
	public static void setJobTitle(final String theJobTitle) {
		INSTANCE.jobTitle = theJobTitle;
	}
	
	
	public static LocalDateTime getJobStartDate() {
		return INSTANCE.jobStartDate;	
	}
	
	public static LocalDateTime getJobEndDate() {
		return INSTANCE.jobEndDate;	
	}
	
	public static void setJobStartDate(final LocalDateTime theJobDate) {
		INSTANCE.jobStartDate = theJobDate;
	}
	
	public static void setJobEndDate(final LocalDateTime theJobDate) {
		INSTANCE.jobEndDate = theJobDate;
	}
	
	
	public static String getJobDescription() {
		return INSTANCE.jobDescription;
	}
	
	public static void setJobDescription(final String theJobDescription) {
		INSTANCE.jobDescription = theJobDescription;
	}
	
	
	public static int getParkNumber() {
		return INSTANCE.parkNumber;
	}
	
	public static void setParkNumber(final int theParkNumber) {
		INSTANCE.parkNumber = theParkNumber;
	}
	
	
	public static Job getJob() {
		final Job job = new Job(INSTANCE.jobTitle, INSTANCE.jobDescription, Controller.getPark(INSTANCE.parkNumber));
		job.setStartTime(CreateNewJobMenu.getJobStartDate());
		job.setEndTime(CreateNewJobMenu.getJobEndDate());
		return job;
	}
	
	
	public static int getNumVolAccepted() {
		return INSTANCE.numVol;
	}
	
	
	public static void setNumVolAccepted(final int theNumVol) {
		INSTANCE.numVol = theNumVol;
	}
}