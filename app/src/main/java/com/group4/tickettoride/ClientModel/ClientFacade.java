package com.group4.tickettoride.ClientModel;

import com.google.gson.Gson;
import com.group4.shared.Model.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.MessageList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.RouteList;
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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Created by Tom on 5/15/2017.
 */

public class ClientFacade implements IClient,IComandExec
{
    private Poller poller;
    private ExecutorService threadPoolExecutor;
    private Future pollerTask;

    private ClientFacade()
    {
        threadPoolExecutor = Executors.newSingleThreadExecutor();
        poller = new Poller();
        pollerTask = threadPoolExecutor.submit(poller);
//         Add a shutdown hook to close down the thread when the app shutsdown
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run()
            {
                pollerTask.cancel(true);
            }
        });
    }

    public static ClientFacade SINGLETON = new ClientFacade();

    @Override
    public Results reportGameState()
    {
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

        ClientModel.SINGLETON.setGame(ClientModel.SINGLETON.getGameList().getGameByName(gameName));

        ClientModel.SINGLETON.setPlayer();

        ClientModel.SINGLETON.sendToObservers(true);

        return null;
    }

    @Override
    public Results onCreateGame() {



        return null;
    }

    @Override
    public Results onLogin(String authToken, String username) {

        ClientModel.SINGLETON.setAuthToken(authToken);
        ClientModel.SINGLETON.setUser( new User(username) );
        ClientModel.SINGLETON.checkForGame();

        if (ClientModel.SINGLETON.getGame() != null)
        {
            //user is already in a game, send status
            GAME_STATUS status = ClientModel.SINGLETON.getGame().getStatus();
            ClientModel.SINGLETON.sendToObservers(status);
        }
        else
        {
            //user is not in a game, move to next activity
            ClientModel.SINGLETON.sendToObservers(true);
        }

        poller.setUpdateGameList(true);

        return null;
    }

    @Override
    public Results onRegister(String authToken, String username) {

        onLogin(authToken, username);

        return null;
    }

    @Override
    public Results onStartGame()
    {
        //poller.setUpdateGameList(false);
        ClientModel.SINGLETON.sendToObservers(true);
        ClientModel.SINGLETON.sendToObservers(ClientModel.SINGLETON.getGame());

        return null;
    }

    @Override
    public Results onEndGame()
    {
        poller.setUpdateGameList(true);
        ClientModel.SINGLETON.sendToObservers(true);
        return null;
    }

    @Override
    public Results onGetGameList(GameList gameList) {

        ClientModel.SINGLETON.setGameList(gameList);

        ClientModel.SINGLETON.updateGame(gameList);

        return null;
    }

    @Override
    public Results onReportGameState(Game gameState) {

        ClientModel.SINGLETON.setGame(gameState);

        return null;
    }

    @Override
    public Results onUpdateChat(MessageList messageList)
    {
        return null;
    }

    @Override
    public Results onUpdateTurnHistory(MessageList turnHistory)
    {
        return null;
    }

    @Override
    public Results onUpdatePlayers(List<Player> playerData)
    {
        return null;
    }

    @Override
    public Results onUpdateMapData(RouteList routeSegments, List<City> cities)
    {
        return null;
    }

    public void startPoller()
    {
        poller.setUpdateGameList(true);
    }
}
