package test;

import java.time.LocalDateTime;
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
    
    @BeforeClass
    public static void init() {
        final Job januaryJob = new Job("", null);
        januaryJob.setStartTime("2017-01-13 13:00");
        listOfJobs.add(januaryJob);
        
        final Job februaryJob = new Job("", null);
        februaryJob.setStartTime("2017-02-03 08:00");
        listOfJobs.add(februaryJob);
    }
    
    @Test
    public void testSingleMonthDisplay() {
        final Calendar januaryCalendar = new Calendar(listOfJobs);
        januaryCalendar.setCurrentDate(LocalDateTime.parse("2017-01-01 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [January]\n"
                              + "|  1:0 |  2:0 |  3:0 |  4:0 |  5:0 |  6:0 |  7:0 |\n"
                              + "|  8:0 |  9:0 | 10:0 | 11:0 | 12:0 | 13:1 | 14:0 |\n"
                              + "| 15:0 | 16:0 | 17:0 | 18:0 | 19:0 | 20:0 | 21:0 |\n"
                              + "| 22:0 | 23:0 | 24:0 | 25:0 | 26:0 | 27:0 | 28:0 |\n"
                              + "| 29:0 | 30:0 | 31:0 |      |      |      |      |";
        assertEquals("Unable to correctly display a single month.", expected, januaryCalendar.toString());
    }
    
    @Test
    public void testDoubleMonthDisplay() {
        final Calendar januaryFebruaryCalendar = new Calendar(listOfJobs);
        januaryFebruaryCalendar.setCurrentDate(LocalDateTime.parse("2017-01-10 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [January]\n"
                              + "|      |      | 10:0 | 11:0 | 12:0 | 13:1 | 14:0 |\n"
                              + "| 15:0 | 16:0 | 17:0 | 18:0 | 19:0 | 20:0 | 21:0 |\n"
                              + "| 22:0 | 23:0 | 24:0 | 25:0 | 26:0 | 27:0 | 28:0 |\n"
                              + "| 29:0 | 30:0 | 31:0 |      |      |      |      |\n"
                              + "               [February]\n"
                              + "|      |      |      |  1:0 |  2:0 |  3:1 |  4:0 |\n"
                              + "|  5:0 |  6:0 |  7:0 |  8:0 |  9:0 |      |      |";
        assertEquals("Unable to correctly display two months.", expected, januaryFebruaryCalendar.toString());
    }
    
    @Test
    public void testLeapYearDisplay() {
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
    public void testCurrentDayOfMonthDoesNotExistInNextMonth() {
        final Calendar leapYearCalendar = new Calendar(listOfJobs);
        leapYearCalendar.setCurrentDate(LocalDateTime.parse("2017-01-31 00:00", DATE_TIME_FORMAT));
        final String expected = "   Su      M      T      W      T      F      S\n"
                              + "               [January]\n"
                              + "|      |      | 31:0 |      |      |      |      |\n"
                              + "               [February]\n"
                              + "|      |      |      |  1:0 |  2:0 |  3:1 |  4:0 |\n"
                              + "|  5:0 |  6:0 |  7:0 |  8:0 |  9:0 | 10:0 | 11:0 |\n"
                              + "| 12:0 | 13:0 | 14:0 | 15:0 | 16:0 | 17:0 | 18:0 |\n"
                              + "| 19:0 | 20:0 | 21:0 | 22:0 | 23:0 | 24:0 | 25:0 |\n"
                              + "| 26:0 | 27:0 | 28:0 |      |      |      |      |";
        assertEquals("Unable to correctly handle when the current day of the month is nonexistent in the next month.",
                     expected, leapYearCalendar.toString());
    }

}
