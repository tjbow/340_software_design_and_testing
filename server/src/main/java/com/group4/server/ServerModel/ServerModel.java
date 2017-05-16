package com.group4.server.ServerModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.User;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the current state of the server
 */
public class ServerModel
{
    private List<User> users;
    private GameList gameList;
    private Map<String, User> userAuthTokens; //TODO: Tom: is this how it was done in 240? //This will work for storage in memory, but we'll want a DB later on
    private CommandList commandList; // holds all commands executed on the server to this point
    private User tempUser;

    private static ServerModel serverModel = new ServerModel();

    private ServerModel()
    {
        users = new ArrayList<>();
        userAuthTokens = new HashMap<>();
        gameList = new GameList(new ArrayList<>());
    }

    /**
     * Return the singleton instance of this class
     * @return
     */
    public static ServerModel getInstance()
    {
        return serverModel;
    }

    /**
     * Checks to see if a user is already registered
     * @param user
     * @return
     */
    private boolean doesUserExist(User user)
    {
        for(User user1 : users)
        {
            if(user1.getUsername().equals(user.getUsername()))
            {
                return true;
            }
        }
        return false;
        //return users.contains(user);
    }

    /** Takes an authToken and finds which user it belongs to, then returns that user
     *
     * @param authToken The authToken supplied to the server
     * @return the User to which the authToken is related, or null if no valid authToken
     */
    public User validateUser(String authToken)
    {
        boolean tokenExists = userAuthTokens.containsKey(authToken);
        if(tokenExists)
        {
            return userAuthTokens.get(authToken);
        }
        else return null;
//        if(doesUserExist(user))
//        {
//            String trueAuthToken = userAuthTokens.get(user);
//            if(trueAuthToken.compareTo(authToken) == 0) // authTokens match
//            {
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }
//        else // user does not exist
//        {
//            return false;
//        }
    }

    /**
     * Adds the user to DB and then calls loginUser(User) to log
     * him in and return an authToken
     * @param user
     * @return
     */
    public String registerUser(User user)
    {
        if(doesUserExist(user))
        {
            return null;
        }
        users.add(user);
        return loginUser(user);
    }

    /**
     * Creates and returns an authToken for the supplied user
     * @param user
     * @return
     */
    public String loginUser(User user)
    {
        if(!doesUserExist(user))
        {
            return null;
        }
        String authToken = generateToken();
        userAuthTokens.put(authToken, user);
        return authToken;
    }

    /**
     * Generates an authorization token
     * @return
     */
    public String generateToken()
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }

    /**
     * Adds a game to the game list on the server
     * @param game
     */
    public void addGame(Game game)
    {
        int gameId = gameList.getGameList().size() + 1;
        game.setGameId(gameId);
        gameList.getGameList().add(game);
    }

    /**
     * Gets the current list of games on the server
     * @return
     */
    public GameList getGameList()
    {
        return gameList;
    }

    /**
     * Gets the list of commands that were executed after the index in commandID
     * @param commandID
     */
    public CommandList getCommandsSinceIndex(int commandID)
    {
        CommandList returnList = new CommandList();
        // need to have at least one new command to sublist, if not, just return  the empty list
        // we are returning all the commands after commandID, thus it is commandID + 1
        if(commandID + 1 < commandList.commandList.size() - 1)
        {
            returnList.commandList = commandList.commandList.subList(commandID + 1, commandList.commandList.size());
        }
        return returnList;
    }

    public User getTempUser()
    {
        return tempUser;
    }

    public void setTempUser(User tempUser)
    {
        this.tempUser = tempUser;
    }

    public void deleteTempUser()
    {
        this.tempUser = null;
    }
}
