package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tom on 5/24/2017.
 */

public class TrainDeckPresenter implements ITrainDeckPresenter, Observer
{
    @Override
    public TrainCardDeck drawFaceUpCard(int num)
    {
        return null;
    }

    @Override
    public TrainCard drawTrainCard()
    {
        return null;
    }

    @Override
    public List<DestinationCard> drawDestCards()
    {
        return null;
    }

    @Override
    public void update(Observable observable, Object o)
    {

    }
}
