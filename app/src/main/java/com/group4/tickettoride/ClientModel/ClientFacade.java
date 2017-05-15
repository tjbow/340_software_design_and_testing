package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IComandExec;
import com.group4.shared.Proxy.IServer;

/**
 * Created by Tom on 5/15/2017.
 */

public class ClientFacade implements IClient,IComandExec
{
    ClientModel clientModel; //TODO: Tom: ClientModel needs to be a singleton?
    Poller poller;
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

    void processResults(Results results)
    {

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
