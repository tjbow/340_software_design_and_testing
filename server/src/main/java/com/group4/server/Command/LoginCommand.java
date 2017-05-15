package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.LoginCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class LoginCommand extends LoginCommandData implements IServerCommand{
    Results login(){
        IServer serverFacade = new ServerFacade();
        return serverFacade.login(super.getUser());
    }

    @Override
    public Results execute() {
        return login();
    }
}
