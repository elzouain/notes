package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.views.LogInMenuView;

public class LogInOption extends MenuOption implements StandardOption {

	@Override
	public void execute(Controller controller) {
		controller.setCurrentView(new LogInMenuView());
		ConsoleUtils.clear();
		controller.printCurrentUser();
		controller.displayOptions();
	}
}
