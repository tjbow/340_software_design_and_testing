package com.group4.shared.command.Client;

import com.group4.shared.command.ClientCommand;

/**
 * Created by abgill on 5/15/2017.
 */

public class CJoinGameCommandData extends ClientCommand {
    private int gameID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
