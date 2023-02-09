package com.simon.notes.view.options;

import java.sql.SQLException;

import com.simon.notes.controller.Controller;
import com.simon.notes.controller.common.ConsoleUtils;

public class DisplayNotesOption extends MenuOption{
    
    public DisplayNotesOption(){
        setTitle("Display notes");
    }

    @Override
    public void execute(Controller controller) {
    	try {
    		controller.getUsersDatabase().printAvailableUsers();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        controller.showDisplayNotesMenuView();
    }
}
