package com.group4.shared.command.Client;

import com.group4.shared.Model.CommandList;
import com.group4.shared.command.ClientCommand;

/**
 * Created by tyler on 5/23/17.
 */

public class CGetPendingCommandsData extends ClientCommand
{
    CommandList commandList;

    public CommandList getCommandList()
    {
        return commandList;
    }

    public void setCommandList(CommandList commandList)
    {
        this.commandList = commandList;
    }
}
