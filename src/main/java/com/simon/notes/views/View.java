package com.simon.notes.views;

import java.util.ArrayList;
import java.util.List;

import com.simon.notes.controller.Controller;
import com.simon.notes.views.options.MenuOption;

public class View{

    private String title;

    List<MenuOption> menuOptions;

    public View(){
        menuOptions = new ArrayList<>();
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void addMenuOption(MenuOption menuOption){
        menuOptions.add(menuOption);
    }

    public List<MenuOption> getMenuOptions(){
        return menuOptions;
    }

    public void printMenuOptions() {
        for (int i = 0; i < getMenuOptions().size(); i++) {
            System.out.format("%d)  %s\n", i, getMenuOptions().get(i).getTitle());
        }
    }
}
