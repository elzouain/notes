package com.simon.notes.views;

import com.simon.notes.view.options.CreateNewUserMenuOption;
import com.simon.notes.view.options.ReturnToMainMenuOption;
import com.simon.notes.view.options.SelectUserMenuOption;

public class SwitchUserMenuView extends View implements StandardView {
	
    public SwitchUserMenuView(){
        setTitle("Switch User");
        addMenuOption(new SelectUserMenuOption());
        addMenuOption(new CreateNewUserMenuOption());
        addMenuOption(new ReturnToMainMenuOption());
    }
}
