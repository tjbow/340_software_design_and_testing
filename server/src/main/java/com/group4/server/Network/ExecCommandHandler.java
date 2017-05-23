package com.group4.server.Network;

import com.google.gson.Gson;
import com.group4.server.Command.CreateGameCommand;
import com.group4.server.Command.EndGameCommand;
import com.group4.server.Command.GetGameListCommand;
import com.group4.server.Command.JoinGameCommand;
import com.group4.server.Command.LoginCommand;
import com.group4.server.Command.RegisterCommand;
import com.group4.server.Command.StartGameCommand;
import com.group4.server.ServerModel.ServerModel;
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
                if (ServerModel.getInstance().getTempUser() != null)
                {
                    CreateGameCommand createGameCommand = new Gson().fromJson(requestBody, CreateGameCommand.class);
                    results = createGameCommand.execute();
                }
                else results = noAuthToken();
                break;
            case "joingame":
                if (ServerModel.getInstance().getTempUser() != null)
                {
                    JoinGameCommand joinGameCommand = new Gson().fromJson(requestBody, JoinGameCommand.class);
                    results = joinGameCommand.execute();
                }
                else results = noAuthToken();
                break;
            case "startgame":
                if (ServerModel.getInstance().getTempUser() != null)
                {
                    StartGameCommand startGameCommand = new Gson().fromJson(requestBody, StartGameCommand.class);
                    results = startGameCommand.execute();
                }
                else results = noAuthToken();
                break;
            case "endgame":
                if(ServerModel.getInstance().getTempUser() != null)
                {
                    EndGameCommand endGameCommand = new Gson().fromJson(requestBody, EndGameCommand.class);
                    results = endGameCommand.execute();
                }
                else results = noAuthToken();
            case "getgamelist":
                GetGameListCommand getGameListCommand = new Gson().fromJson(requestBody, GetGameListCommand.class);
                results = getGameListCommand.execute();
                break;
            case "sendchat":
//                SendChatCommand sendChatCommand = new Gson().fromJson(requestBody, SendChatCommand.class);
//                results = sendChatCommand.execute();
                break;
            case "getpending":
//                GetPendingCommands getPendingCommands = new Gson().fromJson(requestBody, GetPendingCommands.class);
//                results = getPendingCommands.execute();
                break;
            case "drawdestcards":
//                DrawDestCardsCommand drawDestCardsCommand = new Gson().fromJson(requestBody, DrawDestCardsCommand.class);
//                results = drawDestCardsCommand.execute();
                break;
            case "returndestcard":
//                ReturnDestCardCommand returnDestCardCommand = new Gson().fromJson(requestBody, ReturnDestCarCommand.class);
//                results = returnDestCardCommand.execute();
                break;
            default:
                results = new Results(false, null, "Command type not yet implemented. Ask Tyler to implement it.", null);
                break;
        }
        return results;
    }

    private Results noAuthToken()
    {
        return new Results(false, null, "User not logged in", null);
    }
}
