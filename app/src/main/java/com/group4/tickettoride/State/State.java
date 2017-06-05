package com.group4.tickettoride.State;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/5/2017.
 */

public class State {
    GamePresenter gamePresenter;
    CardDecksPresenter cardDecksPresenter;

    public State(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        this.gamePresenter = gamePresenter;
        this.cardDecksPresenter = cardDecksPresenter;
    }

    private String error = "Action not Available: Not your turn";

    public void drawTrainCard() {gamePresenter.displayError(error);}
    public void drawFaceUpTrainCard(int num) {gamePresenter.displayError(error);}
    public void drawDestCards() {gamePresenter.displayError(error);}
    public void claimRoute(String routeId, List<TrainCard> usedCards) {gamePresenter.displayError(error);}

}
