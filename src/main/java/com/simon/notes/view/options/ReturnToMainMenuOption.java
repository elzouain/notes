package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class ReturnToMainMenuOption extends MenuOption{
	
    public ReturnToMainMenuOption(){
        setTitle("Return to Main Menu");
    }

	@Override
	public void execute(Controller controller) {
		// TODO Auto-generated method stub
		controller.showMainMenuView();		
	}

}
