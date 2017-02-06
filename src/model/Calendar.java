/**
 * 
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author John Bako
 *
 */
public class Calendar {
	
	private static final List<DayOfWeek> days = Arrays.asList(
			DayOfWeek.SUNDAY,
			DayOfWeek.MONDAY,
			DayOfWeek.TUESDAY,
			DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY,
			DayOfWeek.SATURDAY
	);

	private final List<Job> jobs;
	
	public Calendar(final List<Job> jobs) {
		this.jobs = jobs;
	}
	
	@Override
	public final String toString() {
		final LocalDate today = LocalDate.now();
		final StringBuilder sb = new StringBuilder();
		sb.append("\t").append(today.getMonth().toString()).append(today.getYear()).append('\n');
		sb.append("-----------------------------------------\n");
		sb.append(" Sat | Mon | Tue | Wed | Thu | Fri | Sun \n");
		sb.append("-----------------------------------------\n");
		for (int i = 0; i < days.indexOf(today.getDayOfWeek()) - 1; i++) sb.append("     |");
		for (int day = today.getDayOfMonth(); day < today.lengthOfMonth(); day++) {
			// TODO: implement if Saturday, append newline char
			sb.append("  ");
			if (Math.log10(day) < 2) sb.append(' ');
			sb.append(day).append(" |");
		}
		sb.append("\n");
		return sb.toString();
	}

}
