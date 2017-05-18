package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by tyler on 5/18/17.
 */

public class EndGameCommandData extends Command
{
    private String gameName;

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }
}
