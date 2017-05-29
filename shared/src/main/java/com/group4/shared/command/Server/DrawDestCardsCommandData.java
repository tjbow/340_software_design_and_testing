package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class DrawDestCardsCommandData extends Command
{
    String userName;
    List<DestinationCard> selectedCards;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<DestinationCard> getSelectedCards()
    {
        return selectedCards;
    }

    public void setSelectedCards(List<DestinationCard> selectedCards)
    {
        this.selectedCards = selectedCards;
    }
}
