package view.menu.items;

import java.util.Arrays;
import java.util.List;

import model.UserType;

/**
 * An enumeration of all menu items. This enum is deprecated and is not to be
 * replaced.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 12 Feb 2017
 */
@Deprecated
public enum MenuItemEnum {
	
	CREATE_NEW_JOB("Create New Job", 
		Arrays.asList(UserType.ParkManager)),
	
	MANAGE_PARKS("Manage Parks", 
		Arrays.asList(UserType.ParkManager)),
	
	JOB_TITLE("Job Title: %s", 
		Arrays.asList(UserType.ParkManager)),
	
	JOB_DATE("Job Date: %s", 
		Arrays.asList(UserType.ParkManager)),
	
	PARK_NUMBER("Park Number: %s", 
		Arrays.asList(UserType.ParkManager)),
	
	JOB_DESCRIPTION("Job Description: %s", 
		Arrays.asList(UserType.ParkManager)),
	
	NUM_VOL_ACCEPTED("Number of Volunteers Accepted: %s", 
		Arrays.asList(UserType.ParkManager)),
	
	SUBMIT_JOB("Submit Job", 
		Arrays.asList(UserType.ParkManager)),
	
	VIEW_JOBS("View Jobs",
		Arrays.asList(UserType.ParkManager)),
	
	VIEW_VOLUNTEERS("View Volunteers",
		Arrays.asList(UserType.ParkManager)),
	
	VIEW_UPCOMING_JOBS("View Upcoming Jobs",
		Arrays.asList(UserType.UrbanPark)),
	
	SEARCH_FOR_JOBS("Search For Jobs",
		Arrays.asList(UserType.Volunteer)),
	
	VIEW_MY_JOBS("View My Jobs",
		Arrays.asList(UserType.Volunteer)),
	
	MAKE_ANOTHER_SEARCH("Make Another Search",
		Arrays.asList(UserType.Volunteer)),
	
	SIGN_UP_FOR_JOB("Sign Up For Job",
		Arrays.asList(UserType.Volunteer)),
	
	BACK("Back", 
		Arrays.asList(UserType.ParkManager, UserType.UrbanPark, UserType.Volunteer)),
	
	EXIT("Exit", 
		Arrays.asList(UserType.ParkManager, UserType.UrbanPark, UserType.Volunteer));
	
	private final String text;
	private final List<UserType> allowedTypes;
	
	MenuItemEnum(final String text, final List<UserType> allowedTypes) {
		this.text = text;
		this.allowedTypes = allowedTypes;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isAllowed(final UserType type) {
		return allowedTypes.contains(type);
	}

}
