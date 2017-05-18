package com.group4.tickettoride.GameList;


import android.content.Intent;

import com.group4.shared.Model.GAME_STATUS;
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
        NextLayerFacade.SINGLETON.joinGame(gameName);
    }

    @Override
    public void createGame() {
        String gameName = fragment.getGameName();
        int playerCount = fragment.getPlayerCount();


        NextLayerFacade.SINGLETON.createGame(gameName, playerCount);
    }

    @Override
    public void update(Observable o, final Object arg) {
        if (arg.getClass() == GameList.class)
        {
            GameList gameList = (GameList) arg;
            GameList displayedGameList = new GameList(new ArrayList<Game>());

            for(Game game : gameList.getGameList())
            {
                if(!game.getStatus().equals(GAME_STATUS.ONGOING))
                {
                    displayedGameList.getGameList().add(game);
                }
            }

            activity.setGameList(displayedGameList);
        }
        else if (arg.getClass() == Boolean.class)
        {
            //start Lobby activity
            Intent i = new Intent(activity, LobbyActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
        else if (arg.getClass() == String.class)
        {
            //it's an error
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayError( (String) arg);
                }
            });

        }
    }

    private void displayError(String error)
    {
        activity.displayError(error);
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
