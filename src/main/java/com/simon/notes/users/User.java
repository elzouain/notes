package com.simon.notes.users;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    
    private long id;
    private String name;
    private String creationDate;
    private List<Note> noteKeeper;
    
    public User() {
    	noteKeeper = new ArrayList();
    }

    public User(String name){
    	this();
        id = Instant.now().toEpochMilli();
        this.name = name;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");    
        creationDate = dateTimeFormatter.format(Instant.ofEpochMilli(id).atZone(ZoneId.systemDefault()).toLocalDateTime()).toString();
    }
    
    public User(Map<Attributes, Object> params) {
    	this();
    	this.id = (Long) params.get(Attributes.ID);
    	this.name = (String) params.get(Attributes.NAME);
    	this.creationDate = (String) params.get(Attributes.CREATION_DATE);
    	this.noteKeeper = (List<Note>) params.get(Attributes.NOTE_KEEPER);
    }   

    public String getCreationDate() {
    	return creationDate;
    }

    public long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public List<Note> getNoteKeeper(){
    	return noteKeeper;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setNoteKeeper(List<Note> noteKeeper) {
    	this.noteKeeper = noteKeeper;
    }
    
    public void printNotes() {
    	System.out.println("Notes:");
    	if(noteKeeper.size() == 0) {
    			System.out.println("-- There are no notes --");
    	}else {
        	for(int i = 0; i < noteKeeper.size(); i++) {
        		System.out.printf("%d) %s\n", i, noteKeeper.get(i).getText());
        	}    		
    	}
    }
    
    public static enum Attributes{
        ID,
        NAME,
        CREATION_DATE,
        NOTE_KEEPER;
    }
}
