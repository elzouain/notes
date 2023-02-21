package com.simon.notes.views.options;

import com.simon.notes.controller.Controller;

public abstract class MenuOption{
    
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public abstract void execute(Controller controller);
}
