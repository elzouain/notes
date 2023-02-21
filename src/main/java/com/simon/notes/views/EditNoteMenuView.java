package com.simon.notes.views;

import com.simon.notes.views.options.ReturnToMainMenuOption;

public class EditNoteMenuView extends View implements StandardView {
    
    public EditNoteMenuView(){
        this.setTitle("Edit Notes");
        this.addMenuOption(new ReturnToMainMenuOption());
    }
}
