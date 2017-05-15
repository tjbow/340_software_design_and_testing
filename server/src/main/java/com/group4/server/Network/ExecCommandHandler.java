package com.group4.server.Network;

import com.google.gson.Gson;
import com.group4.server.Command.CreateGameCommand;
import com.group4.server.Command.LoginCommand;
import com.group4.server.Command.RegisterCommand;
import com.group4.server.Command.StartGameCommand;
import com.group4.shared.Model.Results;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.LoginCommandData;

/**
 * Created by tyler on 5/12/17.
 */

public class ExecCommandHandler
{
    public Results handleCommand(String requestBody)
    {
        // deserialize to get the type
        String type = Serializer.deserializeCommand(requestBody).getType();

        //execute the command object
        Results results = null;
        switch (type)
        {
            case "login":
                LoginCommand loginCommand = new Gson().fromJson(requestBody, LoginCommand.class);
                results = loginCommand.execute();
                break;
            case "register":
                RegisterCommand registerCommand = new Gson().fromJson(requestBody, RegisterCommand.class);
                results = registerCommand.execute();
                break;
            case "creategame":
                CreateGameCommand createGameCommand = new Gson().fromJson(requestBody, CreateGameCommand.class);
                results = createGameCommand.execute();
                break;
            case "joingame":
//                JoinGameCommand joinGameCommand = new Gson().fromJson(requestBody, JoinGameCommand.class);
//                results = joinGameCommand.execute();
                break;
            case "startgame":
                StartGameCommand startGameCommand = new Gson().fromJson(requestBody, StartGameCommand.class);
                results = startGameCommand.execute();
                break;
            default:
                results = new Results(false, null, "Command type not yet implemented. Ask Tyler to implement it.", null);
                break;
        }
        return results;
    }
}
