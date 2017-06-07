package com.group4.tickettoride.State;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.tickettoride.Game.GameFragments.CardDecksPresenter;
import com.group4.tickettoride.Game.GamePresenter;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/5/2017.
 */

public class DrawnFirstCardState extends State{

    private String error = "You must draw another Train Card";

    public DrawnFirstCardState(GamePresenter gamePresenter, CardDecksPresenter cardDecksPresenter) {
        super(gamePresenter, cardDecksPresenter);
    }

    @Override
    public void drawTrainCard(){
        NextLayerFacade.SINGLETON.drawFaceDownTrainCard();
    }

    @Override
    public void drawFaceUpTrainCard(int num){
        if(cardDecksPresenter.getFaceUpCards().get(num).getColor() == CARD_COLOR.RAINBOW){
            String locomotiveError = "You cannot select a Locomotive";
            gamePresenter.displayError(locomotiveError);
        }
        else{
            NextLayerFacade.SINGLETON.drawFaceUpTrainCard(num);
        }
    }

    @Override
    public void drawDestCards(){
        gamePresenter.displayError(error);
    }

    @Override
    public void claimRoute(String routeId, List<TrainCard> usedCards){
        gamePresenter.displayError(error);
    }
}
