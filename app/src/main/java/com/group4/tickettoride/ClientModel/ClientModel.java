package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.GameList;
import com.group4.shared.Model.PlayerList;
import com.group4.shared.Model.User;

import java.util.Observable;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;
    private PlayerList playerList;
    // Todo: int commandIDIndex, last command run

    public ClientModel(User user, GameList gameList, PlayerList playerList) {
        this.user = user;
        this.gameList = gameList;
        this.playerList = playerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
        notifyObservers(gameList);
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    /**
     * Runs the commands in the commandList on the client model
     */
    // Todo: public void runCommands(CommandList commandList)

}
