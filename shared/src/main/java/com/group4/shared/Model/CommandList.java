package com.group4.shared.Model;

import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 5/12/17.
 */

public class CommandList
{
    public List<ClientCommand> commandList;

    public List<ClientCommand> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<ClientCommand> commandList) {
        this.commandList = commandList;
    }

    public CommandList()
    {
        this.commandList = new ArrayList<>();
    }
}
