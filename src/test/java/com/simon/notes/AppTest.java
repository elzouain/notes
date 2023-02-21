package com.simon.notes;


import java.sql.SQLException;
import java.time.Instant;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.simon.notes.model.UsersDatabase;
import com.simon.notes.users.Note;
import com.simon.notes.users.User;


public class AppTest 
{
	
	public static UsersDatabase usersDB = null;
	
	@BeforeClass
	public static void connectDB() {
		usersDB = new UsersDatabase();
		usersDB.connect();
	}
	
	@AfterClass
	public static void disconnectDB() {
        usersDB.close();
	}

    @Test
    public void Should_Create_New_User() throws SQLException
    {
        User user = new User("testUser" + Instant.now().toEpochMilli());
        usersDB.insertUser(user);
        assert usersDB.selectAllUserNames().contains(user.getName()): "Failed to add user.";
        usersDB.deleteUser(user.getName());
    }
    
    @Test
    public void Should_Delete_User() throws SQLException
    {
        User user = new User("testUser" + Instant.now().toEpochMilli());
        usersDB.insertUser(user);
        usersDB.deleteUser(user.getName());
        assert !usersDB.selectAllUserNames().contains(user.getName()): "Failed to delete user.";
    }
    
    @Test
    public void Should_Add_Note() throws SQLException
    {
        User user = new User("testUser" + Instant.now().toEpochMilli());
        user.getNoteKeeper().add(new Note("Note text"));
        usersDB.insertUser(user);
        User user2 = usersDB.selectUserByUsername(user.getName());
        assert user2.getNoteKeeper().get(0).getText().equals(user.getNoteKeeper().get(0).getText()): "Failed to add note.";
        usersDB.deleteUser(user.getName());
    }
}
