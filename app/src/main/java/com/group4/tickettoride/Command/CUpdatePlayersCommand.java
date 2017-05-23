package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CUpdatePlayersCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdatePlayersCommand extends CUpdatePlayersCommandData implements IClientCommand
{
    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onUpdatePlayers(super.getPlayerData());
    }
}
