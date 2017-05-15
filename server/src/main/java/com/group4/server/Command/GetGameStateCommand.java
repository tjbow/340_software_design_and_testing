package com.group4.server.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetGameStateCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class GetGameStateCommand extends GetGameStateCommandData implements IServerCommand{
    private Results getGameState(){
        return null;
    }

    @Override
    public Results execute() {
        return null;
    }
}
