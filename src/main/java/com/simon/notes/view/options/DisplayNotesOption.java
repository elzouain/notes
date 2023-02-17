package com.simon.notes.view.options;

import java.sql.SQLException;

import com.simon.notes.controller.Controller;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.views.DisplayNotesMenuView;

public class DisplayNotesOption extends MenuOption{
    
    public DisplayNotesOption(){
        setTitle("Display notes");
    }

    @Override
    public void execute(Controller controller) {
    	controller.setCurrentView(new DisplayNotesMenuView());
    	ConsoleUtils.clear();
    	controller.printCurrentUser();
    	controller.getCurrentUser().printNotes();
        StringUtils.printSeparatorLines();
        controller.getCurrentView().printMenuOptions();
        controller.selectOption();
    }
}
