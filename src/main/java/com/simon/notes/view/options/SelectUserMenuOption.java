package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.simon.notes.controller.Controller;
import com.simon.notes.views.LogInMenuView;

public class SelectUserMenuOption extends MenuOption implements StandardOption  {
	
	public SelectUserMenuOption() {
		setTitle("Select User");
	}

	@Override
	public void execute(Controller controller) {
    	try {
    		if(controller.getUsersDatabase().countUsers() == 0) {
    			if(controller.getCurrentView().getClass().equals(LogInMenuView.class)) {   			
    				controller.getCurrentView().getMenuOptions().remove(0);
    				new LogInOption().execute(controller);		
    			}else {
    				new SwitchUserOption().execute(controller);
    			}
    		}else {
    			assignUser(controller);
    			new MainMenuOption().execute(controller);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}    	
	}	
	
	public void assignUser(Controller controller) {
	  	try {
    		List<String> availableUserNames = controller.getUsersDatabase().printAvailableUsers();
	    	System.out.print("Please select the user: ");
			int userIndex = Integer.parseInt(new Scanner(System.in).next().trim()) - 1;
			controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(availableUserNames.get(userIndex)));
			System.out.printf("Welcome back, %s\n", controller.getCurrentUser().getName());
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}    	
	}
}
