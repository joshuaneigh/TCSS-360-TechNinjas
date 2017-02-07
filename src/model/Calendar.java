/**
 * 
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * A calendar of the upcoming jobs for a month.
 * @author John Bako, Michael Loundagin
 */
public class Calendar {

    /**
     * The number of days in a week.
     */
    private static final int DAYS_IN_WEEK = 7;
    
    /**
     * The value when a day should not be displayed.
     */
    private static final int NONDISPLAYED_DAY = -1;
    
    /**
     * The format expected from Job.getStartTime().
     */
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * The current date.
     */
    private LocalDateTime currentDate;
    
    /**
     * Used across helper methods.
     */
    private LocalDateTime endDate;

    /**
     * The list of all jobs.
     */
    private List<Job> jobs;
	
    /**
     * Constructs a calendar of jobs for the coming month.
     * @param theJobs The list of jobs.
     */
    public Calendar(final List<Job> theJobs) {
    	Objects.isNull(theJobs);
        jobs = theJobs;
    }
    
    /**
     * Sets the current date. This is for debugging and should not be used otherwise.
     * @param theCurrentDate The new current date
     */
    public void setCurrentDate(final LocalDateTime theCurrentDate) {
    	currentDate = theCurrentDate;
    }
	
    /**
     * Formats a calendar of jobs for the coming month.
     * @return A formatted calendar of jobs for the coming month
     */
	public String toString() {
        final int numOfWeekRows = calculateNumOfWeekRows(currentDate);
        final int[][] jobsForCalendarDays = createJobCalendar(currentDate, numOfWeekRows);
        String returnString = "   Su      M      T      W      T      F      S\n";
        returnString += "               [" + currentDate.getMonth().toString() + "]\n";
        returnString += "|";
        final LocalDateTime iterDate = LocalDateTime.now();
        final Month currentMonth = currentDate.getMonth();
        boolean nextMonthFlag = false;
        for (int i = 0; i < numOfWeekRows; i++) {
        	for (int j = 0; j < DAYS_IN_WEEK; j++) {
        		//if we've entered the next month before setting the flag, we need to add the next month header
        		if (!(nextMonthFlag && iterDate.getMonth().equals(currentMonth))) {
        			for (int k = j; k < DAYS_IN_WEEK; k++) {
        				returnString += "      |";
        			}
        			returnString += "\n               [" + iterDate.getMonth().toString() + "]\n";
        			returnString += "|";
        			for (int k = 0; k < j; k++) {
        				returnString += "      |";
        			}
        		}
        		returnString += " " + String.format("%1$2d", iterDate.getDayOfMonth()) + ":" + jobsForCalendarDays[i][j] + " |";
        	}
        	returnString += "\n|";
        }
        return returnString;
	}
	
	private int calculateNumOfWeekRows(final LocalDateTime currentDate) {
		int numOfWeekRows = 0;
		final LocalDateTime iterDate = LocalDateTime.now().plusDays(1);
		final int currentDayOfMonth = currentDate.getDayOfMonth();
        while (iterDate.getDayOfMonth() != currentDayOfMonth) {
        	if (iterDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
        		numOfWeekRows++;
        	}
        	iterDate.plusDays(1);
        }
        endDate = iterDate;
        return numOfWeekRows;
	}
	
	private int[][] createJobCalendar(final LocalDateTime currentDate, final int numOfWeekRows) {
        //create array to store number of jobs for each date
		final int[][] jobsForCalendarDays = new int[numOfWeekRows][DAYS_IN_WEEK];
        //iterate over the jobs to add the appropriate days
		final int currentDayOfMonth = currentDate.getDayOfMonth();
        final Month currentMonth = currentDate.getMonth();
        for (final Job job : jobs) {
            final LocalDateTime jobDate = LocalDateTime.parse(job.getStartTime(), DATE_TIME_FORMAT);
            final int jobDay = jobDate.getDayOfMonth();
            final Month jobMonth = jobDate.getMonth();
            //test if the job occurs within the coming month
            if (jobMonth.equals(currentMonth) && jobDay >= currentDayOfMonth
                    || jobMonth.plus(1).equals(currentMonth) && jobDay < currentDayOfMonth) {
                //increment the position that is the number of days away from the current day
                final int numOfDaysPastCurrentDay = (int) currentDate.until(jobDate, ChronoUnit.DAYS);
                final int row = numOfDaysPastCurrentDay / numOfWeekRows;
                final int col = numOfDaysPastCurrentDay % DAYS_IN_WEEK;
                jobsForCalendarDays[row][col]++;
            }
        }
        
        //Shift values over by the number of prior days in the first week
        final int currentDayOfWeekInt = currentDate.getDayOfWeek().getValue() - 1;
        //shift the last row
        for (int j = DAYS_IN_WEEK - currentDayOfWeekInt; j >= 0; j--) {
        	jobsForCalendarDays[numOfWeekRows][j + currentDayOfWeekInt] = jobsForCalendarDays[numOfWeekRows][j];
        }
        //other rows
        for (int i = numOfWeekRows - 2; i >= 0; i--) {
        	for (int j = DAYS_IN_WEEK; j >= 0; j--) {
        		if (j > DAYS_IN_WEEK - currentDayOfWeekInt) {
        			jobsForCalendarDays[i + 1][(j + currentDayOfWeekInt) % DAYS_IN_WEEK] = jobsForCalendarDays[i][j];
        		} else {
        			jobsForCalendarDays[i][j + currentDayOfWeekInt] = jobsForCalendarDays[i][j];
        		}
        	}
        }
        
        //set the prior days of the first week row as nondisplayed
        for (DayOfWeek i = DayOfWeek.SUNDAY; i.compareTo(currentDate.getDayOfWeek()) < 0; i.plus(1)) {
        	jobsForCalendarDays[0][i.getValue() - 1] = NONDISPLAYED_DAY;
        }
        //set the days at and after one month in the future as nondisplayed
        for (DayOfWeek i = DayOfWeek.SATURDAY; i.compareTo(endDate.getDayOfWeek()) >= 0; i.minus(1)) {
        	jobsForCalendarDays[numOfWeekRows - 1][i.getValue() - 1] = NONDISPLAYED_DAY;
        }
        return jobsForCalendarDays;
	}

}
