package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class MainMenuOption extends MenuOption {
	
	public MainMenuOption() {
		setTitle("Main Menu");
	}

	@Override
	public void execute(Controller controller) {
		controller.showMainMenuView();		
	}

}
