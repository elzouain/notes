package com.simon.notes.view.options;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;
import com.simon.notes.users.User;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.views.DeleteNotesMenuView;

public class DeleteNoteOption extends MenuOption{
	
	public static final Logger LOG = LogManager.getLogger(EditNoteOption.class); 

    public DeleteNoteOption(){
        setTitle("Delete note");
    }

    @Override
    public void execute(Controller controller) {    	
    	controller.setCurrentView(new DeleteNotesMenuView());
    	ConsoleUtils.clear();
    	controller.printCurrentUser();
    	User currentUser = controller.getCurrentUser();
    	currentUser.printNotes();
        StringUtils.printSeparatorLines();
        if(currentUser.getNoteKeeper().size() > 0) {        	
		    System.out.print("Please select which note to delete: ");
		    try {
		        int noteIndex = Integer.parseInt(new Scanner(System.in).next());
		        currentUser.getNoteKeeper().remove(noteIndex);
		        controller.getUsersDatabase().updateUserNotes(currentUser);
		    }catch(NumberFormatException e) {
		    	System.out.println("Invalid option...");
		    }catch(SQLException e) {
		    	LOG.error("NOTE DELETE ERROR", e);
		    }
        }
        new DisplayNotesOption().execute(controller);
    }
}
