package com.group4.shared.command.Client;

import com.group4.shared.command.ClientCommand;

/**
 * Created by abgill on 5/15/2017.
 */

public class CJoinGameCommandData extends ClientCommand {
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
