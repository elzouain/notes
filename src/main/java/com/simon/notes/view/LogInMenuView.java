package com.simon.notes.view;

import com.simon.notes.view.options.CreateNewUserMenuOption;
import com.simon.notes.view.options.QuitMenuOption;
import com.simon.notes.view.options.SelectUserMenuOption;

public class LogInMenuView extends View implements StandardView {
	
    public LogInMenuView(){
        setTitle("Log In");
        addMenuOption(new SelectUserMenuOption());
        addMenuOption(new CreateNewUserMenuOption());
        addMenuOption(new QuitMenuOption());
    }
}
