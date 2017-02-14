package view;

import java.util.Stack;

import model.Job;
import model.Location;
import model.Park;
import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.WelcomeMenu;

public final class TextUI {
	
	private static final Stack<Menu> MENUS;
	private static Job SELECTED_JOB;
	private static Park SELECTED_PARK;
	
	static {
		MENUS = new Stack<>();
	}
	
	private TextUI() {}
	
	public static void launch() {
		new LoginMenu().activate();
		MENUS.add(new WelcomeMenu());
		MENUS.peek().activate();
	}
	
	public static void navigate(final Menu menu) {
		MENUS.add(menu);
		menu.activate();
	}
	
	public static void back() {
		MENUS.pop();
		MENUS.peek().activate();
	}
	
	public static void register(final Menu menu) {
		MENUS.push(menu);
	}
	
	public static void selectJob(final Job job) {
		SELECTED_JOB = job;
	}
	
	public static void selectPark(final Park park) {
		SELECTED_PARK = park;
	}
	
	public static Job getSelectedJob() {
		return SELECTED_JOB;
	}
	
	public static Park getSelectedPark() {
		return SELECTED_PARK;
	}
	
}
