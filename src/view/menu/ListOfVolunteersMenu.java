package view.menu;

import java.util.List;

import view.TextUI;
import model.Job;
import model.User;
import model.UserType;

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
