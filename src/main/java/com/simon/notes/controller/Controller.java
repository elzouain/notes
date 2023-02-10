package com.simon.notes.controller;

import java.util.Scanner;

import javax.naming.SizeLimitExceededException;

import com.simon.notes.controller.common.ConsoleUtils;
import com.simon.notes.controller.common.StringUtils;
import com.simon.notes.model.UsersDatabase;
import com.simon.notes.users.User;
import com.simon.notes.view.*;


public class Controller {

	private User currentUser;
    private UsersDatabase usersDatabase;
    private View currentView;
    private Scanner scanner;
    private AddNoteMenuView addNoteMenuView;
    private DisplayNotesMenuView displayNotesMenuView;
    private LogInMenuView logInMenuView;
    private MainMenuView mainMenuView;   
    private SwitchUserMenuView switchUserMenuView;    

    public void init(){       
        addNoteMenuView = new AddNoteMenuView();
        displayNotesMenuView = new DisplayNotesMenuView();
        logInMenuView = new LogInMenuView();
        mainMenuView = new MainMenuView();
        scanner = new Scanner(System.in);
        switchUserMenuView = new SwitchUserMenuView();
        usersDatabase = new UsersDatabase();
        usersDatabase.connect();
        showLogInMenuView();
    }

    public void exit() {
        System.out.print("Would you like to the quit program [Y/N]? ");
        if(scanner.next().equalsIgnoreCase("Y")) {
        	if(usersDatabase != null) {
        		usersDatabase.close();
        	}
        	System.exit(0);
        }
        showMainMenuView();            
    }
    
      
    public Scanner getScanner(){
        return scanner;
    }
    
    public UsersDatabase getUsersDatabase() {
    	return usersDatabase;
    }
    
    public User getCurrentUser() {
    	return currentUser;
    }
    
    public void setCurrentUser(User user) {
    	currentUser = user;
    }
    
    public View getCurrentView(){
        return currentView;
    }
    
    public void clearAndDisplayOptions() {
        ConsoleUtils.clear();
        if(currentUser != null)
        	System.out.println("Current user: " + currentUser.getName());
        StringUtils.printSeparatorLines();
        currentView.printMenuOptions();
        selectOption();
    }
    
    
    public void showAddNoteMenuView() {
    	currentView = addNoteMenuView;
    	clearAndDisplayOptions();
    }
    
    public void showDisplayNotesMenuView() {
    	currentView = displayNotesMenuView;
    	clearAndDisplayOptions();
    }
    
    public void showLogInMenuView() {
    	currentView = logInMenuView;
    	clearAndDisplayOptions();
    }
        
    public void showMainMenuView(){
        currentView = mainMenuView;
        clearAndDisplayOptions();
    }
    
    public void showSwitchUserMenuView() {
    	currentView = switchUserMenuView;
    	clearAndDisplayOptions();
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
        	e.printStackTrace();
            selectOption();
        }
    }
}
