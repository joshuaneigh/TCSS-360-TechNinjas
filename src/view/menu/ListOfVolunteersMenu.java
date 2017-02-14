package view.menu;

import java.util.List;

import model.Job;
import model.User;
import view.TextUI;

public class ListOfVolunteersMenu implements Menu{

	@Override
	public void activate() {
		MenuUtils.printHeader("List of Volunteers | Park %d | Job %s - %s");
		final Job job = TextUI.getSelectedJob();
		List<User> volunteers = job.getVolunteers();
		for (User volunteer : volunteers) {
			System.out.println(volunteer);
		}
	}
}