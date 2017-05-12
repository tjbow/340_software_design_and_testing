package com.group4.server.Command;

import com.group4.shared.Model.Result;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.RegisterCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class RegisterCommand extends RegisterCommandData implements IServerCommand{
    IClientCommand register(){
        return new IClientCommand() {
            @Override
            public void execute() {

            }
        };
    }

    @Override
    public Result execute() {
        return null;
    }
}
