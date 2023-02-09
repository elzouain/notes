package com.simon.notes.view.options;

import java.sql.SQLException;

import com.simon.notes.controller.Controller;
import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.controller.common.StringUtils;
import com.simon.notes.view.LogInMenuView;
import com.simon.notes.view.options.StandardOption;

public class SelectUserMenuOption extends MenuOption implements StandardOption  {
	
	public SelectUserMenuOption() {
		setTitle("Select User");
	}

	@Override
	public void execute(Controller controller) {
    	try {
    		controller.getUsersDatabase().printAvailableUsers();
    		if(controller.getUsersDatabase().countUsers() == 0) {
    			if(controller.getCurrentView().getClass().equals(LogInMenuView.class)) {   			
    				controller.getCurrentView().getMenuOptions().remove(0);
    				ConsoleUtils.clear();
    				System.out.println("Please create a new user.");
    				StringUtils.printSeparatorLines();
    				controller.showLogInMenuView();    				
    			}else {
    				controller.showSwitchUserMenuView();	
    			} 		
    			
    		}else {
    			controller.showMainMenuView();
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
	}

}
