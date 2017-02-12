package view;

import java.util.Stack;

import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.WelcomeMenu;

public final class TextUI {
	
	private static final Stack<Menu> MENUS;
	
	static {
		MENUS = new Stack<>();
	}
	
	private TextUI() {}
	
	public static void launch() {
		new LoginMenu().activate();
		MENUS.add(new WelcomeMenu());
		MENUS.peek().activate();
	}
	
	public static void register(final Menu menu) {
		MENUS.push(menu);
	}
	
	public static void back() {
		MENUS.pop();
		MENUS.peek().activate();
	}
	
}
