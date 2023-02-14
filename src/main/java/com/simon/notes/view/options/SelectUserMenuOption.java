package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.simon.notes.controller.Controller;
import com.simon.notes.view.LogInMenuView;
import com.simon.notes.view.options.StandardOption;

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
    				controller.showLogInMenuView();    				
    			}else {
    				controller.showSwitchUserMenuView();	
    			}
    		}else {
    			assignUser(controller);
    			controller.showMainMenuView();
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
