package com.group4.ServerModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.User;

import java.util.List;
import java.util.Map;

/**
 * Holds the current state of the server
 */
public class ServerModel
{
    private List<User> users;
    private GameList gameList;
    private Map<User, String> userAuthTokens;
    private CommandList commandList; // holds all commands executed on the server to this point

    /**
     * Checks to see if a user is already registered
     * @param user
     * @return
     */
    public boolean doesUserExist(User user)
    {
        return false;
    }

    /**
     *  Takes an authToken and finds which user it belongs to, then returns that user
     * @param authToken
     * @return
     */
    public User validateUser(String authToken)
    {
        return null;
    }

    /**
     * Returns a boolean indicating success of add to DB
     * @param user
     * @return
     */
    public boolean registerUser(User user)
    {
        return false;
    }

    /**
     * Creates and returns an authToken for the supplied user
     * @param user
     * @return
     */
    public String loginUser(User user)
    {
        return null;
    }

    /**
     * Generates an authorization token
     * @return
     */
    public String generateToken()
    {
        return null;
    }

    /**
     * Adds a game to the game list on the server
     * @param game
     */
    public void addGame(Game game)
    {

    }

    /**
     * Gets the current list of games on the server
     * @return
     */
    public GameList getGameList()
    {
        return null;
    }
}
