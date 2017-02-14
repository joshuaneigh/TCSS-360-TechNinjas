package view.menu.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.UserType;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class JobDateMenuItem implements MenuItem {

	private static final DateTimeFormatter FORMATTER;
	
	static {
		 FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	}
	
	@Override
	public void activate() {
		System.out.println("Please enter the starting date and time (yyyy-MM-dd HH:mm): ");
		final LocalDateTime start = LocalDateTime.parse(MenuUtils.input(), FORMATTER);
		System.out.println("Please enter the ending date and time (yyyy-MM-dd HH:mm): ");
		final LocalDateTime end = LocalDateTime.parse(MenuUtils.input(), FORMATTER);
		CreateNewJobMenu.setJobStartDate(start);
		CreateNewJobMenu.setJobEndDate(end);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		if (CreateNewJobMenu.getJobStartDate() == null)
			return "Dates: ";
		else if (CreateNewJobMenu.getJobEndDate() == null)
			return "Dates: ";
		else
			return "Dates: " + CreateNewJobMenu.getJobStartDate().format(FORMATTER) + " to "
					+ CreateNewJobMenu.getJobEndDate().format(FORMATTER);
	}

}