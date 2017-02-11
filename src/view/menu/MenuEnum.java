package view.menu;

public enum MenuEnum {
	
	LOGIN("Please Login", null),
	
	WELCOME("Welcome!", new MenuItemEnum[] {
			MenuItemEnum.CREATE_NEW_JOB,
			MenuItemEnum.MANAGE_PARKS,
			MenuItemEnum.VIEW_UPCOMING_JOBS,
			MenuItemEnum.SEARCH_FOR_JOBS,
			MenuItemEnum.VIEW_MY_JOBS,
			MenuItemEnum.EXIT
		}),
	
	CREATE_NEW_JOB("Create New Job", new MenuItemEnum[] {
			MenuItemEnum.JOB_TITLE,
			MenuItemEnum.JOB_DATE,
			MenuItemEnum.PARK_NUMBER,
			MenuItemEnum.JOB_DESCRIPTION,
			MenuItemEnum.NUM_VOL_ACCEPTED,
			MenuItemEnum.SUBMIT_JOB,
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	MANAGE_PARKS("Create New Job", new MenuItemEnum[] {
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	MANAGE_PARKS_PARK_SELECTED("Create New Job", new MenuItemEnum[] {
			MenuItemEnum.VIEW_JOBS,
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	VIEW_JOBS("View Jobs | Park %d | %s - %s", new MenuItemEnum[] {
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	VIEW_JOBS_JOB_SELECTED("View Jobs | Park %d | Job %s - %s", new MenuItemEnum[] {
			MenuItemEnum.VIEW_VOLUNTEERS,
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	LIST_OF_VOLUNTEERS("List of Volunteers | Park %d | Job %s - %s", null),
	
	UPCOMING_JOBS("Upcoming Jobs", new MenuItemEnum[] {
			MenuItemEnum.MAKE_ANOTHER_SEARCH,
			MenuItemEnum.SIGN_UP_FOR_JOB,
			MenuItemEnum.BACK,
			MenuItemEnum.EXIT
		}),
	
	SEARCH_FOR_JOBS("Search for Jobs", null),
	
	MY_JOBS("My Jobs", null);
	
	private final String title;
	private final MenuItemEnum[] items;
	
	MenuEnum(final String text, final MenuItemEnum[] items) {
		this.title = text;
		this.items = items;
	}
	
	public String getTitle() {
		return title;
	}
	
	public MenuItemEnum[] getItems() {
		return items;
	}
	
}