package test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Calendar;
import model.Job;

/**
 * Testing class for Calendar.
 * @author Michael Loundagin
 */
public class CalendarTest {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    private static final List<Job> listOfJobs = new ArrayList<Job>();
    
    private static final Calendar GENERAL_CALENDAR = new Calendar(listOfJobs);
    
    @BeforeClass
    public static void init() {
        final Job januaryJob = new Job("", "", null);
        januaryJob.setStartTime(LocalDateTime.of(2017, 1, 13, 13, 00));
        listOfJobs.add(januaryJob);
        
        final Job februaryJob = new Job("", "", null);
        februaryJob.setStartTime(LocalDateTime.of(2017, 2, 1, 8, 00));
        listOfJobs.add(februaryJob);
        
        final Job februaryJobSameDay = new Job("", "", null);
        februaryJobSameDay.setStartTime(LocalDateTime.of(2017,  2, 1, 6, 00));
        listOfJobs.add(februaryJobSameDay);
        
        final Job januaryJob2 = new Job("", "", null);
        februaryJobSameDay.setStartTime(LocalDateTime.of(2017,  1, 22, 6, 00));
        listOfJobs.add(januaryJob2);
    }
    
    @Test
    public void toString_startOfMonth_displayOneMonth() {
        final Calendar januaryCalendar = new Calendar(listOfJobs);
        januaryCalendar.setCurrentDate(LocalDateTime.parse("2017-01-01 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [January]\n"
                              + "|  1:0 |  2:0 |  3:0 |  4:0 |  5:0 |  6:0 |  7:0 |\n"
                              + "|  8:0 |  9:0 | 10:0 | 11:0 | 12:0 | 13:1 | 14:0 |\n"
                              + "| 15:0 | 16:0 | 17:0 | 18:0 | 19:0 | 20:0 | 21:0 |\n"
                              + "| 22:1 | 23:0 | 24:0 | 25:0 | 26:0 | 27:0 | 28:0 |\n"
                              + "| 29:0 | 30:0 | 31:0 |      |      |      |      |";
        assertEquals("Unable to correctly display a single month.", expected, januaryCalendar.toString());
    }
    
    @Test
    public void toString_dayOfMonthExistsInNextMonth_displayTwoMonths() {
        final Calendar januaryFebruaryCalendar = new Calendar(listOfJobs);
        januaryFebruaryCalendar.setCurrentDate(LocalDateTime.parse("2017-01-10 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [January]\n"
                              + "|      |      | 10:0 | 11:0 | 12:0 | 13:1 | 14:0 |\n"
                              + "| 15:0 | 16:0 | 17:0 | 18:0 | 19:0 | 20:0 | 21:0 |\n"
                              + "| 22:1 | 23:0 | 24:0 | 25:0 | 26:0 | 27:0 | 28:0 |\n"
                              + "| 29:0 | 30:0 | 31:0 |      |      |      |      |\n"
                              + "               [February]\n"
                              + "|      |      |      |  1:2 |  2:0 |  3:0 |  4:0 |\n"
                              + "|  5:0 |  6:0 |  7:0 |  8:0 |  9:0 |      |      |";
        assertEquals("Unable to correctly display two months.", expected, januaryFebruaryCalendar.toString());
    }
    
    @Test
    public void toString_yearIsLeapYearAndMonthIsFebruary_displayFebruaryWith29Days() {
        final Calendar leapYearCalendar = new Calendar(listOfJobs);
        leapYearCalendar.setCurrentDate(LocalDateTime.parse("2016-02-01 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [February]\n"
                              + "|      |  1:0 |  2:0 |  3:0 |  4:0 |  5:0 |  6:0 |\n"
                              + "|  7:0 |  8:0 |  9:0 | 10:0 | 11:0 | 12:0 | 13:0 |\n"
                              + "| 14:0 | 15:0 | 16:0 | 17:0 | 18:0 | 19:0 | 20:0 |\n"
                              + "| 21:0 | 22:0 | 23:0 | 24:0 | 25:0 | 26:0 | 27:0 |\n"
                              + "| 28:0 | 29:0 |      |      |      |      |      |";
        assertEquals("Unable to correctly display February during a leap year.", expected, leapYearCalendar.toString());
    }
    
    @Test
    public void monthLater_dayOfMonthDoesNotExistInNextMonth_returnedDateIsPastAllOfNextMonth() {
        assertEquals("Unable to return a month later when the current day of the month is nonexistent in the next month.",
            LocalDateTime.parse("2017-03-01 00:00", DATE_TIME_FORMAT),
            GENERAL_CALENDAR.monthLater(LocalDateTime.parse("2017-01-31 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void calendarSize_monthIsFebruaryAndMonthFitsIntoFourCalendarWeeks_returnedRowsIsFour() {
    	assertEquals("Attempted to use more than four rows for a February which could be stored in four rows.",
    			4, GENERAL_CALENDAR.calendarSize(LocalDateTime.parse("2015-02-01 00:00", DATE_TIME_FORMAT),
    					LocalDateTime.parse("2015-02-28 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void calendarSize_endDateIsBeforeStartDate_returnedResultIsSameAsIfArgumentsWereSwapped() {
    	assertEquals("Could not properly swap the arguments.",
    			GENERAL_CALENDAR.calendarSize(LocalDateTime.parse("2017-01-16 00:00", DATE_TIME_FORMAT),
    					LocalDateTime.parse("2017-02-16 00:00", DATE_TIME_FORMAT)),
    			GENERAL_CALENDAR.calendarSize(LocalDateTime.parse("2017-02-16 00:00", DATE_TIME_FORMAT),
    					LocalDateTime.parse("2017-01-16 00:00", DATE_TIME_FORMAT)));
    }
    
    //unfortunately, there is not really a good way to go about testing this method case-wise
    @Test
    public void createJobCalendar_generalTest_integersCorrespondToIfCalendarDayIsDisplayedOrNumberOfJobs() {
    	final Calendar arrayCreationCalendar = new Calendar(listOfJobs);
        arrayCreationCalendar.setCurrentDate(LocalDateTime.parse("2017-01-10 00:00", DATE_TIME_FORMAT));
        final String errorMessage = "Job array not created properly in row ";
        final int[][] jobArray = arrayCreationCalendar.createJobCalendar();
        assertArrayEquals(errorMessage + 0, new int[] {-1, -1, 0, 0, 0, 1, 0}, jobArray[0]);
        assertArrayEquals(errorMessage + 1, new int[] {0, 0, 0, 0, 0, 0, 0}, jobArray[1]);
        assertArrayEquals(errorMessage + 2, new int[] {1, 0, 0, 0, 0, 0, 0}, jobArray[2]);
        assertArrayEquals(errorMessage + 3, new int[] {0, 0, 0, 2, 0, 0, 0}, jobArray[3]);
        assertArrayEquals(errorMessage + 4, new int[] {0, 0, 0, 0, 0, -1, -1}, jobArray[4]);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void createWeekLine_sundayDateIsNotSunday_throwsIllegalArgumentException() {
    	GENERAL_CALENDAR.createWeekLine(new int[] {-1, -1, -1, -1, -1, -1, 0},
    			LocalDateTime.parse("2017-02-14 00:00", DATE_TIME_FORMAT));
    }
    
    @Test
    public void createWeekLine_firstDisplayedDayIsSaturday_allDaysEmptyExceptSaturday() {
    	assertEquals("Failed to display only Saturday.",
    	    "|      |      |      |      |      |      | 18:0 |",
    	    GENERAL_CALENDAR.createWeekLine(new int[] {-1, -1, -1, -1, -1, -1, 0},
    	        LocalDateTime.parse("2017-02-12 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void createWeekLine_lastDisplayedDayIsSunday_allDaysEmptyExceptSunday() {
    	assertEquals("Failed to display only Sunday.",
    	    "| 19:0 |      |      |      |      |      |      |",
    	    GENERAL_CALENDAR.createWeekLine(new int[] {0, -1, -1, -1, -1, -1, -1},
    	        LocalDateTime.parse("2017-02-19 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void createWeekLine_enteringNextMonth_nextMonthTrasitionPerformed() {
    	final Calendar enteringNextMonthCalendar = new Calendar(listOfJobs);
    	enteringNextMonthCalendar.setCurrentDate(LocalDateTime.parse("2017-01-15 00:00", DATE_TIME_FORMAT));
    	assertEquals("Failed to generate week with month transition.",
    	    "| 29:0 | 30:0 | 31:0 |      |      |      |      |\n"
    	  + "               [February]\n"
    	  + "|      |      |      |  1:0 |  2:0 |  3:0 |  4:0 |",
    	    enteringNextMonthCalendar.createWeekLine(new int[] {0, 0, 0, 0, 0, 0, 0},
    	        LocalDateTime.parse("2017-01-29 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void createWeekLine_enteringNextMonthDuringSunday_nextMonthHeaderGenerated() {
    	final Calendar enteringNextMonthCalendar = new Calendar(listOfJobs);
    	enteringNextMonthCalendar.setCurrentDate(LocalDateTime.parse("2016-12-28 00:00", DATE_TIME_FORMAT));
    	assertEquals("Failed to generate week with month transition.",
    	    "               [January]\n"
    	  + "|  1:0 |  2:0 |  3:0 |  4:0 |  5:0 |  6:0 |  7:0 |",
    	    enteringNextMonthCalendar.createWeekLine(new int[] {0, 0, 0, 0, 0, 0, 0},
    	        LocalDateTime.parse("2017-01-01 00:00", DATE_TIME_FORMAT)));
    }
    
    @Test
    public void enterNextMonth_generalTest_generateTransition() {
    	assertEquals("Failed to generate month transition.",
            "      |      |      |      |\n"
          + "               [February]\n"
          + "|      |      |      |",
    	    GENERAL_CALENDAR.enterNextMonth(3, Month.FEBRUARY));
    }
    
    @Test
    public void enterNextMonth_nextMonthIsJanuary_generateTransitionOverYear() {
    	assertEquals("Failed to generate month transition across years.",
            "      |      |      |      |      |      |\n"
          + "               [January]\n"
          + "|      |",
    	    GENERAL_CALENDAR.enterNextMonth(1, Month.JANUARY));
    }

}
