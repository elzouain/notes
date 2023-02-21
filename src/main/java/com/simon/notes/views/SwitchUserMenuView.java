package com.simon.notes.views;

import com.simon.notes.views.options.CreateNewUserMenuOption;
import com.simon.notes.views.options.ReturnToMainMenuOption;
import com.simon.notes.views.options.SelectUserMenuOption;

public class SwitchUserMenuView extends View implements StandardView {
	
    public SwitchUserMenuView(){
        setTitle("Switch User");
        addMenuOption(new SelectUserMenuOption());
        addMenuOption(new CreateNewUserMenuOption());
        addMenuOption(new ReturnToMainMenuOption());
    }
}
