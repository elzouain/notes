package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;
import com.simon.notes.users.User;

public class DeleteUserOption extends MenuOption implements StandardOption {
	
	public static final Logger LOG = LogManager.getLogger(DeleteUserOption.class); 
	
	public DeleteUserOption() {
		setTitle("Delete User");
	}

	@Override
	public void execute(Controller controller) {
    	try {
    		if(controller.getUsersDatabase().countUsers() == 0)
    			System.out.println(" -- There are no users --");
    		else 
    			deleteUser(controller);
    		new LogInOption().execute(controller); 
    	}catch(SQLException e) {
        	LOG.error("User removal error found.", e);
    	} 
	}
	
	public Boolean deleteUser(Controller controller) throws SQLException {
		List<String> availableUserNames = controller.getUsersDatabase().printAvailableUsers();
    	System.out.print("Please select the user: ");
		int userIndex = Integer.parseInt(new Scanner(System.in).next().trim()) - 1;
		User user = controller.getUsersDatabase().selectUserByUsername(availableUserNames.get(userIndex));
		try {
			if(user.getId() == controller.getCurrentUser().getId())
				controller.setCurrentUser(null);
			controller.getUsersDatabase().deleteUser(user.getName());
			return true;
		}catch(Exception e) {
			LOG.error("Error deleting user " + user.getName(), e);
			e.printStackTrace();
			controller.getUsersDatabase().insertUser(user);
			return false;
		} 	
	}
}
