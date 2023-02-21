package com.simon.notes.views.options;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;
import com.simon.notes.users.Note;
import com.simon.notes.users.User;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.views.AddNoteMenuView;

public class AddNoteOption extends MenuOption{
	
	public static final Logger LOG = LogManager.getLogger(AddNoteOption.class); 
    
    public AddNoteOption(){
        setTitle("Add Note");
    }

    @Override
    public void execute(Controller controller) {
    	controller.setCurrentView(new AddNoteMenuView());
    	ConsoleUtils.clear();
    	controller.printCurrentUser();
    	StringUtils.printSeparatorLines();
    	controller.getCurrentUser().printNotes();
    	StringUtils.printSeparatorLines();
    	try {
    		System.out.print("Type note: ");
    		String text = new Scanner(System.in).nextLine();
    		this.addNote(controller, text);
    	}catch(SQLException e) {
    		LOG.error("Error found adding note.", e);
    	}finally {
    		new DisplayNotesOption().execute(controller);
    	}
    }
    
    public void addNote(Controller controller, String text) throws SQLException {
    	if(text == null || text.equals(""))
    		return;
    	User currentUser = controller.getCurrentUser(); 
    	currentUser.getNoteKeeper().add(new Note(text));
    	controller.getUsersDatabase().updateUserNotes(currentUser);
    	controller.setCurrentUser(controller.getUsersDatabase().selectUserByUsername(currentUser.getName()));
    }
}
