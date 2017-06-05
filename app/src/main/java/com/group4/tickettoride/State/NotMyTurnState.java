package com.group4.tickettoride.State;

import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;

/**
 * Created by Russell Fitzpatrick on 6/5/2017.
 */

public class NotMyTurnState extends State{

    public NotMyTurnState(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        super(gamePresenter, cardDecksPresenter);
    }
}
