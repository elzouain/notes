package com.simon.notes.view;

public class DisplayNotesMenuView  extends View implements StandardView{

    public DisplayNotesMenuView(){
        setTitle("Display Notes Menu");
    }

    @Override
    public void printMenuOptions() {
        for (int i = 0; i < getMenuOptions().size(); i++) {
            System.out.format("%d)  %s\n", i, getMenuOptions().get(i).getTitle());
        } 
    }
    
}
