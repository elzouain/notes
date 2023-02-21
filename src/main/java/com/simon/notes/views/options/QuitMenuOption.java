package com.simon.notes.views.options;

import com.simon.notes.controller.Controller;

public class QuitMenuOption extends MenuOption{

    public QuitMenuOption(){
        setTitle("Quit");
    }

    @Override
    public void execute(Controller controller) {
        controller.exit();
    }
}
