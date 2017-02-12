package view;

import java.util.Stack;

import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.WelcomeMenu;

public class TextUI {
	
	private final Stack<Menu> menus;
	
	public TextUI() {
		menus = new Stack<>();
	}
	
	public void launch() {
		new LoginMenu().activate();
		menus.add(new WelcomeMenu());
		menus.peek().activate();
	}
	
}
