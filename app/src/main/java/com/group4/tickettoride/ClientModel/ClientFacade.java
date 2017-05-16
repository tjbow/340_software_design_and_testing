package com.group4.tickettoride.ClientModel;

import com.google.gson.Gson;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IComandExec;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Client.CGetGameListCommandData;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Client.CRegisterCommandData;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.tickettoride.Command.CRegisterCommand;

/**
 * Created by Tom on 5/15/2017.
 */

public class ClientFacade implements IClient,IComandExec
{
    Poller poller;

    public static ClientFacade SINGLETON = new ClientFacade();

    @Override
    public Results reportGameState()
    {
        return null;
    }

    @Override
    public Results getGameList()
    {
        //return clientModel.getGameList();
        return null;
    }

    /**
     * Poll for the most recent set of commands
     */
    void pollForUpdates()
    {

    }

    public void processResults(Results results)
    {
        CommandList cmdList = results.getCommandList();
        for(Command cmd : cmdList.commandList)
        {
            String type = cmd.getType();
            switch (type)
            {
                case "login":
                    //set the authToken
                    //set the user
                    CLoginCommandData loginData = (CLoginCommandData) cmd;
                    ClientModel.SINGLETON.setAuthToken(loginData.getAuthToken());
                    break;
                case "register":
                    //set the authToken
                    //set the user
                    CRegisterCommandData registerData = (CRegisterCommandData) cmd;
                    ClientModel.SINGLETON.setAuthToken(registerData.getAuthToken());
                    break;
                case "getgamelist":
                    // set the game list
                    CGetGameListCommandData gameListData = (CGetGameListCommandData) cmd;
                    ClientModel.SINGLETON.setGameList(gameListData.getGameList());
                    break;
                case "joingame":
                    //TODO: @Tom or @Russ: Implement these
                    //do stuff
                    //
                    break;
                case "creategame":
                    //do stuff
                    //
                    break;
                default:
                    //do stuff
                    break;
            }
        }
    }

    @Override
    public Results onJoinGame(int gameID) {
        return null;
    }

    @Override
    public Results onCreateGame() {
        return null;
    }

    @Override
    public Results onLogin(String authToken) {
        return null;
    }

    @Override
    public Results onRegister(String authToken) {
        return null;
    }

    @Override
    public Results onStartGame() {
        return null;
    }

    @Override
    public Results onGetGameList(GameList gameList) {
        return null;
    }

    @Override
    public Results onReportGameState(Game gameState) {
        return null;
    }
}
