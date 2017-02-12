package view.menu;

public class CreateNewJobMenuItem implements Menu {

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
}
