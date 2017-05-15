package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by beefhead on 5/12/2017.
 */

public class StartGameCommandData extends Command {
    private int gameID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
