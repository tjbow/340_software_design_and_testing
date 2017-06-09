package com.group4.tickettoride.State;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/5/2017.
 */

public class MyTurnState extends State{

    public MyTurnState(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        super(gamePresenter, cardDecksPresenter);
        gamePresenter.displayError("It is your Turn");
    }

    @Override
    public void drawTrainCard(){

        gamePresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
        cardDecksPresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));

        NextLayerFacade.SINGLETON.drawFaceDownTrainCard();
    }

    @Override
    public void drawFaceUpTrainCard(int num){

        gamePresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
        cardDecksPresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));

        NextLayerFacade.SINGLETON.drawFaceUpTrainCard(num);
    }

    @Override
    public void drawDestCards(){

        gamePresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
        cardDecksPresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));

        NextLayerFacade.SINGLETON.drawDestinationCards();
    }

    @Override
    public void claimRoute(String routeId, List<TrainCard> usedCards){

        gamePresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));
        cardDecksPresenter.setState(new PendingState(gamePresenter, cardDecksPresenter));

        NextLayerFacade.SINGLETON.claimRoute(routeId, usedCards);
    }
}
