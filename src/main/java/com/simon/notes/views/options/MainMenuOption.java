package com.simon.notes.views.options;

import com.simon.notes.controller.Controller;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.views.MainMenuView;

public class MainMenuOption extends MenuOption {
	
	public MainMenuOption() {
		setTitle("Main Menu");
	}

	@Override
	public void execute(Controller controller) {		
		controller.setCurrentView(new MainMenuView());
		ConsoleUtils.clear();
		controller.printCurrentUser();
		controller.displayOptions();
	}
}
