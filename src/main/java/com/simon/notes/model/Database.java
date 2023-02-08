package com.simon.notes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String name;
    private String path;
    private String URL;
    private Connection connection;
    
    public Database(String name){
        this.name = name;
        path = System.getProperty("user.dir") + "/" + name;
        URL = "jdbc:sqlite:" + path;
        
    }

    public Connection getConnection(){
        return connection;
    }

    public String getName(){
        return name;
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database " + name, e);
        }
    }

    public void close(){
        try{
            connection.close();
            System.out.println("Disconnected successfully.");
        }catch(Exception e){
            throw new RuntimeException("Error disconnecting from the database " + name, e);
        }
    }

}
