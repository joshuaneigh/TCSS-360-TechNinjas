package view.menu;

import view.menu.items.*;

/**
 * An enumeration of all {@link Menu} titles and {@link MenuItem}s that are to be inside each {@link Menu}.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
public enum MenuEnum {
	
	LOGIN("Please Login\n", null),
	
	WELCOME("Welcome!\n", new MenuItem[] {
			new CreateNewJobMenuItem(),
			new ManageParksMenuItem(),
			new ViewUpcomingJobsMenuItem(),
			new SearchForJobsMenuItem(),
			new ViewMyJobsMenuItem(),
			new ChangeMaxJobsMenuItem(),
			new ExitMenuItem()
		}),
	
	CREATE_NEW_JOB("Create New Job\n", new MenuItem[] {
			new JobTitleMenuItem(),
			new JobDateMenuItem(),
			new ParkNumberMenuItem(),
			new JobDescriptionMenuItem(),
			new NumVolAcceptedMenuItem(),
			new SubmitJobMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	MANAGE_PARKS("Manage Parks\n", new MenuItem[] {
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	MANAGE_PARKS_PARK_SELECTED("Manage Parks | Park %s | %s, %s\n", new MenuItem[] {
			new ViewJobsMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	VIEW_JOBS("View Jobs | Park %d\n", new MenuItem[] {
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	VIEW_JOBS_JOB_SELECTED("View Jobs | Park %d | Job %s - %s\n", new MenuItem[] {
			new ViewVolunteersMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	LIST_OF_VOLUNTEERS("List of Volunteers | Park %d | Job %s - %s\n", null),
	
	UPCOMING_JOBS("Upcoming Jobs\n", new MenuItem[] {
			new MakeAnotherSearchMenuItem(),
			new SignUpForJobMenuItem(),
			new BackMenuItem(),
			new ExitMenuItem()
		}),
	
	SEARCH_FOR_JOBS("Search for Jobs\n", null),
	
	MY_JOBS("My Jobs\n", null);
	
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