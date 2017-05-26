package com.group4.server.ServerModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.google.gson.Gson;
import com.group4.shared.Model.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private Map<String, User> userAuthTokens;
    private User tempUser;

    private List<City> cities;

    private static ServerModel serverModel = new ServerModel();

    private ServerModel()
    {
        users = new ArrayList<>();
        userAuthTokens = new HashMap<>();
        gameList = new GameList(new ArrayList<>());

        cities = getCities();
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
     * @return A boolean indicating if the user is registered
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
    private String generateToken()
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
    protected boolean createGame(Game game)
    {
        if(gameList.getGameByName(game.getGameName()) != null)
        {
            return false;
        }
        gameList.add(game);

        System.out.println("Game \"" + game.getGameName() + "\" created by " + getTempUser().getUsername() + " with " + game.getPlayerCount() + " players.");

        Player player = new Player(getTempUser());

        joinGame(game.getGameName(), player);

        return true;
    }

    protected boolean joinGame(String gameName, Player player)
    {
        Game game = gameList.getGameByName(gameName);

        if(game.getCurrentPlayerSize() >= game.getPlayerCount())
        {
            //don't join
            System.out.println("Player " + player.getUserName() + " attempted to join the game \"" + game.getGameName()
                    + ",\" but it was full.");
            return false;
        }
        int currentPlayerCount = game.getCurrentPlayerSize();
        switch (currentPlayerCount)
        {
            case 0:
                player.setColor("blue");
                break;
            case 1:
                player.setColor("red");
                break;
            case 2:
                player.setColor("green");
                break;
            case 3:
                player.setColor("yellow");
                break;
            case 4:
                player.setColor("black");
                break;
        }

        game.addPlayer(player.getUserName(), player);
        System.out.println("Player \"" + player.getUserName() + "\" joined the game " + game.getGameName()
                + " (" + game.getCurrentPlayerSize() + "/" + game.getPlayerCount() + " players).");
        return true;
    }

    public void startGame(String gameName)
    {
        Game game = gameList.getGameByName(gameName);
        game.setStatus(GAME_STATUS.ONGOING);

        for(Map.Entry<String, Player> entry : game.getPlayers().entrySet())
        {
            entry.getValue().initializeGame();
        }

        Player firstPlayer = game.getPlayerList().get(0);
        firstPlayer.setTurn(true);
        game.getGameStats().setPlayerCurrentTurn(firstPlayer.getUserName());

        //initialize all the game and player decks
        game.dealInitialCards();

        System.out.println(gameName + " has been started by " + getTempUser().getUsername());
    }

    public boolean endGame(String gameName)
    {
        //TODO: TYLER: adjust ServerModel endGame
        Game game = gameList.getGameByName(gameName);
        return gameList.remove(game);
    }

    /**
     * Gets the current list of games on the server
     * @return
     */
    public GameList getGameList()
    {
        return gameList;
    }

    private List<City> getCities()
    {
        Gson gson = new Gson();
        ArrayList<City> cities = null;
        try
        {
            cities = (ArrayList<City>) gson.fromJson(new FileReader("cities.json"), ArrayList.class);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return cities;
    }

    //    AUTHTOKEN METHODS
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
