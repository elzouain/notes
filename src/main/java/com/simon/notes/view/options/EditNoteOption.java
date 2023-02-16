package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class EditNoteOption extends MenuOption{

    public EditNoteOption(){
        setTitle("Edit note");
    }

    @Override
    public void execute(Controller controller) {
    	controller.showEditNoteMenuView();        
    }    
}
