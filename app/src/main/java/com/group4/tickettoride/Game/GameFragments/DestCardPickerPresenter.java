package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tom on 5/26/2017.
 */

public class DestCardPickerPresenter implements Observer, IDestCardPickerPresenter
{
    private IDestCardPickerFragment destFragment;
    private DestinationCardDeck cards;

    public DestCardPickerPresenter(IDestCardPickerFragment fragment)
    {
        destFragment = fragment;
        ClientModel.SINGLETON.addObserver(this);
        //cards = ClientModel.SINGLETON.
    }

    @Override
    public void addDestination(List<String> cardIDs)
    {

    }



    @Override
    public List<DestinationCard> drawThreeNewCards()
    {
        return null;
    }

    @Override
    public void returnDestinationCards(DestinationCardDeck cards)
    {

    }

    @Override
    public void update(Observable observable, Object o)
    {

    }
}
