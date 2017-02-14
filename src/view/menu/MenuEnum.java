package view.menu;

import view.menu.items.*;

/**
 * An enumeration of all {@link Menu} titles and {@link MenuItem}s that are to be inside each {@link Menu}.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public enum MenuEnum {
	
	LOGIN("Please Login", null),
	
	WELCOME("Welcome!", new MenuItem[] {
			new CreateNewJobMenuItem(),
			new ManageParksMenuItem(),
//			new ViewUpcomingJobsMenuItem(),
			new SearchForJobsMenuItem(),
			new ViewMyJobsMenuItem(),
			new ExitMenuItem()
		}),
	
	CREATE_NEW_JOB("Create New Job", new MenuItem[] {
			new JobTitleMenuItem(),
			new JobDateMenuItem(),
			new ParkNumberMenuItem(),
			new JobDescriptionMenuItem(),
			new NumVolAcceptedMenuItem(),
			new SubmitJobMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	MANAGE_PARKS("Manage Parks", new MenuItem[] {
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	MANAGE_PARKS_PARK_SELECTED("Manage Parks | Park %s | %s, %s", new MenuItem[] {
			new ViewJobsMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	VIEW_JOBS("View Jobs | Park %d | %s - %s", new MenuItem[] {
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	VIEW_JOBS_JOB_SELECTED("View Jobs | Park %d | Job %s - %s", new MenuItem[] {
			new ViewVolunteersMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	LIST_OF_VOLUNTEERS("List of Volunteers | Park %d | Job %s - %s", null),
	
	UPCOMING_JOBS("Upcoming Jobs", new MenuItem[] {
			new MakeAnotherSearchMenuItem(),
			new SignUpForJobMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	SEARCH_FOR_JOBS("Search for Jobs", null),
	
	MY_JOBS("My Jobs", null);
	
	private final String title;
	private final MenuItem[] items;
	
	MenuEnum(final String title, final MenuItem[] items) {
		this.title = title;
		this.items = items;
	}
	
	public String getTitle() {
		return title;
	}
	
	public MenuItem[] getItems() {
		return items;
	}
	
}