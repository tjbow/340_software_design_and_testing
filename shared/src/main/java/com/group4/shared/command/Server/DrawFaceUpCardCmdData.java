package com.group4.shared.command.Server;

import com.group4.shared.command.Command;

/**
 * Created by tyler on 6/2/17.
 */

public class DrawFaceUpCardCmdData extends Command
{
    String userName;
    int cardPosition;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getCardPosition()
    {
        return cardPosition;
    }

    public void setCardPosition(int cardPosition)
    {
        this.cardPosition = cardPosition;
    }
}
