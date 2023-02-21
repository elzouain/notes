package com.simon.notes.views.options;

import com.simon.notes.controller.Controller;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.views.SwitchUserMenuView;

public class SwitchUserOption extends MenuOption{

    public SwitchUserOption(){
        setTitle("Switch User");
    }

    @Override
    public void execute(Controller controller) {
    	controller.setCurrentView(new SwitchUserMenuView());
    	ConsoleUtils.clear();
    	controller.printCurrentUser();
    	controller.displayOptions();
    }    
}
