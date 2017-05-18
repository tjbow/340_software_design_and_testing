package com.group4.tickettoride.Game;

import android.content.Intent;

import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by tyler on 5/18/17.
 */

public class GamePresenter implements IGamePresenter, Observer
{
    private GameActivity activity;

    public GamePresenter(GameActivity activity)
    {
        this.activity = activity;
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void endGame(String gameName)
    {
        NextLayerFacade.SINGLETON.endGame(gameName);
    }

    @Override
    public void onBackPressed()
    {
        activity.finish();
        ClientModel.SINGLETON.deleteObserver(this);
    }

    @Override
    public void update(Observable o, final Object arg)
    {
        if(arg.getClass() == Boolean.class)
        {
            o.deleteObserver(this);
            //end game and start GameListActivity
            Intent i = new Intent(activity, GameListActivity.class);
            activity.startActivity(i);
            activity.finish();
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
    }

    private void displayError(String error)
    {
        activity.displayError(error);
    }
}
