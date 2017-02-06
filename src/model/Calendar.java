/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author John Bako
 *
 */
public class Calendar {

        private static final String[] MONTH_NAMES = ["January", "February",
                "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"];

        private static final MAX_DAYS_OF_MONTH = 31;
	
	private static int myMonth;
	
	private static int myYear;
	
	public Calender() {
                final Calendar currentDate = Calendar.getInstance();
		this(currentDate.get(Calendar.MONTH) + 1,
                     currentDate.get(Calendar.YEAR));
	}

	public Calender(int theDay, int myMonth2, int theYear) {
		myMonth = myMonth2;
		myYear = theYear;
	}
	
	public int getMonth() {
		return myMonth;
	}
	
	public int getYear() {
		return myYear;
	}
	
	public void setMonth(int themonth) {
		myMonth = themonth;
	}
	
	public void setYear(int theYear) {
		myYear = theYear;
	}
	
	public String toString() {
                //jobs are stored by day into this array
                final ArrayList<Job>[] jobsByDay
                        = new ArrayList<Job>[MAX_DAYS_OF_MONTH];
                boolean hasJobs;
                for (ArrayList<Job> jobsList : jobsByDay) {
                        jobsList = new ArrayList<Job>();
                }
                for (final Park park : Controller.getParks()) {
                        for (final Job job : park.getJobs()) {
                                final jobDate = job.getStart()
                                if (jobDate.getMonth() == myMonth) {
                                        jobsByDay[jobDate.getDay()].add(job);
                                        hasJobs = true;
                                }
                        }
                }
                String returnString = "";
                monthName = MONTH_NAMES[myMonth];
                if (!hasJobs) {
                        returnString = "There are no jobs for" + monthName
                               + " " + myYear;
                } else {
                        returnString = "Jobs for" + monthName + " "
                        + myYear + "\n";
                        boolean dayFormatFlag;
                        for (int day = 0; i < MAX_DAYS_OF_MONTH; i++) {
                                final ArrayList<Job> jobsForDay = jobsByDay.get(i);
                                if (dayFormatFlag) {
                                        returnString += "\n\n"
                                }
                                if (!(jobsForDay.isEmpty())) {
                                        boolean jobFormatFlag;
                                        returnString += "    " + monthName
                                                + " " + (day + 1) + "\n";
                                        for (final Job job : jobsForDay) {
                                                if (jobFormatFlag) {
                                                        returnString += "\n\n"
                                                }
                                                returnString += "        "
                                                        + job.getPark() + "\n"
                                                        + job.getDesc()
                                                jobFormatFlag = true;
                                        }
                                        dayFormatFlag = true;
                                }
                        }
                }
                return returnString;
        }

}
