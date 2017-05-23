package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetPendingCommandsData;

/**
 * Created by tyler on 5/23/17.
 */

public class GetPendingCommands extends GetPendingCommandsData implements IServerCommand
{
    private Results getPendingCommands()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.getPendingCommands(super.getUser(), super.getLastCmdExecuted());
    }

    @Override
    public Results execute()
    {
        return getPendingCommands();
    }
}
