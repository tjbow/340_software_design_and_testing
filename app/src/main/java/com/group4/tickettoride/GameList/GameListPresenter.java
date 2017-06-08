package com.group4.tickettoride.GameList;


import android.content.Intent;

import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;

import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Lobby.LobbyActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.ArrayList;
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
    public void onBackPressed()
    {
        Intent i = new Intent(activity, Login_RegisterActivity.class);
        activity.startActivity(i);
        ClientModel.SINGLETON.clear();
        ClientModel.SINGLETON.deleteObserver(this);
        ClientFacade.SINGLETON.setUpdateGameInfo(false);
        ClientFacade.SINGLETON.setUpdateGameList(false);
        activity.finish();
    }

    @Override
    public void update(Observable o, final Object arg) {
        if (arg.getClass() == GameList.class)
        {
            GameList gameList = (GameList) arg;
            GameList displayedGameList = new GameList(new ArrayList<Game>());

            for(Game game : gameList.getGameList())
            {
                if(!game.getStatus().equals(GAME_STATUS.ONGOING) && !game.getStatus().equals(GAME_STATUS.FINISHED))
                {
                    displayedGameList.add(game);
                }
            }

            activity.setGameList(displayedGameList);
        }
        else if (arg.getClass() == Boolean.class)
        {
            o.deleteObserver(this);
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


}
