package com.simon.notes.views;

import com.simon.notes.views.options.AddNoteOption;
import com.simon.notes.views.options.DeleteNoteOption;
import com.simon.notes.views.options.DeleteUserOption;
import com.simon.notes.views.options.DisplayNotesOption;
import com.simon.notes.views.options.EditNoteOption;
import com.simon.notes.views.options.QuitMenuOption;
import com.simon.notes.views.options.SwitchUserOption;

public class MainMenuView extends View{

    public MainMenuView(){
        setTitle("Main Menu");
        addMenuOption(new DisplayNotesOption());
        addMenuOption(new AddNoteOption());
        addMenuOption(new EditNoteOption());
        addMenuOption(new DeleteNoteOption());
        addMenuOption(new SwitchUserOption());
        addMenuOption(new DeleteUserOption());
        addMenuOption(new QuitMenuOption());
    }

}
