package com.group4.tickettoride.Lobby;

import android.content.Intent;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.LinkedList;
import java.util.List;
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
        //TODO @john: implement start game
        NextLayerFacade.SINGLETON.startGame(game.getGameName());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Game.class)
        {
            //TODO: @john: gameName change
            //gets the updated version of itself from the gameList
            this.game = (Game) arg;
            setGameInfo();

        }
        else if (arg.getClass() == String.class)
        {
            activity.displayError((String) arg);
        }
        else if (arg.getClass() == Boolean.class)
        {
            o.deleteObserver(this);
            Intent i = new Intent(activity, GameActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void setGameInfo()
    {
        List<Player> players = new LinkedList<>();
        for (String key : game.getPlayers().keySet())
        {
            players.add(game.getPlayers().get(key));
        }

        activity.setPlayerList(players);

        activity.setCurrentPlayers(game.getCurrentPlayerSize());
        activity.setMaxPlayers(game.getPlayerCount());

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
