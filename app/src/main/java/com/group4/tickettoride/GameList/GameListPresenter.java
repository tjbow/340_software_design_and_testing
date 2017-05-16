package com.group4.tickettoride.GameList;


import com.group4.shared.Model.GameList;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class GameListPresenter implements IGameListPresenter, Observer {

    private IGameListActivity activity;

    private ICreateGameFragment fragment;

    public GameListPresenter(IGameListActivity activity)
    {
        this.activity = activity;
        ClientModel.SINGLETON.addObserver(this);
    }

    //TODO @john: this seems a bit hackish
    public GameListPresenter(ICreateGameFragment fragment)
    {
        this.fragment = fragment;
    }

    @Override
    public void joinGame() {
        //TODO @john: implement

    }

    @Override
    public void createGame() {
        String gameName = fragment.getGameName();
        int playerCount = fragment.getPlayerCount();

        NextLayerFacade.SINGLETON.createGame(gameName, playerCount);
    }

    @Override
    public void update(Observable o, Object arg) {
        activity.setGameList( (GameList) arg );
    }
}
