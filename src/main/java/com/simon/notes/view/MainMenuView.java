package com.simon.notes.view;

import com.simon.notes.view.options.AddNoteOption;
import com.simon.notes.view.options.DeleteNoteOption;
import com.simon.notes.view.options.DisplayNotesOption;
import com.simon.notes.view.options.EditNoteOption;
import com.simon.notes.view.options.QuitMenuOption;
import com.simon.notes.view.options.SwitchUserOption;

public class MainMenuView extends View{

    public MainMenuView(){
        setTitle("Main Menu");
        addMenuOption(new DisplayNotesOption());
        addMenuOption(new AddNoteOption());
        addMenuOption(new EditNoteOption());
        addMenuOption(new DeleteNoteOption());
        addMenuOption(new SwitchUserOption());
        addMenuOption(new QuitMenuOption());
    }

}
