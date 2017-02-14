package view.menu;

import java.util.List;

import view.TextUI;
import model.Job;
import model.User;
import model.UserType;

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
