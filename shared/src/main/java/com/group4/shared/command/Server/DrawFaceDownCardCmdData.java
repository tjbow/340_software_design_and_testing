package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.command.Command;

/**
 * Created by tyler on 6/2/17.
 */

public class DrawFaceDownCardCmdData extends Command
{
    String userName;
    TrainCard selectedCard;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public TrainCard getSelectedCard()
    {
        return selectedCard;
    }

    public void setSelectedCard(TrainCard selectedCard)
    {
        this.selectedCard = selectedCard;
    }
}
