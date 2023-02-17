package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;
import com.simon.notes.users.Note;
import com.simon.notes.users.User;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.views.EditNoteMenuView;

public class EditNoteOption extends MenuOption{
	
	public static final Logger LOG = LogManager.getLogger(EditNoteOption.class); 

    public EditNoteOption(){
        setTitle("Edit note");
    }

    @Override
    public void execute(Controller controller) {
    	controller.setCurrentView(new EditNoteMenuView());    	
    	ConsoleUtils.clear();
    	controller.printCurrentUser();
    	User currentUser = controller.getCurrentUser();
    	currentUser.printNotes();
    	StringUtils.printSeparatorLines();
        if(currentUser.getNoteKeeper().size() > 0) {
		    List<Note> noteKeeperBefore = new ArrayList(currentUser.getNoteKeeper()); 
		    try {
		    	Scanner scanner = new Scanner(System.in);
		    	System.out.print("Please select note to edit: ");
		        Note targetNote = currentUser.getNoteKeeper().get(Integer.parseInt(scanner.nextLine()));
		        System.out.printf("Updating \"%s\"\n", targetNote.getText());
		        System.out.print("Please type the new content: ");    
		        targetNote.setText(scanner.nextLine());
		        controller.getUsersDatabase().updateUserNotes(currentUser);
		    }catch(SQLException e) {
		    	LOG.error("NOTE UPDATE ERROR", e);
		    	currentUser.setNoteKeeper(noteKeeperBefore);
		    }
        }
        
        new DisplayNotesOption().execute(controller);
    }    
}
