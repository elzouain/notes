package com.simon.notes.view.options;

import java.sql.SQLException;

import com.simon.notes.controller.Controller;
import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.users.User;
import com.simon.notes.view.LogInMenuView;

public class CreateNewUserMenuOption extends MenuOption implements StandardOption {
	
	public CreateNewUserMenuOption() {
		setTitle("Create New User");
	}

	@Override
	public void execute(Controller controller) {
    	ConsoleUtils.clear();
    	System.out.print("Please enter a name: ");
    	String name = controller.getScanner().next();
    	try {
    		controller.getUsersDatabase().insertIntoUsersTable(new User(name));
    	}catch(SQLException e) {
    		System.out.printf("Unable to create user with name '%s'.\n", name);
    	}
    	ConsoleUtils.clear();
    	if(controller.getCurrentView().getClass().equals(LogInMenuView.class)) {
    		controller.showLogInMenuView();
    	}else {
    		controller.showSwitchUserMenuView();
    	}
	}
}
