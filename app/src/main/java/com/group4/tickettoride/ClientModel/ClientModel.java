package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.GameList;
import com.group4.shared.Model.User;

import java.util.Observable;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;
    // Todo: int commandIDIndex, last command run

    private ClientModel(User user, GameList gameList) {
        this.user = user;
        this.gameList = gameList;
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

    /**
     * Runs the commands in the commandList on the client model
     */
    // Todo: public void runCommands(CommandList commandList)

}
