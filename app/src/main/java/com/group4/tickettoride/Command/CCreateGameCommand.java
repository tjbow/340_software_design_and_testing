package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Client.CCreateGameCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by abgill on 5/12/2017.
 */

public class CCreateGameCommand extends CCreateGameCommandData implements IClientCommand {
    @Override
    public Results execute() {
        return createGame();
    }

    Results createGame(){
        return null;
    }
}
