package com.group4.tickettoride.ClientModel;

import android.util.Log;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.GameStats;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Created by Tom on 5/15/2017.
 */

public class ClientFacade implements IClient
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

    public void setUpdateGameList(boolean poll)
    {
        poller.setUpdateGameList(poll);
    }

    public void setUpdateGameInfo(boolean poll)
    {
        poller.setUpdateGameInfo(poll);
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
        Log.i("ClientFacade", "cmdList size: " + cmdList.size());
        for(ClientCommand cmd : cmdList.getCommandList())
        {
            ((IClientCommand) cmd).execute();
            ClientModel.SINGLETON.setCommandIDIndex(cmd.getCommandNumber());
        }
    }

    //-----------------PRE-GAME-------------------------------------------------------
    @Override
    public Results onLogin(String authToken, String username) {

        poller.setUpdateGameList(true);
        poller.setUpdateGameInfo(false);
//        ClientModel.SINGLETON.additionalUserLogin(new User(username));

        ClientModel.SINGLETON.setAuthToken(authToken);
        ClientModel.SINGLETON.setUser(new User(username));
        ClientModel.SINGLETON.setCommandIDIndex(0);
        ClientModel.SINGLETON.setGameIfUserIsPlaying();

        if (ClientModel.SINGLETON.getGame() != null)
        {
            //user is already in a game, send status to LoginPresenter which decides to start
            //either LobbyActivity or GameActivity
            GAME_STATUS status = ClientModel.SINGLETON.getGame().getStatus();
            ClientModel.SINGLETON.sendToObservers(status);

            poller.setUpdateGameList(false);
            poller.setUpdateGameInfo(true);
        }
        else
        {
            //user is not part of a game, move to GameListActivity
            ClientModel.SINGLETON.sendToObservers(true);
        }

        return null;
    }

    @Override
    public Results onRegister(String authToken, String username) {

        onLogin(authToken, username);

        return null;
    }

    @Override
    public Results onJoinGame(String gameName) {

        ClientModel.SINGLETON.setGame(ClientModel.SINGLETON.getGameList().getGameByName(gameName));

        ClientModel.SINGLETON.setPlayer();

        ClientModel.SINGLETON.sendToObservers(true);

        //stop getting the gameList and start getting gameInfo instead
        poller.setUpdateGameList(false);
        poller.setUpdateGameInfo(true);

        return null;
    }

    @Override
    public Results onCreateGame() {
        return null;
    }

    @Override
    public Results onGetGameList(GameList gameList) {

        ClientModel.SINGLETON.setGameList(gameList);

//        ClientModel.SINGLETON.updateGame(gameList);

        return null;
    }

    //--------------------GAMEPLAY--------------------------------------------
    @Override
    public Results onUpdateGame(GAME_STATUS status) {
        Game game = ClientModel.SINGLETON.getGame();

//        game.setPlayers(playerList);

        ClientModel.SINGLETON.setGame(game);

        ClientModel.SINGLETON.sendToObservers(ClientModel.SINGLETON.getGame());

        return null;
    }

    @Override
    public Results onUpdateGameStats(GameStats gameStats)
    {
        ClientModel.SINGLETON.updateStats(gameStats);
        return null;
    }


    @Override
    public Results onStartGame()
    {
        ClientModel.SINGLETON.sendToObservers(true);
        return null;
    }

    @Override
    public Results onEndGame()
    {
        poller.setUpdateGameList(true);
        poller.setUpdateGameInfo(false);

        ClientModel.SINGLETON.sendToObservers(GAME_STATUS.FINISHED);
        return null;
    }

    @Override
    public Results onUpdateChat(ChatHistory chatHistory)
    {
        ClientModel.SINGLETON.setChatHistory(chatHistory);
        return null;
    }

    @Override
    public Results onUpdateTurnHistory(TurnHistory turnHistory)
    {
        ClientModel.SINGLETON.setTurnHistory(turnHistory);
        return null;
    }

    @Override
    public Results onUpdatePlayers(List<Player> playerData)
    {
        ClientModel.SINGLETON.updatePlayerInfo(playerData);
        return null;
    }

    @Override
    public Results onUpdateMapData(RouteList routeSegments, List<City> cities)
    {
        ClientModel.SINGLETON.updateMap(routeSegments, cities);
        return null;
    }

    //TODO: TYLER: add onUpdatePlayerHand
}
