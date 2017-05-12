package com.group4.server.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.GetGameListCommandData;

/**
 * Created by beefhead on 5/12/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements IServerCommand{
    public IClientCommand getGameList(){
        return new IClientCommand() {
            @Override
            public void execute() {

            }
        };
    }

    @Override
    public Results execute() {
        return null;
    }
}
