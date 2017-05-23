package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.ReturnDestCardCommandData;

/**
 * Created by tyler on 5/23/17.
 */

public class ReturnDestCardCommand extends ReturnDestCardCommandData implements IServerCommand
{
    private Results returnDestCard()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.returnDestinationCard(super.getReturnedCard());
    }

    @Override
    public Results execute()
    {
        return returnDestCard();
    }
}
