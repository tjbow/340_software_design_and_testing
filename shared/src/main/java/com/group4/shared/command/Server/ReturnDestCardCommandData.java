package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class ReturnDestCardCommandData extends Command
{
    List<DestinationCard> returnedCard;

    public List<DestinationCard> getReturnedCard()
    {
        return returnedCard;
    }

    public void setReturnedCard(List<DestinationCard> returnedCard)
    {
        this.returnedCard = returnedCard;
    }
}
