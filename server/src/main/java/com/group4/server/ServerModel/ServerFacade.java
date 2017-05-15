package com.group4.server.ServerModel;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
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
    ServerModel serverModel = ServerModel.getInstance();

    @Override
    public Results login(User user)
    {
        String authToken = serverModel.loginUser(user);
        if(authToken != null)
        {
            System.out.println("User " + user.getUsername() + " logged in");
            return new Results(true, authToken, null, null);
        }
        else
        {
            System.out.println("User " + user.getUsername() + " attempted to login");
            return new Results(false, null, "Invalid login credentials", null);
        }
    }

    @Override
    public Results register(User user)
    {
        String authToken = serverModel.registerUser(user);
        if(authToken != null)
        {
            System.out.println("User " + user.getUsername() + " registered");
            return new Results(true, authToken, null, null);
        }
        else
        {
            System.out.println("User " + user.getUsername() + " failed to register");
            return new Results(false, null, "That username is taken", null);
        }
    }

    @Override
    public Results createGame()
    {
        //TODO: Tom: we need a parameter of number of players to create a game
        // does the player who starts the game automatically join it?
        // if so we need the user/player object to put them in the map
        // if not we need to get rid of the players map in the constructor for game
        // because it will always be empty to start
        // will we ever need to create a game with a map, or just a single player at most?
        return null;
    }

    @Override
    public Results joinGame(int gameId)
    {
        // need the player/user object to join them to a game
        return null;
    }

    @Override
    public Results startGame(int gameId)
    {
        return null;
    }

    @Override
    public Results reportGameState()
    {
        //TODO: Tom: what is this method supposed to do?
        return null;
    }

    @Override
    public Results getGameList()
    {
        //return serverModel.getGameList();
        return null;
    }
}
