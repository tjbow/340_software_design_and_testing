package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CJoinGameCommandData;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by abgill on 5/15/2017.
 */

public class CJoinGameCommand extends CJoinGameCommandData implements IClientCommand{
    @Override
    public Results execute() {
        return ClientFacade.SINGLETON.onJoinGame(super.getGameName());
    }
}
