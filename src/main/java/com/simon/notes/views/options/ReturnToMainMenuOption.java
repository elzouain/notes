package com.simon.notes.views.options;

import com.simon.notes.controller.Controller;

public class ReturnToMainMenuOption extends MenuOption{
	
    public ReturnToMainMenuOption(){
        setTitle("Return to Main Menu");
    }

	@Override
	public void execute(Controller controller) {
		new MainMenuOption().execute(controller);
	}

}
