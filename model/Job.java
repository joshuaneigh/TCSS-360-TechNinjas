package model;
import java.util.List;

/**
 * 
 * @author Jasmine Dacones
 *
 */
public class Job {
	int MAX_VOLUME = 30;
	int MAX_LENGTH = 2;
	
	private String desc;
	private Park park;
	private List<UserType> volunteers;
	private DateAndTime start;
	private DateAndTime end;
	
	public Job(String theDesc, Park thePark, DateAndTime theStart, DateAndTime theEnd ) {
		desc = theDesc;
		park = thePark;
		start = theStart;
		end = theEnd;
	}
}
