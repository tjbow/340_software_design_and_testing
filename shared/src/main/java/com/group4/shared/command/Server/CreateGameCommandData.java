package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CreateGameCommandData extends Command {
    private String gameName;
    private int numberOfPlayers;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
