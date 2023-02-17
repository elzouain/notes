package com.simon.notes;


import java.sql.SQLException;
import java.time.Instant;

import org.junit.Test;

import com.simon.notes.model.UsersDatabase;
import com.simon.notes.users.User;


public class AppTest 
{

    @Test
    public void Should_Create_New_User() throws SQLException
    {
        UsersDatabase db = new UsersDatabase();
        db.connect();
        User user = new User("testUser" + Instant.now().toEpochMilli());
        db.insertUser(user);
        assert db.printAvailableUsers().contains(user.getName()): "Failed to add user.";
    }
}
