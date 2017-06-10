package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetSnapshotCommandData;

/**
 * Created by tyler on 6/9/17.
 */

public class GetSnapshotCommand extends GetSnapshotCommandData implements IServerCommand
{
    private Results getSnapshot()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.getSnapshot(super.getUserName());
    }

    @Override
    public Results execute()
    {
        return getSnapshot();
    }
}
