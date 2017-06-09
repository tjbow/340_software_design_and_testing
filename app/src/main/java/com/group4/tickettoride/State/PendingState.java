package com.group4.tickettoride.State;

import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;

/**
 * Created by Russell Fitzpatrick on 6/9/2017.
 */

public class PendingState extends State{

    public PendingState(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        super(gamePresenter, cardDecksPresenter);
        setError("Waiting for Server");
    }
}
