package com.group4.server.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.RegisterCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class RegisterCommand extends RegisterCommandData implements IServerCommand{
    private Results register(){
        IServer serveFacade = null;  //TODO: DREW: update when implamented
        return serveFacade.register(super.getUser());
    }

    @Override
    public Results execute() {
        return register();
    }
}
