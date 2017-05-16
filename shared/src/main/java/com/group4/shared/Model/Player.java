package com.group4.shared.Model;

import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Player {

    private UUID id;
    private User user;
    private String userName;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //TODO: TYLER: I'm thinking about a change here so the user object isn't stored within a player,
    // since that would mean the user's password gets sent around to all the clients. See the 2nd constructor for an alternative.
    public Player(UUID id, User user) {
        this.id = id;
        this.user = user;
    }

    public Player(User user)
    {
        this.userName = user.getUsername();
        this.id = user.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
