package view.menu.items;

import model.UserType;
import view.menu.MenuEnum;
import view.menu.MenuUtils;

public class CreateNewJobMenuItem implements MenuItem {

	@Override
	public void activate() {
		MenuUtils.printHeader("Create New Job");
		System.out.format("Make a selection to edit the field or navigate:\n");
		MenuUtils.menu(MenuEnum.CREATE_NEW_JOB);
	}
	
	public static void main(String[] args) {
		CreateNewJobMenuItem menu = new CreateNewJobMenuItem();
	    menu.activate();
	}

	@Override
	public boolean isAllowed(UserType userType) {
		return true;
	}

	@Override
	public String getLabel() {
		return "Create New Job";
	}
}