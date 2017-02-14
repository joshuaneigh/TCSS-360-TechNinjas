package view.menu;

import java.util.List;

import model.Job;
import model.User;
import view.TextUI;

public class ListOfVolunteersMenu implements Menu{

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.LIST_OF_VOLUNTEERS.getTitle(), TextUI.getSelectedPark().getNumber(), TextUI.getSelectedJob().getJobTitle());
		final Job job = TextUI.getSelectedJob();
		
		List<User> volunteers = job.getVolunteers();
		if (volunteers.isEmpty())
			System.out.println("There are currently no volunteers for this job.");
		else 
			for (final User volunteer : volunteers) System.out.println(volunteer);
		
		System.out.println("\nPress [Enter] to return... ");
		MenuUtils.input();
		TextUI.back();
		System.out.println("SHOULD NEVER GET HERE");
	}
}