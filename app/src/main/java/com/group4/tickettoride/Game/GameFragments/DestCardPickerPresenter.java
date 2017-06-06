package com.group4.tickettoride.Game.GameFragments;

import android.util.Log;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.sql.StatementEvent;

/**
 * Created by Tom on 5/26/2017.
 */

public class DestCardPickerPresenter implements Observer, IDestCardPickerPresenter
{
    private IDestCardPickerFragment destFragment;
    Player player;

    private List<DestinationCard> cards;

    public DestCardPickerPresenter(DestCardPickerFragment fragment)
    {
        destFragment = fragment;
        ClientModel.SINGLETON.addObserver(this);
        player = ClientModel.SINGLETON.getPlayer();
        if(fragment.getMinNumSelected() == 2) // beginning of game
        {
            cards = ClientModel.SINGLETON.getPlayer().getPlayerHand().getDestinationCards().getDestDeck();
        }
        else
        {
//            cards = drawThreeNewCards();
            cards = ClientModel.SINGLETON.getTempDestPickerDeck();
            ClientModel.SINGLETON.setDestPickerDeck(null);
        }

    }

    @Override
    public void addDestination(List<DestinationCard> cards)
    {
        for(DestinationCard card : cards)
        {
            player.getPlayerHand().getDestinationCards().add(card);
        }
    }

    @Override
    public void returnSelectedDestCards(List<Integer> selCards)
    {
        List<DestinationCard> returnCards = new ArrayList<>();
        for(Integer sel : selCards)
        {
            if(sel < cards.size())
            {
                returnCards.add(cards.get(sel));
            }
        }
        ServerProxy.SINGLETON.returnDestinationCard(returnCards);
    }

    @Override
    public List<DestinationCard> drawThreeNewCards()
    {
        List<Integer> destIDBeforeDraw = new ArrayList<>(); // grab list before draw
        for(DestinationCard card : player.getPlayerHand().getDestinationCards().getDestDeck())
        {
            destIDBeforeDraw.add(card.getId());
        }
        // draw cards into player hand
        Results results = ServerProxy.SINGLETON.drawDestinationCards(player.getUserName());

        // if success, get the newly drawn cards
        if(results.isSuccess())
        {
            List<DestinationCard> destCardsAfterDraw = player.getPlayerHand().getDestinationCards().getDestDeck();
            List<DestinationCard> newDestCards = new ArrayList<>();
            for(DestinationCard card : destCardsAfterDraw)
            {
                if(!destIDBeforeDraw.contains(card.getId())) // this card wasn't present before drawn, it is new
                {
                    newDestCards.add(card);
                }
            }
            return newDestCards;
        }
        else
        {
            System.out.println("Failed to draw destination cards");
            throw new RuntimeException(results.getErrorInfo());
        }
    }

    @Override
    public void update(Observable observable, Object arg)
    {
        //
    }

    public List<DestinationCard> getCards()
    {
        return cards;
    }

    @Override
    public void setFirstDestCardsSelected()
    {
        ClientModel.SINGLETON.getPlayer().setFirstDestCardsSelected(true);
    }
}
