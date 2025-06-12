package edu.vassar.cmpu203.flash.model;

import androidx.annotation.NonNull;
import java.util.Set;
import java.util.HashSet;

public class User {
    private static Set<String> usernames = new HashSet<>();
    private String username;
    private String password;

    public User(String username, String password) {
        if (usernames.contains(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        this.username = username;
        this.password = password;
        usernames.add(username);
    }

    public static boolean logIn(String username, String password) {
        return usernames.contains(username);
    }

    public String getUsername() {
        return username;
    }
}




