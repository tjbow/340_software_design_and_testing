package com.group4.shared.Model;

import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class User {

    private String username;
    private String password;
    private UUID id;

    public User(String username, String password, UUID id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setId();
    }

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        id = UUID.randomUUID();
    }
}
