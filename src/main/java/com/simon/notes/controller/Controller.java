package com.simon.notes.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.SizeLimitExceededException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.model.UsersDatabase;
import com.simon.notes.users.Note;
import com.simon.notes.users.User;
import com.simon.notes.utils.ConsoleUtils;
import com.simon.notes.utils.StringUtils;
import com.simon.notes.view.*;


public class Controller {
	
	public static final Logger LOG = LogManager.getLogger(Controller.class); 

	private User currentUser;
    private UsersDatabase usersDatabase;
    private View currentView;
    private AddNoteMenuView addNoteMenuView;
    private DeleteNotesMenuView deleteNoteMenuView;
    private DisplayNotesMenuView displayNotesMenuView;
    private EditNoteMenuView editNoteMenuView;
    private LogInMenuView logInMenuView;
    private MainMenuView mainMenuView;   
    private SwitchUserMenuView switchUserMenuView;    

    public void init(){       
        addNoteMenuView = new AddNoteMenuView();
        deleteNoteMenuView = new DeleteNotesMenuView();
        displayNotesMenuView = new DisplayNotesMenuView();
        editNoteMenuView = new EditNoteMenuView();
        logInMenuView = new LogInMenuView();
        mainMenuView = new MainMenuView();
        switchUserMenuView = new SwitchUserMenuView();
        usersDatabase = new UsersDatabase();
        usersDatabase.connect();
        showLogInMenuView();
    }

    public void exit() {
        System.out.print("Would you like to the quit program [Y/N]? ");
        if(new Scanner(System.in).next().equalsIgnoreCase("Y")) {
        	if(usersDatabase != null) {
        		usersDatabase.close();
        	}
        	System.exit(0);
        }
        showMainMenuView();            
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
    
    public void printCurrentUser() {
        if(currentUser != null)
        	System.out.println("Current user: " + currentUser.getName());    	
    }
    
    public void displayOptions() {
        StringUtils.printSeparatorLines();
        currentView.printMenuOptions();
        selectOption();
    }
    
    
    public void showAddNoteMenuView() {
    	currentView = addNoteMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	displayOptions();
    }
    
    public void showDeleteNotesMenuView() {
    	currentView = deleteNoteMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	currentUser.printNotes();
        StringUtils.printSeparatorLines();
        if(currentUser.getNoteKeeper().size() > 0) {        	
		    System.out.print("Please select which note to delete: ");
		    try {
		        int noteIndex = Integer.parseInt(new Scanner(System.in).next());
		        currentUser.getNoteKeeper().remove(noteIndex);
		    	usersDatabase.updateUserNotes(currentUser);
		    }catch(NumberFormatException e) {
		    	System.out.println("Invalid option...");
		    }catch(SQLException e) {
		    	LOG.error("NOTE DELETE ERROR", e);
		    }
        }
        showDisplayNotesMenuView();
    }
    
    public void showDisplayNotesMenuView() {
    	currentView = displayNotesMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	currentUser.printNotes();
        StringUtils.printSeparatorLines();
        currentView.printMenuOptions();
        selectOption();
    }
    
    public void showEditNoteMenuView() {
    	currentView = editNoteMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	currentUser.printNotes();
    	StringUtils.printSeparatorLines();
        if(currentUser.getNoteKeeper().size() > 0) {
		    List<Note> noteKeeperBefore = new ArrayList(currentUser.getNoteKeeper()); 
		    try {
		    	Scanner scanner = new Scanner(System.in);
		    	System.out.print("Please select note to edit: ");
		        Note targetNote = currentUser.getNoteKeeper().get(Integer.parseInt(scanner.nextLine()));
		        System.out.printf("Updating \"%s\"\n", targetNote.getText());
		        System.out.print("Please type the new content: ");    
		        targetNote.setText(scanner.nextLine());
		    	usersDatabase.updateUserNotes(currentUser);
		    }catch(SQLException e) {
		    	LOG.error("NOTE UPDATE ERROR", e);
		    	currentUser.setNoteKeeper(noteKeeperBefore);
		    }
        }
        showDisplayNotesMenuView();
    }
    
    public void showLogInMenuView() {
    	currentView = logInMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	displayOptions();
    }
        
    public void showMainMenuView(){
        currentView = mainMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	displayOptions();
    }
    
    public void showSwitchUserMenuView() {
    	currentView = switchUserMenuView;
    	ConsoleUtils.clear();
    	printCurrentUser();
    	displayOptions();
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
}
