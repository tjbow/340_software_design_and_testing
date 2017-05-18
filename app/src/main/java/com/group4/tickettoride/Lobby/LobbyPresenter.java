package com.group4.tickettoride.Lobby;

import android.content.Intent;

import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameActivity;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
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
        NextLayerFacade.SINGLETON.startGame(game.getGameName());
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(activity, Login_RegisterActivity.class);
        activity.startActivity(i);
        ClientModel.SINGLETON.deleteObserver(this);
        activity.finish();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Game.class)
        {
            //TODO: @john: gameName change
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
            activity.displayError((String) arg);
        }
        else if (arg.getClass() == Boolean.class)
        {
            startGameActivity(o);
        }
    }

    private void startGameActivity(Observable o)
    {
        o.deleteObserver(this);
        Intent i = GameActivity.newIntent(activity, ClientModel.SINGLETON.getGame().getGameName());
        activity.startActivity(i);
        activity.finish();
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
