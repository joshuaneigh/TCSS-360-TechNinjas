package model;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Objects;

/**
 * A calendar of the upcoming jobs for a month.
 * @author Michael Loundagin
 */
public class Calendar {

    /**
     * The months of a year.
     */
    private static final String[] MONTH_NAMES = {"January", "February", "March",
                                                 "April", "May", "June",
                                                 "July", "August", "September",
                                                 "October", "November", "December"};

    /**
     * The number of days in a week.
     */
    private static final int DAYS_IN_WEEK = 7;
    
    /**
     * The value when a day should not be displayed.
     */
    private static final int NONDISPLAYED_DAY = -1;

    /**
     * The header for any Calendar containing abbreviations of the weeks days.
     */
    private static final String CALENDAR_HEADER = "   Su      M      T      W      T      F      S\n";

    /**
     * The start of a month header.
     */
    private static final String MONTH_HEADER_START = "               [";

    /**
     * The end of a month header.
     */
    private static final String MONTH_HEADER_END = "]\n";

    /**
     * The filler for a nondisplayed day.
     */
    private static final String EMPTY_DAY = "      |";
    
    /**
     * The current date.
     */
    private LocalDateTime currentDate = LocalDateTime.now();

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
        final int[][] jobsByCalendarDays = createJobCalendar();
        final StringBuilder returnString = new StringBuilder(512);
        returnString.append(CALENDAR_HEADER);
        returnString.append(createMonthHeader(currentDate.getMonth()));
        LocalDateTime iterationDate = currentDate;
        //set the date back to the last Sunday
        iterationDate = iterationDate.minusDays(daysSinceSunday(iterationDate));
        //iterate over each week
        for (int row = 0; row < jobsByCalendarDays.length; row++, iterationDate = iterationDate.plusDays(7)) {
        	returnString.append(createWeekLine(jobsByCalendarDays[row], iterationDate));
            //if this is not the last row, add a line break
            if (row < jobsByCalendarDays.length - 1) {
                returnString.append("\n");
            }
        }
        return returnString.toString();
    }

    /**
     * Returns how many days since the last Sunday the given date is.
     * @param theDate The given date
     * @return How many days since the last Sunday the given date is
     */
    private int daysSinceSunday(final LocalDateTime theDate) {
        //A DayOfWeek enum is Monday = 1, Sunday = 7
        //This method makes it Sunday = 0, Saturday = 6
        return theDate.getDayOfWeek().getValue() % 7;
    }

    /**
     * Returns the date one month later.
     * @param theDate The given date
     * @return The date one month later than the given date.
     */
    public LocalDateTime monthLater(final LocalDateTime theDate) {
        LocalDateTime monthLater = theDate.plusMonths(1);
        /*
         * If the given date's day of month is nonexistent in the next month, monthLater will be off
         * by one day. This can be fixed by adding a day.
         */
        if (monthLater.getDayOfMonth() != theDate.getDayOfMonth()) {
            monthLater = monthLater.plusDays(1);
        }
        return monthLater;
    }

    /**
     * Finds the size of the calendar from the starting date to the ending date in rows.
     * If the startDate is erroneously in future of the endDate, the calendar will be from
     * end to start.
     * @param theStartDate The starting date
     * @param theEndDate The ending date
     * @return The size of the calendar in rows
     */
    public int calendarSize(LocalDateTime startDate, LocalDateTime endDate) {
        //If the startDate is after the endDate, swap the arguments
        if (startDate.compareTo(endDate) > 0) {
            final LocalDateTime tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }
        final int startDateDaysSinceSunday = daysSinceSunday(startDate);
        //Addition is for the startDate itself!
        final int totalDays = (int) startDate.until(endDate, ChronoUnit.DAYS) + 1;
        //Addition is because we need at least one row!
        final int calendarSize = (startDateDaysSinceSunday + totalDays - 1) / DAYS_IN_WEEK + 1;
        return calendarSize;
    }



    /**
     * Creates an array representing the calendar of jobs for the upcoming month. The columns are
     * days of the week starting from Sunday. Each day contains the number of jobs occurring that
     * day. If a day is not shown in this calendar, it is set as NONDISPLAYED_DAY.
     * @return The calendar of jobs for the upcoming month.
     */
    public int[][] createJobCalendar() {
        final int currentDateDaysSinceSunday = daysSinceSunday(currentDate);
        final LocalDateTime monthLater = monthLater(currentDate);
        final int calendarRows = calendarSize(currentDate, monthLater.minusDays(1));
        final int[][] jobsByCalendarDays = new int[calendarRows][DAYS_IN_WEEK];
        for (final Job job : jobs) {
            final LocalDateTime jobDate = job.getStartTime();
            /*
             * If this job's date is between the current date (inclusive) and a month later
             * (exclusive), then it is in our calendar.
             */
            if (jobDate.compareTo(currentDate) >= 0 && jobDate.compareTo(monthLater) < 0) {
                final int jobDateCalendarPosition = currentDateDaysSinceSunday
                                                    + (int) currentDate.until(jobDate, ChronoUnit.DAYS);
                final int row = jobDateCalendarPosition / DAYS_IN_WEEK;
                final int col = jobDateCalendarPosition % DAYS_IN_WEEK;
                jobsByCalendarDays[row][col]++;
            }
        }
        //mark the days before the current date as nondisplayed
        for (int col = 0; col < currentDateDaysSinceSunday; col++) {
            jobsByCalendarDays[0][col] = NONDISPLAYED_DAY;
        }
        //mark the days after and including one month later as nondisplayed
        for (int col = DAYS_IN_WEEK - 1; col >= daysSinceSunday(monthLater); col--) {
            jobsByCalendarDays[calendarRows - 1][col] = NONDISPLAYED_DAY;
        }
        return jobsByCalendarDays;
    }

    /**
     * Creates the header for the given month.
     * @param theMonth The given month
     * @return The header for the given month
     */
    private String createMonthHeader(final Month theMonth) {
        final String monthName = MONTH_NAMES[theMonth.getValue() - 1];
        return MONTH_HEADER_START + monthName + MONTH_HEADER_END;
    }
    
    /**
     * Creates the string representing a week on the calendar
     * @param theWeekArray The array of the number of jobs each day for the week
     * @param theSundayDate The date of the given week's Sunday
     * @return The string representation of the given week array
     * @throws IllegalArgumentException If the given Sunday date is not a Sunday
     */
    public String createWeekLine(final int[] theWeekArray, final LocalDateTime theSundayDate) {
    	if (daysSinceSunday(theSundayDate) > 0) {
    		throw new IllegalArgumentException("Given Sunday date is not a Sunday!");
    	}
    	//create a copy of theSundayDate for naming conventions
    	LocalDateTime iterationDate = LocalDateTime.from(theSundayDate);
    	final StringBuilder weekString = new StringBuilder(128);
    	/*
    	 * If the next month has been entered already, we must make the header before starting the
    	 * next week.
    	 */
    	if (checkEnteredNextMonth(iterationDate)) {
    		weekString.append(createMonthHeader(iterationDate.getMonth()));
    	}
    	//start of the week
    	weekString.append("|");
        //iterate over each day
        for (int col = 0; col < DAYS_IN_WEEK; col++, iterationDate = iterationDate.plusDays(1)) {
            //if the current day should not be displayed
            if (theWeekArray[col] == NONDISPLAYED_DAY) {
            	//NOTE that the date does not increment!
                weekString.append(EMPTY_DAY);
            //if it should be displayed
            } else {
                /*
                 * If the next month has just been entered, we must break to the next row and
                 * add the header for the next month. Note: This only fires if the day isn't
                 * Sunday since it would conflict with the check outside of this loop.
                 */
                if (checkEnteredNextMonth(iterationDate) && daysSinceSunday(iterationDate) > 0) {
                    //fill in the remaining days in the row as empty
                    weekString.append(enterNextMonth(col, iterationDate.getMonth()));
                }
                weekString.append(createDaySlot(theWeekArray[col], iterationDate));
                //END IF-ELSE
            }
            //END FOR COLUMN
        }
        return weekString.toString();
    }
    
    /**
     * Checks if the iteration has entered the next month.
     * Specifically, this checks if the day of month is 1 and if the month isn't the
     * same as the current date's.
     * @param theDate The date to check
     * @return If the date has entered the next month
     */
    private boolean checkEnteredNextMonth(final LocalDateTime theDate) {
    	return theDate.getDayOfMonth() == 1
                && !theDate.getMonth().equals(currentDate.getMonth());
    }
    
    /**
     * Creates the string representing a day on the calendar.
     * @param theNumOfJobs The number of jobs for the day
     * @param theDate The date of the day
     * @return The string representation of the calendar day
     */
    private String createDaySlot(final int theNumOfJobs, final LocalDateTime theDate) {
    	final StringBuilder dayString = new StringBuilder(8);
    	//insert " dd:j |" where 'dd' is the day of month and 'j' is jobs for the day
        dayString.append(String.format("%1$3d", theDate.getDayOfMonth())
            + ":" + theNumOfJobs + " |");
        return dayString.toString();
    }
    
    /**
     * Creates the string representing the end of a month going into the beginning of the next.
     * DO NOT USE THIS IF THE DAY OF WEEK OF THE FIRST DAY OF NEXT MONTH IS A SUNDAY.
     * Another instruction flow is supposed to handle that case.
     * @param dayOfWeek The day of the week, where Sunday = 0 and Saturday = 6
     * @param nextMonth The next month. Used for the month's header
     * @return
     */
    public String enterNextMonth(final int dayOfWeek, final Month nextMonth) {
    	final StringBuilder nextMonthString = new StringBuilder(64);
    	for (int col2 = dayOfWeek; col2 < DAYS_IN_WEEK; col2++) {
            nextMonthString.append(EMPTY_DAY);
        }
        nextMonthString.append("\n");
        //insert header for next month. iterationDate currently is in next month
        nextMonthString.append(createMonthHeader(nextMonth));
        nextMonthString.append("|");
        //fill in enough days to align with the correct day of week
        for (int col2 = 0; col2 < dayOfWeek; col2++) {
            nextMonthString.append(EMPTY_DAY);
        }
        return nextMonthString.toString();
    }
}
