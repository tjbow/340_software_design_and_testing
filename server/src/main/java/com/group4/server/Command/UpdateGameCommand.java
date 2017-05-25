package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetGameStateCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class UpdateGameCommand extends GetGameStateCommandData implements IServerCommand{
    private Results updateGame(){
        IServer serverFacade = new ServerFacade();
//        return serverFacade.get();
        return null;
    }

    @Override
    public Results execute() {
        return updateGame();
    }
}
