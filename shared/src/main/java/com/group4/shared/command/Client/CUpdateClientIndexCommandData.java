package com.group4.shared.command.Client;

import com.group4.shared.command.ClientCommand;

/**
 * Created by tyler on 6/9/17.
 */

public class CUpdateClientIndexCommandData extends ClientCommand
{
    int currentIndex;

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex)
    {
        this.currentIndex = currentIndex;
    }
}
