package com.group4.tickettoride.NextLayerFacade;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;

public class NextLayerFacade implements INextLayerFacade {

    private NextLayerFacade(){}

    public static NextLayerFacade SINGLETON = new NextLayerFacade();

    @Override
    public void login(String username, String password) {
        User loggedUser = new User(username, password);
        ServerProxy.SINGLETON.login(loggedUser);
    }

    @Override
    public void register(String username, String password) {
        User registeredUser = new User(username, password);
        ServerProxy.SINGLETON.register(registeredUser);
    }

    @Override
    public void createGame(String gameName, int playerCount) {
        ServerProxy.SINGLETON.createGame(gameName, playerCount);
    }

    @Override
    public void joinGame(String gameName) {
        //Game game = ClientModel.SINGLETON.getGameList().getGame(position);
        ServerProxy.SINGLETON.joinGame(gameName);
    }
}
