package com.simon.notes.view;

import com.simon.notes.view.options.ReturnToMainMenuOption;

public class DisplayNotesMenuView  extends View implements StandardView{

    public DisplayNotesMenuView(){
        setTitle("Display Notes Menu");
        addMenuOption(new ReturnToMainMenuOption());
    }
}
