package com.simon.notes.view;

import com.simon.notes.view.options.AddNoteOption;
import com.simon.notes.view.options.DeleteNoteOption;
import com.simon.notes.view.options.DisplayNotesOption;
import com.simon.notes.view.options.EditNoteOption;
import com.simon.notes.view.options.QuitOption;
import com.simon.notes.view.options.SwitchUserOption;

public class MainMenuView extends View implements StandardView{

    public MainMenuView(){
        setTitle("Main Menu");
        addMenuOption(new DisplayNotesOption());
        addMenuOption(new AddNoteOption());
        addMenuOption(new EditNoteOption());
        addMenuOption(new DeleteNoteOption());
        addMenuOption(new SwitchUserOption());
        addMenuOption(new QuitOption());
    }

    @Override
    public void printMenuOptions() {
        for (int i = 0; i < getMenuOptions().size(); i++) {
            System.out.format("%d)  %s\n", i, getMenuOptions().get(i).getTitle());
        }
    }
    
}
