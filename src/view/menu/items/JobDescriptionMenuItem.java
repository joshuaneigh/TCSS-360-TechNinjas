package view.menu.items;

import model.UserType;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class JobDescriptionMenuItem implements MenuItem {

	@Override
	public void activate() {
		System.out.println("Please enter the new value: ");
		final String input = MenuUtils.input();
		CreateNewJobMenu.setJobDescription(input);
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Job Description: " + (CreateNewJobMenu.getJobDescription() == null ? "" : CreateNewJobMenu.getJobDescription());

	}

}
