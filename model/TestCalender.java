/**
 * 
 */
package model;


/**
 * @author John Bako
 *
 */
public class TestCalender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int day = 3, month = 2, year = 2017;
		int spaces = 1;
		
		Calender myCalender = new Calender();
		
		myCalender.setDay(day);
		myCalender.setMonth(month);
		myCalender.setYear(year);
		
		String[] myMonths = {
				"",
				"January", "February", "March",
				"April", "May", "June",  
				"July", "August", "September", 
				"October", "November", "December"
		};
		
		int[] myDays = {
				0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
		};
		
		for (int i = month; i <= 12; i++) {
			// check for leap year
			if ((year % 4 == 0 && year % 100 != 0) ||
					((year % 400 == 0)) && i == 2) {
				myDays[i] = 29;
			}
			
			// Display the month and year
	        System.out.println("          "+ myMonths[i] + " " + year);

	        // Display the lines
	        System.out.println("_____________________________________");
	        System.out.println("   Sun  Mon Tue   Wed Thu   Fri  Sat");
	        
	        // spaces required
            spaces = (myDays[i - 1] + spaces) % 7;
            
            // print the calendar
            for (int j = 0; j < spaces; j++)
                System.out.print("     ");
            for (int j = 1; j <= myDays[i]; j++) {
                System.out.printf(" %3d ", j);
                if (((j + spaces) % 7 == 0) || (j == myDays[i])) System.out.println();
//                if (j == day) {
//                	System.out.println(j);
//                }
            }

            System.out.println();
		}

	}
}
