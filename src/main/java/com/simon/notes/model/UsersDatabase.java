package com.simon.notes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.simon.notes.controller.common.StringUtils;
import com.simon.notes.users.User;

public class UsersDatabase extends Database{

    public UsersDatabase(){
        super("users.db");
    }

    public void createUsersTable() throws SQLException {
    	Statement statement = null;
        if(!doesUsersTableExists()){
            try{
                statement = getConnection().createStatement();
                statement.execute(UsersQuery.CREATE_TABLE_QUERY);
            }catch(Exception e){
                if(e.getMessage().contains("already exists")){
                    return;
                }else{
                    throw new RuntimeException("Error creating table.", e);
                }
            }finally {
            	if(statement != null)
            		statement.close();
            }
        }
    }

    public boolean doesUsersTableExists() throws SQLException {
    	Statement statement = null;
        try{
            statement = getConnection().createStatement();
            statement.executeQuery(UsersQuery.SELECT_ALL_USERS_QUERY);
            return true;
        }catch(Exception e){
            return false;
        }finally {
        	if(statement != null)
        		statement.close();
        }
    }

    public void insertIntoUsersTable(long id, String username, String creationDate) throws SQLException {
    	PreparedStatement preparedStatement = null;
        try{
        	createUsersTable();
        	preparedStatement = getConnection().prepareStatement(UsersQuery.INSERT_INTO_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, creationDate);
            preparedStatement.executeUpdate();
            System.out.printf("User ['%s'] created successfully.\n", username);
        }catch(Exception e){
            throw new RuntimeException("Error inserting user into users table.", e);
        }finally {
        	if(preparedStatement != null)
        		preparedStatement.close();
        }
    }

    public void insertIntoUsersTable(User user) throws SQLException {
        insertIntoUsersTable(user.getId(), user.getName(), user.getCreationDate());
    }

    public int countUsers() throws SQLException {
        if(!doesUsersTableExists())
            return 0;
            
    	Statement statement = null;
    	ResultSet resultSet = null;
        try{
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(UsersQuery.COUNT_USERS_QUERY);
            return resultSet.getInt(1);
        }catch(Exception e){
            throw new RuntimeException("Error counting users from " + getName(), e);
        }finally {
        	if(statement != null)
        		statement.close();
        	if(resultSet != null)
        		resultSet.close();
        }
    }
    
    public List<String> printAvailableUsers() throws SQLException {
    	List<String> userNames = new ArrayList();
    	Statement statement = null;
    	ResultSet resultSet = null;
        try{
        	createUsersTable();
        	statement = getConnection().createStatement();
            resultSet = statement.executeQuery(UsersQuery.SELECT_ALL_USERS_QUERY);
            int userCount = 1;
            if(countUsers() != 0) {
	            while(resultSet.next()) {
	            	System.out.printf("%d) %s\n", userCount, resultSet.getString(2));
	            	++userCount;
	            	userNames.add(resultSet.getString(2));
	            }
            }else {
            	System.out.println("No users found.");
            }
            StringUtils.printSeparatorLines();
            return userNames;
        }catch(Exception e){
            throw new RuntimeException("Error retrieving usernames from DB:", e);
        }finally {
        	if(statement != null)
        		statement.close();
        	if(resultSet != null)
        		resultSet.close();
        }
    }
    
    public User selectUserByUsername(String username) throws SQLException {
    	Statement statement = null;
    	ResultSet resultSet = null;
        try{
        	createUsersTable();
        	statement = getConnection().createStatement();
            resultSet = statement.executeQuery(UsersQuery.SELECT_USER_BY_USERNAME_QUERY + "='" + username + "'");
            while(resultSet.next()) {
            	return new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3)); 
            }
        }catch(Exception e){
            throw new RuntimeException("Error retrieving usernames from DB:", e);
        }finally {
        	if(statement != null)
        		statement.close();
        	if(resultSet != null)
        		resultSet.close();
        }
		return null;
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
        public static final String SELECT_USER_BY_USERNAME_QUERY = String.format("SELECT * FROM %s WHERE %s=", TABLE_NAME, USERNAME_COL);
    }
}
