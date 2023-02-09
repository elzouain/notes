package com.simon.notes.users;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class User {
    
    private long id;
    private String name;
    private String creationDate;

    public User(String name){
        id = Instant.now().toEpochMilli();
        this.name = name;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");    
        creationDate = dateTimeFormatter.format(Instant.ofEpochMilli(id).atZone(ZoneId.systemDefault()).toLocalDateTime()).toString();
    }
    
    public User(Long id, String name, String creationDate) {
    	this.id = id;
    	this.name = name;
    	this.creationDate = creationDate;
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
    

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }


}
