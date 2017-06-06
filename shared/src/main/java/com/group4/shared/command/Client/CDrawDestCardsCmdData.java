package com.group4.shared.command.Client;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.command.ClientCommand;

import java.util.List;

/**
 * Created by tyler on 6/5/17.
 */

public class CDrawDestCardsCmdData extends ClientCommand
{
    List<DestinationCard> receivedCards;

    public List<DestinationCard> getReceivedCards()
    {
        return receivedCards;
    }

    public void setReceivedCards(List<DestinationCard> receivedCards)
    {
        this.receivedCards = receivedCards;
    }
}
