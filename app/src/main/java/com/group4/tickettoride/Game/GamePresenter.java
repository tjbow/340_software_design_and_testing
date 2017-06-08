package com.group4.tickettoride.Game;

import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.shared.Model.Map.RouteList;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameFragments.DestCardPickerFragment;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;
import com.group4.tickettoride.R;
import com.group4.tickettoride.State.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tyler on 5/18/17.
 */

public class GamePresenter implements IGamePresenter, Observer
{
    private GameActivity activity;
    private GameMapView gameMapView;
    private Game game;
    private State state = State.SINGLETON;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public GamePresenter(GameActivity activity)
    {
        state.setGamePresenter(this);
        this.activity = activity;
        this.gameMapView = (GameMapView) activity.findViewById(R.id.game_map_view);
        ClientModel.SINGLETON.setRouteSegments(gameMapView.getRouteSegments());
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
                //start the end game dialog
                activity.showEndGame();

//                o.deleteObserver(this);
//                activity.startNextActivity(GameListActivity.class);
            }
            else if(status == GAME_STATUS.FINAL_TURN)
            {
                displayError("Last moves of the game!");
            }
        }
        else if(arg.getClass() == RouteList.class)
        {
            RouteList newRoutes = (RouteList) arg;
            gameMapView.setRouteSegments(newRoutes.getRouteList());
        }
        else if (arg.getClass() == DestinationCardDeck.class)
        {
            DestinationCardDeck deck = (DestinationCardDeck) arg;
            List<DestinationCard> receivedCards = deck.getDestDeck();
            Log.d("DestCardPickerPresenter", "PICKER CALLED");
            DialogFragment destPickerDialog = DestCardPickerFragment.newInstance(1);
            destPickerDialog.show(activity.getSupportFragmentManager(), "dialog");
        }
        else if (arg.getClass() == MOVE_STATE.class){
            state.updateState((MOVE_STATE) arg);
        }
    }

    public void displayError(String error)
    {
        activity.displayError(error);
    }
}
