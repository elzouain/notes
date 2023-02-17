package com.simon.notes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simon.notes.users.Note;
import com.simon.notes.users.User;
import com.simon.notes.utils.StringUtils;

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
    
    public String formatNotes(User user) {
    	StringBuilder builder = new StringBuilder();
    	if(user.getNoteKeeper().size() > 0) {
        	for(Note note:user.getNoteKeeper()) {
        		builder.append(note.getText()).append(StringUtils.NOTE_SEPARATOR);
        	}
        	return builder.toString();    		
    	}else {
    		return "";
    	}
    }
    
    public List<Note> parseNotes(String queriedNotes){
    	String[] notesText = queriedNotes.split(StringUtils.NOTE_SEPARATOR);
    	List<Note> noteKeeper = new ArrayList();
    	if(!notesText[0].equals("")) {
        	for(String text:notesText)
        		noteKeeper.add(new Note(text));
    	}
    	return noteKeeper;
    }

    public void insertUser(User user) throws SQLException {        
    	PreparedStatement preparedStatement = null;
        try{
        	createUsersTable();
        	preparedStatement = getConnection().prepareStatement(UsersQuery.INSERT_INTO_QUERY);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getCreationDate());
            preparedStatement.setString(4, formatNotes(user));
            preparedStatement.executeUpdate();
            System.out.printf("User ['%s'] created successfully.\n", user.getName());
        }catch(Exception e){
            throw new RuntimeException("Error inserting user into users table.", e);
        }finally {
        	if(preparedStatement != null)
        		preparedStatement.close();
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
            	Map<User.Attributes, Object> params = new HashMap();
            	params.put(User.Attributes.ID, resultSet.getLong(1));
            	params.put(User.Attributes.NAME, resultSet.getString(2));
            	params.put(User.Attributes.CREATION_DATE, resultSet.getString(3));
            	params.put(User.Attributes.NOTE_KEEPER, parseNotes(resultSet.getString(4)));
            	return new User(params); 
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
    
    public void updateUserNotes(User user) throws SQLException {
    	PreparedStatement preparedStatement = null;
        try{
        	preparedStatement = getConnection().prepareStatement(UsersQuery.UPDATE_USER_NOTES_QUERY);
            preparedStatement.setString(1, formatNotes(user));
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
            System.out.printf("User ['%s'] updated successfully.\n", user.getName());
        }catch(Exception e){
            throw new RuntimeException("Error inserting user into users table.", e);
        }finally {
        	if(preparedStatement != null)
        		preparedStatement.close();
        }
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
        	if(statement != null) statement.close();
        	if(resultSet != null) resultSet.close();
        }
    }   


    public static class UsersQuery{
        public static final String TABLE_NAME = "users";
        public static final String ID_COL = "_id";
        public static final String USERNAME_COL = "username";
        public static final String CREATION_DATE_COL = "creation_date";
        public static final String NOTES_COL = "notes";
        public static final String CREATE_TABLE_QUERY = String.format("CREATE TABLE %s (%s INTEGER, %s VARCHAR, %s VARCHAR, %s VARCHAR);", TABLE_NAME, ID_COL, USERNAME_COL, CREATION_DATE_COL, NOTES_COL);
        public static final String COUNT_USERS_QUERY = String.format("SELECT COUNT(*) FROM %s;", TABLE_NAME);
        public static final String INSERT_INTO_QUERY = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?);", TABLE_NAME, ID_COL, USERNAME_COL, CREATION_DATE_COL, NOTES_COL);
        public static final String SELECT_ALL_USERS_QUERY = String.format("SELECT * FROM %s;", TABLE_NAME);
        public static final String SELECT_USER_BY_USERNAME_QUERY = String.format("SELECT * FROM %s WHERE %s=", TABLE_NAME, USERNAME_COL);
        public static final String UPDATE_USER_NOTES_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?", TABLE_NAME, NOTES_COL, ID_COL);
    }
}
