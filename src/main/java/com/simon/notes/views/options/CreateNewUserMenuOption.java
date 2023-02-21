package com.simon.notes.views.options;

import java.sql.SQLException;
import java.util.Scanner;

import com.simon.notes.controller.Controller;
import com.simon.notes.users.User;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.views.LogInMenuView;

public class CreateNewUserMenuOption extends MenuOption implements StandardOption {
	
	public CreateNewUserMenuOption() {
		setTitle("Create New User");
	}

	@Override
	public void execute(Controller controller) {
    	ConsoleUtils.clear();
    	if(controller.getCurrentUser() != null)
        	controller.printCurrentUser();
    	System.out.print("Please enter a name: ");
    	String name = new Scanner(System.in).nextLine().trim();
    	User user = null;
    	try {
    		user = controller.getUsersDatabase().selectUserByUsername(name);
        	if(user == null) {
        		controller.getUsersDatabase().insertUser(new User(name));
        	}else {
        		System.out.println("User already exists: " + name);
        	}
        	ConsoleUtils.clear();
        	if(controller.getCurrentView().getClass().equals(LogInMenuView.class) && controller.getCurrentUser() != null) {
        		controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(name));
        		new MainMenuOption().execute(controller);
        	}else {
        		new SwitchUserOption().execute(controller);
        	}
    	}catch(SQLException e) {
    		System.out.printf("Unable to create user with name '%s'.\n", name);
    	}
	}
}
