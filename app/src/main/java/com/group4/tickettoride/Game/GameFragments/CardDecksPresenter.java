package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;
import com.group4.tickettoride.State.State;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tom on 5/24/2017.
 */

public class CardDecksPresenter implements ITrainDeckPresenter, Observer
{
    ITrainDeckFragment trainFragment;

    private State state = State.SINGLETON;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    CardDecksPresenter(ITrainDeckFragment inTrainFragment)
    {
        state.setCardDecksPresenter(this);
        trainFragment = inTrainFragment;
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void drawFaceUpCard(int num)
    {

        state.drawFaceUpTrainCard(num);

//        if(ClientModel.SINGLETON.getPlayer().isTurn())
//        {
//            NextLayerFacade.SINGLETON.drawFaceUpTrainCard(num);
//        }
//        else
//        {
//            ClientModel.SINGLETON.sendToObservers("It's not your turn!");
//        }
    }

    @Override
    public void drawTrainCard()
    {
        state.drawTrainCard();

//        if(ClientModel.SINGLETON.getPlayer().isTurn())
//        {
//            NextLayerFacade.SINGLETON.drawFaceDownTrainCard();
//        }
//        else
//        {
//            ClientModel.SINGLETON.sendToObservers("It's not your turn!");
//        }
    }

    @Override
    public void drawDestCards()
    {
        state.drawDestCards();

//        if(ClientModel.SINGLETON.getPlayer().isTurn())
//        {
//            NextLayerFacade.SINGLETON.drawDestinationCards();
//        }
//        else
//        {
//            ClientModel.SINGLETON.sendToObservers("It's not your turn!");
//        }
    }

    @Override
    public List<TrainCard> getFaceUpCards()
    {
        return ClientModel.SINGLETON.getGame().getDecks().getFaceUpDeck().getFaceUpCards();
    }

    @Override
    public int getTrainDeckCardsRemaining()
    {
        return ClientModel.SINGLETON.getGame().getDecks().getTrainCardDeck().getCardDeck().size();
    }

    @Override
    public int getDestDeckCardsRemaining()
    {
        return ClientModel.SINGLETON.getGame().getDecks().getDestinationCardDeck().getDestDeck().size();
    }

    @Override
    public void update(Observable observable, Object o)
    {
        trainFragment.setFaceUpCards(getFaceUpCards());
        trainFragment.setDestCardsRemaining(getDestDeckCardsRemaining());
        trainFragment.setTrainDeckCardsRemaining(getTrainDeckCardsRemaining());

        if (o.getClass() == MOVE_STATE.class){
            state.updateState((MOVE_STATE) o);
        }
    }


}
