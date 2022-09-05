package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class QuitOption extends MenuOption{

    public QuitOption(){
        setTitle("Quit");
    }

    @Override
    public void execute(Controller controller) {
        controller.exit();
    }}
