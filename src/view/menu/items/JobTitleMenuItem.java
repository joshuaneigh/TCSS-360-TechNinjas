package view.menu.items;


import model.UserType;
import view.menu.CreateNewJobMenu;
import view.menu.MenuUtils;

public class JobTitleMenuItem implements MenuItem {
	
	@Override
	public void activate() {
		System.out.println("Please enter the new value: ");
		final String input = MenuUtils.input();
		CreateNewJobMenu.setJobTitle(input);
	}
	
	@Override
	public boolean isAllowed(final UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Job Title: ";
	}

	public static void main(String[] args) {
		JobTitleMenuItem menu = new JobTitleMenuItem();
		menu.activate();
	}
}
