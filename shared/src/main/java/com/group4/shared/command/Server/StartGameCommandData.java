package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by beefhead on 5/12/2017.
 */

public class StartGameCommandData extends Command {
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
