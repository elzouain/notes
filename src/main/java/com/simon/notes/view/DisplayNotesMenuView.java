package com.simon.notes.view;

import com.simon.notes.view.options.ReturnToMainMenuOption;

public class DisplayNotesMenuView  extends View implements StandardView{

    public DisplayNotesMenuView(){
        this.setTitle("Display Notes Menu");
        this.addMenuOption(new ReturnToMainMenuOption());
    }
}
