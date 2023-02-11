package com.simon.notes.view.options;

import java.sql.SQLException;

import com.simon.notes.controller.Controller;
import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.users.Note;
import com.simon.notes.users.User;

public class AddNoteOption extends MenuOption{
    
    public AddNoteOption(){
        setTitle("Add Note");
    }

    @Override
    public void execute(Controller controller) {
    	ConsoleUtils.clear();
    	System.out.print("Type note: ");
    	String text = controller.getScanner().next();
    	try {
        	User currentUser = controller.getCurrentUser(); 
        	currentUser.getNoteKeeper().add(new Note(text));
        	controller.getUsersDatabase().updateUserNotes(currentUser);
        	controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(currentUser.getName()));
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}   	    	
    	// TODO: Add notes to the DB
    	controller.showDisplayNotesMenuView();
    }
}
