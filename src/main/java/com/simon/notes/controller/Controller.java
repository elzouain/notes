package com.simon.notes.controller;

import java.util.Scanner;

import javax.naming.SizeLimitExceededException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.model.UsersDatabase;
import com.simon.notes.users.User;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.view.options.LogInOption;
import com.simon.notes.view.options.MainMenuOption;
import com.simon.notes.view.options.MenuOption;
import com.simon.notes.views.*;


public class Controller {
	
	public static final Logger LOG = LogManager.getLogger(Controller.class); 

	private User currentUser;
    private UsersDatabase usersDatabase;
    private View currentView;
    
    public User getCurrentUser() {
    	return currentUser;
    }
    
    public View getCurrentView(){
        return currentView;
    }
    
    public UsersDatabase getUsersDatabase() {
    	return usersDatabase;
    }
    
    public void setCurrentUser(User user) {
    	currentUser = user;
    }   
    
    public void setCurrentView(View view) {
    	this.currentView = view;
    }
    
    public void init(){
        usersDatabase = new UsersDatabase();
        usersDatabase.connect();
        new LogInOption().execute(this);
    }

    public void exit() {
        System.out.print("Would you like to the quit program [Y/N]? ");
        if(new Scanner(System.in).next().equalsIgnoreCase("Y")) {
        	if(usersDatabase != null) {
        		usersDatabase.close();
        	}
        	System.exit(0);
        }
        new MainMenuOption().execute(this);
    }
    
    public void printCurrentUser() {
        if(currentUser != null)
        	System.out.println("Current user: " + currentUser.getName());    	
    }
    
    public void displayOptions() {
        StringUtils.printSeparatorLines();
        currentView.printMenuOptions();
        selectOption();
    }

    public void selectOption(){
        System.out.print("Select an option: ");
        int optionIndex;
        try{
            optionIndex = new Scanner(System.in).nextInt();
            if(optionIndex < 0 &&  optionIndex >= currentView.getMenuOptions().size()){
                throw new SizeLimitExceededException();
            }else{
                currentView.getMenuOptions().get(optionIndex).execute(this);
            }
        }catch(SizeLimitExceededException e){
            System.out.println("Invalid option entered. Select a number within range.");
            selectOption();
        }catch(RuntimeException e){
        	LOG.error("Selection error found.", e);
            selectOption();
        }
    }
    
    public void selectOption(MenuOption option) {
    	for(MenuOption o:currentView.getMenuOptions()) {
    		if(o.equals(option))
    			o.execute(this);
    	}
    }
}
