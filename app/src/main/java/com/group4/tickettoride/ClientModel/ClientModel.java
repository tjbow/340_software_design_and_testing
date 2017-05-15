package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.GameList;
import com.group4.shared.Model.User;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;

    public static ClientModel SINGLETON = new ClientModel();

    private ClientModel() {}

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

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }
}
