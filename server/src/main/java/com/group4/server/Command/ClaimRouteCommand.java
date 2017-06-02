package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.ClaimRouteCommandData;

/**
 * Created by tyler on 6/2/17.
 */

public class ClaimRouteCommand extends ClaimRouteCommandData implements IServerCommand
{
    private Results claimRoute()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.claimRoute(super.getUserName(), super.getClaimedRoute(), super.getUsedCards());
    }
    @Override
    public Results execute()
    {
        return claimRoute();
    }
}
