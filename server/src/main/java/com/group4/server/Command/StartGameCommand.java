package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.StartGameCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class StartGameCommand extends StartGameCommandData implements IServerCommand{
    private Results startGame(){
        IServer serverFacade = new ServerFacade();
        return serverFacade.startGame(super.getGameName());
    }

    @Override
    public Results execute() {
        return startGame();
    }
}
