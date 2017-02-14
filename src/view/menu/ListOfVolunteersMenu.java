package view.menu;

import java.util.List;

import model.Job;
import model.User;
import view.TextUI;

public class ListOfVolunteersMenu implements Menu{

	@Override
	public void activate() {
		MenuUtils.printHeader(MenuEnum.LIST_OF_VOLUNTEERS.getTitle());
		final Job job = TextUI.getSelectedJob();
		List<User> volunteers = job.getVolunteers();
		for (User volunteer : volunteers) {
			System.out.println(volunteer);
		}
	}
}