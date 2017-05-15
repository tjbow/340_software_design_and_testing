package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by abgill on 5/15/2017.
 */

public class JoinGameCommandData extends Command {
    int gameID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
