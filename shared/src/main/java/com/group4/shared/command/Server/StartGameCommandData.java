package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by beefhead on 5/12/2017.
 */

public class StartGameCommandData extends Command {
    private String gameUUID;

    public String getGameUUID() {
        return gameUUID;
    }

    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }
}
