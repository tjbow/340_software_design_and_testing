package com.group4.shared.command.Client;

import com.group4.shared.Model.Message;
import com.group4.shared.Model.MessageList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateTurnHistoryCommandData extends ClientCommand
{
    TurnHistory turnHistory;

    public TurnHistory getTurnHistory()
    {
        return turnHistory;
    }

    public void setTurnHistory(TurnHistory turnHistory)
    {
        this.turnHistory = turnHistory;
    }
}
