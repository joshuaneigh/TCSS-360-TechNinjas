/**
 * 
 */
package model;

/**
 * @author John Bako
 *
 */
public class Calender {
	
	private static int myDay;
	
	private static int myMonth;
	
	private static int myYear;
	
	public Calender() {
		this(myDay, myMonth, myYear);
	}

	public Calender(int theDay, int myMonth2, int theYear) {
		myDay = theDay;
		myMonth = myMonth2;
		myYear = theYear;
	}
	
	public int getDay() {
		return myDay;
	}
	
	public int getMonth() {
		return myMonth;
	}
	
	public int getYear() {
		return myYear;
	}
	
	public void setDay(int theDay) {
		myDay = theDay;	
	}
	
	public void setMonth(int themonth) {
		myMonth = themonth;
	}
	
	public void setYear(int theYear) {
		myYear = theYear;
	}
	
	 public String toString() {
         return String.format("%d/%02d/%02d", getDay(), getMonth() + 1, getYear());
     }

}
