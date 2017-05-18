package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.StartGameCommandData;

/**
 * Created by tyler on 5/18/17.
 */

public class EndGameCommand extends StartGameCommandData implements IServerCommand
{
    private Results endGame(){
        IServer serverFacade = new ServerFacade();
        return serverFacade.endGame(super.getGameName());
    }

    @Override
    public Results execute()
    {
        return endGame();
    }
}
