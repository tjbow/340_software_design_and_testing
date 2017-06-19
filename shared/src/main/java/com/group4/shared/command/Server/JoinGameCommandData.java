package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by abgill on 5/15/2017.
 */

public class JoinGameCommandData extends Command {
    String gameName;
    String userName;

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
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
