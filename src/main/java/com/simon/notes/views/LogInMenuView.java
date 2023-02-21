package com.simon.notes.views;

import com.simon.notes.views.options.CreateNewUserMenuOption;
import com.simon.notes.views.options.QuitMenuOption;
import com.simon.notes.views.options.SelectUserMenuOption;

public class LogInMenuView extends View implements StandardView {
	
    public LogInMenuView(){
        setTitle("Log In");
        addMenuOption(new SelectUserMenuOption());
        addMenuOption(new CreateNewUserMenuOption());
        addMenuOption(new QuitMenuOption());
    }
}
