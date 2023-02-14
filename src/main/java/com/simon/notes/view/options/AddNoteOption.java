package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.Scanner;

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
    	String text = new Scanner(System.in).nextLine();
    	try {
        	User currentUser = controller.getCurrentUser(); 
        	currentUser.getNoteKeeper().add(new Note(text));
        	controller.getUsersDatabase().updateUserNotes(currentUser);
        	controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(currentUser.getName()));
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	controller.showDisplayNotesMenuView();
    }
}
