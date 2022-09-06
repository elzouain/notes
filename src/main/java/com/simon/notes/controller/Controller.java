package com.simon.notes.controller;

import java.util.Scanner;

import javax.naming.SizeLimitExceededException;

import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.model.UsersDatabase;
import com.simon.notes.view.MainMenuView;
import com.simon.notes.view.View;

public class Controller {

    private UsersDatabase usersDatabase;
    private View currentView;
    private MainMenuView mainMenuView;
    private Scanner scanner;

    public void init(){
        UsersDatabase usersDatabase = new UsersDatabase();
        usersDatabase.connect();
        scanner = new Scanner(System.in);
        mainMenuView = new MainMenuView();
        showMainMenuView();
    }

    public void exit(){
        System.out.print("Would you like to the quit program [Y/N]? ");
        if(scanner.next().equalsIgnoreCase("Y"))
            System.exit(0);
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
        int optionIndex;
        try{
            optionIndex = Integer.parseInt(scanner.next());
            if(optionIndex < 0 &&  optionIndex >= currentView.getMenuOptions().size()){
                throw new SizeLimitExceededException();
            }else{
                currentView.getMenuOptions().get(optionIndex).execute(this);
            }
        }catch(SizeLimitExceededException e){
            System.out.println("Invalid option entered. Select a number within range.");
            selectOption();
        }catch(RuntimeException e){
            System.out.println("Error found:" + e.getMessage());
            selectOption();
        }
    }
}
