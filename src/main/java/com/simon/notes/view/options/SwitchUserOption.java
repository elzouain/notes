package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class SwitchUserOption extends MenuOption{

    public SwitchUserOption(){
        setTitle("Switch User");
    }

    @Override
    public void execute(Controller controller) {
    	controller.showSwitchUserMenuView();        
    }    
}
