package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class DisplayNotesOption extends MenuOption{
    
    public DisplayNotesOption(){
        setTitle("Display notes");
    }

    @Override
    public void execute(Controller controller) {
        throw new RuntimeException("Method not implemented.");
    }

}
