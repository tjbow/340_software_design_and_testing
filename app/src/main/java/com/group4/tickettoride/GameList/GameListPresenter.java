package com.group4.tickettoride.GameList;


import com.group4.shared.Model.GameList;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameListPresenter implements IGameListPresenter, Observer {

    private IGameListActivity activity;

    public GameListPresenter(IGameListActivity activity)
    {
        this.activity = activity;
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void joinGame() {
        //TODO @john: implement
    }

    @Override
    public void createGame() {
        //TODO @john: implement
    }

    @Override
    public void update(Observable o, Object arg) {
        activity.setGameList( (GameList) arg );
    }
}
