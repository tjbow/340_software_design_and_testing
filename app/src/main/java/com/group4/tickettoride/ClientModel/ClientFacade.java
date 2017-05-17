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
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.Command.CLoginCommand;
import com.group4.tickettoride.Command.CRegisterCommand;

/*
 * Created by Tom on 5/15/2017.
 */

public class ClientFacade implements IClient,IComandExec
{
    private Poller poller;
    private Thread pollerThread;

    private ClientFacade()
    {
        poller = new Poller();
        pollerThread = new Thread(poller, "Poller Thread");
        pollerThread.start();
    }

    public static ClientFacade SINGLETON = new ClientFacade();

    @Override
    public Results reportGameState()
    {
        return null;
    }

    //@Override
    public Results getGameList()
    {
        //TODO: Tom: We should probably pull getGameList() out of IClient, and this function should
        // just return GameList object
        //return ClientModel.SINGLETON.getGameList();
        return null;
    }

    /**
     * Processes the results, runs commands if success and displays the error message if failure
     * @param results contains the command list to be executed or error message to be displayed
     */
    public void processResults(Results results)
    {
        if(!results.isSuccess())
        {
            String errorMessage = results.getErrorInfo();

            // send errorMessage to the UI from here
            ClientModel.SINGLETON.sendToObservers(errorMessage);

            return;
        }
        //else:
        CommandList cmdList = results.getCommandList();
        for(ClientCommand cmd : cmdList.getCommandList())
        {
            ((IClientCommand) cmd).execute();
            ClientModel.SINGLETON.setCommandIDIndex(cmd.getCommandNumber());
        }
    }

    @Override
    public Results onJoinGame(String gameName) {

        //TODO @john: added this for gameName support
        //int position = ClientModel.SINGLETON.getGameList().getGameByName());

        ClientModel.SINGLETON.setPlayer();

        ClientModel.SINGLETON.getGameList().addPlayer(ClientModel.SINGLETON.getUser().getUsername(),
                ClientModel.SINGLETON.getPlayer(), gameName);

        return null;
    }

    @Override
    public Results onCreateGame() {
        return null;
    }

    @Override
    public Results onLogin(String authToken) {

        ClientModel.SINGLETON.setAuthToken(authToken);
        ClientModel.SINGLETON.sendToObservers(true);
        if(ClientModel.SINGLETON.getUser() != null) // make sure the user logged in successfully
        {
            poller.setUpdateGameList(true);
        }
        return null;
    }

    @Override
    public Results onRegister(String authToken) {

        ClientModel.SINGLETON.setAuthToken(authToken);
        ClientModel.SINGLETON.sendToObservers(true);
        if(ClientModel.SINGLETON.getUser() != null) // make sure the user logged in successfully
        {
            poller.setUpdateGameList(true);
        }
        return null;
    }

    @Override
    public Results onStartGame()
    {
        poller.setUpdateGameList(false);
        return null;
    }

    @Override
    public Results onGetGameList(GameList gameList) {

        ClientModel.SINGLETON.setGameList(gameList);

        return null;
    }

    @Override
    public Results onReportGameState(Game gameState) {

        ClientModel.SINGLETON.getGameList().setGame(gameState, ClientModel.SINGLETON.getGameList().findGame(gameState.getGameId()));

        return null;
    }
}
