package com.group4.shared.Model;

/**
 * Created by tyler on 5/22/17.
 */

public class Message
{
    private String message;
    private String userName;
    private String color;

    public Message(String message, String userName, String color)
    {
        this.message = message;
        this.userName = userName;
        this.color = color;
    }

    public String getMessage()
    {
        return message;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getColor()
    {
        return color;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
