package com.simon.notes.views.options;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.views.LogInMenuView;

public class SelectUserMenuOption extends MenuOption implements StandardOption  {
	
	public static final Logger LOG = LogManager.getLogger(SelectUserMenuOption.class); 
	
	public SelectUserMenuOption() {
		setTitle("Select User");
	}

	@Override
	public void execute(Controller controller) {
    	ConsoleUtils.clear();
    	if(controller.getCurrentUser() != null)
        	controller.printCurrentUser();
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
    		LOG.error("Error found selecting user.", e);
    		e.printStackTrace();
    	}    	
	}	
	
	public void assignUser(Controller controller) throws SQLException {
		List<String> availableUserNames = controller.getUsersDatabase().printAvailableUsers();
    	System.out.print("Please select the user: ");
    	int userIndex = Integer.parseInt(new Scanner(System.in).next().trim());
		if(userIndex == -1)
			userIndex = 0;
		controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(availableUserNames.get(userIndex)));
	}
}
