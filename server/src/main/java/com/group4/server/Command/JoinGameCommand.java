package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.command.Server.JoinGameCommandData;
import com.group4.shared.command.IServerCommand;

/**
 * Created by abgill on 5/15/2017.
 */

public class JoinGameCommand extends JoinGameCommandData implements IServerCommand {
    @Override
    public Results execute() {
        return new ServerFacade().joinGame(super.getGameID());
    }
}
