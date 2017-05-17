package com.group4.tickettoride.GameList;


import android.content.Intent;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;

import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Lobby.LobbyActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class GameListPresenter implements IGameListPresenter, Observer {

    private GameListActivity activity;

    private ICreateGameFragment fragment;

    public GameListPresenter(GameListActivity activity)
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
    public void joinGame(String gameName) {
        //TODO @john: are we using position or id or name?
        NextLayerFacade.SINGLETON.joinGame(gameName);


    }

    @Override
    public void createGame() {
        String gameName = fragment.getGameName();
        int playerCount = fragment.getPlayerCount();


        NextLayerFacade.SINGLETON.createGame(gameName, playerCount);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO @john: is this how we want it?
        if (arg.getClass() == GameList.class)
        {
            activity.setGameList( (GameList) arg );
        }
        else if (arg.getClass() == String.class)
        {
            //start Lobby activity
            Intent i = LobbyActivity.newIntent(activity, (String) arg);
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void testRecycler()
    {
        Game game1 = new Game("Test Game", 2);
        List<Game> games = new ArrayList<>();
        games.add(game1);
        GameList gameList = new GameList(games);

        this.activity.setGameList(gameList);
    }
}
