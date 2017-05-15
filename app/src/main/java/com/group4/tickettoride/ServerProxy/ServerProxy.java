package com.group4.tickettoride.ServerProxy;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ServerProxy implements IServer {

    private ServerProxy(){}

    public static ServerProxy SINGLETON = new ServerProxy();

    @Override
    public Results login(User user) {
        return null;
    }

    @Override
    public Results register(User user) {
        return null;
    }

    @Override
    public Results createGame() {
        return null;
    }

    @Override
    public Results joinGame(int gameId) {
        return null;
    }

    @Override
    public Results startGame(int gameId) {
        return null;
    }
}
