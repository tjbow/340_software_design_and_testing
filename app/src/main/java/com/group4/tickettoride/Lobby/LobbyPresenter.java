package com.group4.tickettoride.Lobby;

import android.content.Intent;

import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by abgill on 5/16/2017.
 */

public class LobbyPresenter implements Observer,ILobbyPresenter {

    private LobbyActivity activity;
    private Game game;

    public LobbyPresenter(LobbyActivity activity)
    {

        this.activity = activity;
        game = ClientModel.SINGLETON.getGame();
        ClientModel.SINGLETON.addObserver(this);
        setGameInfo();
    }

    @Override
    public void startGame() {
        NextLayerFacade.SINGLETON.startGame(game.getGameName());
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
        if (arg.getClass() == Game.class)
        {
            //gets the updated version of itself from the gameList
            this.game = (Game) arg;

            if(this.game.getStatus() == GAME_STATUS.ONGOING)
            {
                startGameActivity(o);
            }

            setGameInfo();

        }
        else if (arg.getClass() == String.class)
        {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayError( (String) arg);
                }
            });
        }
        else if (arg.getClass() == Boolean.class)
        {
            startGameActivity(o);
        }
        else if(arg.getClass() == GameList.class)
        {
            this.game = ClientModel.SINGLETON.getGame();
            setGameInfo();
        }
    }

    private void startGameActivity(Observable o)
    {
        o.deleteObserver(this);
        Intent i = GameActivity.newIntent(activity, ClientModel.SINGLETON.getGame().getGameName());
        activity.startActivity(i);
        activity.finish();
    }

    private void displayError(String error)
    {
        activity.displayError(error);
    }

    public void setGameInfo()
    {
        activity.setPlayerList(game.getPlayers());
        activity.setCurrentPlayers(game.getCurrentPlayerSize());
        activity.setMaxPlayers(game.getPlayerCount());
        activity.setGameName(game.getGameName());

        //check if the game is full. If it is, enable the start button, else disable it
        if (game.getCurrentPlayerSize() < game.getPlayerCount())
        {
            activity.setStartButtonEnabled(false);
        }
        else
        {
            activity.setStartButtonEnabled(true);
        }
    }
}
