package com.group4.shared.command.Client;

import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.shared.command.ClientCommand;

/**
 * Created by tyler on 6/5/17.
 */

public class CUpdateStateCommandData extends ClientCommand
{
    String userName;
    MOVE_STATE state;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public MOVE_STATE getState()
    {
        return state;
    }

    public void setState(MOVE_STATE state)
    {
        this.state = state;
    }
}
