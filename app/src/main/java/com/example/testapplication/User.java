package com.example.testapplication;

public class User {
    String username, password;
    int userId;
    static int nextUserID=1;
    // we can add name, address, and email later on.

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        userId = nextUserID;
        nextUserID++;
    }
}
