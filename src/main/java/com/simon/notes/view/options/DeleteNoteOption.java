package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class DeleteNoteOption extends MenuOption{

    public DeleteNoteOption(){
        setTitle("Delete note");
    }

    @Override
    public void execute(Controller controller) {
    	controller.showDeleteNotesMenuView();
    }
}
