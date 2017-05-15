package com.group4.server.Network;

import com.google.gson.Gson;
import com.group4.server.Command.LoginCommand;
import com.group4.shared.Model.Results;
import com.group4.shared.command.Command;
import com.group4.shared.command.IServerCommand;

/**
 * Created by tyler on 5/12/17.
 */

public class ExecCommandHandler
{
    public Results handleCommand(String requestBody)
    {
        // deserialize to get the type
        Command requestCommand = Serializer.deserializeCommand(requestBody);

        //TODO: TYLER: fix the following to properly handle the right commands
        //execute the command object
        Results results = null;
        if(requestCommand.getType().equals("login"))
        {
            IServerCommand cmd = new Gson().fromJson(requestBody, LoginCommand.class);
            results = cmd.execute();
        }
        else if(requestCommand.getType().equals("register"))
        {
//            RegisterCommand cmd = new RegisterCommand(requestCommand.getState());
//            results = cmd.execute();
        }
        else if(requestCommand.getType().equals("test"))
        {
            results = new Results(false, "The test is successful", null, null);
        }
        //add more else if statements on this line for other command types
        else
        {
            //output an error
            results = new Results(false, null, "Invalid command object (type not yet implemented).", null);
        }
        return results;
    }
}
