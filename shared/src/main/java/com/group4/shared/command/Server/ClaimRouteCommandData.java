package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 6/2/17.
 */

public class ClaimRouteCommandData extends Command
{
    String userName;
    RouteSegment claimedRoute;
    List<TrainCard> usedCards;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public RouteSegment getClaimedRoute()
    {
        return claimedRoute;
    }

    public void setClaimedRoute(RouteSegment claimedRoute)
    {
        this.claimedRoute = claimedRoute;
    }

    public List<TrainCard> getUsedCards()
    {
        return usedCards;
    }

    public void setUsedCards(List<TrainCard> usedCards)
    {
        this.usedCards = usedCards;
    }
}
