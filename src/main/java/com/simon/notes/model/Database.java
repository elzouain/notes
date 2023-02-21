package com.simon.notes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Database {
	
	public static final Logger LOG = LogManager.getLogger(Database.class); 
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
            LOG.info("Connected successfully.");
        } catch (SQLException e) {
        	LOG.error("Error connecting to the database " + name, e);
        }
    }

    public void close(){
        try{
            connection.close();
            LOG.info("Disconnected successfully.");
        }catch(Exception e){
        	LOG.error("Error disconnecting to the database " + name, e);
        }
    }

}
