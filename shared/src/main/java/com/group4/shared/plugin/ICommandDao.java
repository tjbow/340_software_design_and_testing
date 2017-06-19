package com.group4.shared.plugin;

import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/14/2017.
 */

public interface ICommandDao {

    public void updateCommands(String gameName, List<Command> commands);
    public List<Command> getCommands(String gameName);
    public void clear();

}
