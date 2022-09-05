package com.simon.notes.view.options;

import com.simon.notes.controller.Controller;

public class MenuOption implements StandardOption{
    
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public void execute(Controller controller) {
        // TODO Auto-generated method stub
        
    }

}
