package com.group4.ServerModel;

import com.group4.shared.Model.Game;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;

import java.util.List;

/*
 * Created by Tom on 5/14/2017.
 */

/**
 * Provides access to important server functions
 */
public class ServerFacade implements IServer, IClient
{
    @Override
    public Results login(User user)
    {
        return null;
    }

    @Override
    public Results register(User user)
    {
        return null;
    }

    @Override
    public Results createGame()
    {
        return null;
    }

    @Override
    public Results joinGame(int gameId)
    {
        return null;
    }

    @Override
    public Results startGame(int gameId)
    {
        return null;
    }

    @Override
    public Game reportGameState()
    {
        return null;
    }

    @Override
    public List<String> getGameList()
    {
        return null;
    }
}
