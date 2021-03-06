package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.CreateGameCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CreateGameCommand  extends CreateGameCommandData implements IServerCommand{
    public Results createGame(){
        IServer serverFacade = new ServerFacade();
        return serverFacade.createGame(super.getGameName(), super.getNumberOfPlayers());
    }

    @Override
    public Results execute() {
        return createGame();
    }
}
