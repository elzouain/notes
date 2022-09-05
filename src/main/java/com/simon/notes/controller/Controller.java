package com.simon.notes.controller;

import java.util.Scanner;

import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.view.MainMenuView;
import com.simon.notes.view.View;

public class Controller {

    private View currentView;
    private MainMenuView mainMenuView;
    private Scanner scanner;    

    public void init(){
        scanner = new Scanner(System.in);
        mainMenuView = new MainMenuView();
        showMainMenuView();
    }

    public View getCurrentView(){
        return currentView;
    }

    public Scanner getScanner(){
        return scanner;
    }
        
    public void showMainMenuView(){
        currentView = mainMenuView;
        ConsoleUtils.clear();
        mainMenuView.printMenuOptions();
        selectOption();
    }

    public void selectOption(){
        System.out.print("Select an option: ");
        int optionIndex = Integer.parseInt(scanner.next());
        currentView.getMenuOptions().get(optionIndex).execute(this);
    }

}
