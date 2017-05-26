package com.group4.tickettoride.Game;

import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by tyler on 5/18/17.
 */

public class GamePresenter implements IGamePresenter, Observer
{
    private GameActivity activity;
    private Game game;

    public GamePresenter(GameActivity activity)
    {
        this.activity = activity;
        getGame();
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void endGame(String gameName)
    {
        NextLayerFacade.SINGLETON.endGame(gameName);
    }

    @Override
    public Game getGame()
    {
        this.game = ClientModel.SINGLETON.getGame();
        activity.setGame(game);
        return null;
    }

    @Override
    public void onBackPressed()
    {
        ClientModel.SINGLETON.clear();
        ClientModel.SINGLETON.deleteObserver(this);
        ClientModel.SINGLETON.deleteObservers();
        activity.startNextActivity(Login_RegisterActivity.class);
        ClientFacade.SINGLETON.setUpdateGameInfo(false);
        ClientFacade.SINGLETON.setUpdateGameList(false);
    }

    @Override
    public void update(Observable o, final Object arg)
    {
        if(arg.getClass() == Boolean.class)
        {
            //do not use (in GamePresenter only)
        }
        else if(arg.getClass() == String.class)
        {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    displayError((String) arg);
                }
            });
        }
        else if(arg.getClass() == Game.class)
        {
            //this is entered if onUpdateGame is called because onUpdateGame sends a game obj to the observers
            this.getGame();
        }
        else if(arg.getClass() == GAME_STATUS.class)
        {
            GAME_STATUS status = (GAME_STATUS) arg;
            if(status == GAME_STATUS.FINISHED)
            {
                o.deleteObserver(this);
                activity.startNextActivity(GameListActivity.class);
            }
        }
    }

    private void displayError(String error)
    {
        activity.displayError(error);
    }
}
