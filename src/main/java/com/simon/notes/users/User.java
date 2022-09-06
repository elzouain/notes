package com.simon.notes.users;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class User {
    
    private long id;
    private String name;

    public User(String name){
        id = Instant.now().toEpochMilli();
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCreationDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");    
        return dateTimeFormatter.format(Instant.ofEpochMilli(id).atZone(ZoneId.systemDefault()).toLocalDateTime()).toString();
    }
}
