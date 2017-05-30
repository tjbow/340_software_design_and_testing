package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/30/17.
 */

public class DrawTrainCardsCommandData extends Command
{
    String userName;
    List<TrainCard> trainCards;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<TrainCard> getTrainCards()
    {
        return trainCards;
    }

    public void setTrainCards(List<TrainCard> trainCards)
    {
        this.trainCards = trainCards;
    }
}
