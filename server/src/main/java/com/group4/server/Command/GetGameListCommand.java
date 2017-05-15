package com.group4.server.Command;

import com.group4.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetGameListCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements IServerCommand{
    public Results getGameList(){
        IClient serverFacade = null;
        return serverFacade.getGameList();
    }

    @Override
    public Results execute() {
        return getGameList();
    }
}
