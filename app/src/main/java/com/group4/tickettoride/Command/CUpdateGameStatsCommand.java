package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CUpdateGameStatsCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 5/24/17.
 */

public class CUpdateGameStatsCommand extends CUpdateGameStatsCommandData implements IClientCommand
{
    private Results updateGameStats()
    {
        return ClientFacade.SINGLETON.onUpdateGameStats(super.getGameStats());
    }

    @Override
    public Results execute()
    {
        return updateGameStats();
    }
}
