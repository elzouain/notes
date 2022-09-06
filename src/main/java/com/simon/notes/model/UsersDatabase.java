package com.simon.notes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersDatabase extends Database{

    public UsersDatabase(){
        super("users.db");
    }

    public void createUsersTable(){
        if(!doesUsersTableExists()){
            try{
                Statement statement = getConnection().createStatement();
                statement.execute(UsersQuery.CREATE_TABLE_QUERY);
            }catch(Exception e){
                if(e.getMessage().contains("already exists")){
                    return;
                }else{
                    throw new RuntimeException("Error creating table.", e);
                }
            }
        }
    }

    public boolean doesUsersTableExists(){
        try{
            Statement statement = getConnection().createStatement();
            statement.executeQuery(UsersQuery.SELECT_ALL_USERS_QUERY);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void insertIntoUsersTable(int id, String username, String creationDate){
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(UsersQuery.INSERT_INTO_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, creationDate);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException("Error inserting user into users table.", e);
        }
    }

    public int countUsers(){
        if(!doesUsersTableExists())
            return 0;
            
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(UsersQuery.COUNT_USERS_QUERY);
            return resultSet.getInt(1);
        }catch(Exception e){
            throw new RuntimeException("Error counting users from " + getName(), e);
        }
    }

    public static class UsersQuery{
        public static final String TABLE_NAME = "users";
        public static final String ID_COL = "_id";
        public static final String USERNAME_COL = "username";
        public static final String CREATION_DATE_COL = "creation_date";
        public static final String CREATE_TABLE_QUERY = String.format("CREATE TABLE %s (%s INTEGER, %s VARCHAR, %s VARCHAR);", TABLE_NAME, ID_COL, USERNAME_COL, CREATION_DATE_COL);
        public static final String COUNT_USERS_QUERY = String.format("SELECT COUNT(*) FROM %s;", TABLE_NAME);
        public static final String INSERT_INTO_QUERY = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?);", TABLE_NAME, ID_COL, USERNAME_COL, CREATION_DATE_COL);
        public static final String SELECT_ALL_USERS_QUERY = String.format("SELECT * FROM %s;", TABLE_NAME);
    }
    
}
