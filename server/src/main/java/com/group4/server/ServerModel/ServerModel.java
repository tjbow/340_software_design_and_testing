package com.group4.server.ServerModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static ServerModel serverModel = new ServerModel();

    private ServerModel()
    {
        users = new ArrayList<>();
        userAuthTokens = new HashMap<>();
        gameList = new GameList(new ArrayList<>());
    }

    /**
     * Return the singleton instance of this class
     * @return this
     */
    public static ServerModel getInstance()
    {
        return serverModel;
    }


//    -------REGISTER/LOGIN/AUTHENTICATION METHODS------------------------------------
    /**
     * Checks to see if a user is already registered
     * @param user the user to be found
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
     * @param user the user to be registered
     * @return login(user), which is an authtoken
     */
    String registerUser(User user)
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
     * @param user the user to be logged in
     * @return new authToken
     */
    String loginUser(User user)
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
     * @return the authToken as a String
     */
    private String generateToken()
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return Arrays.toString(bytes);
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
//    --------END AUTHENTICATION METHODS------------------------------------------------


//    --------GAME PREP METHODS---------------------------------------------------------
    /** Adds a game to the game list on the server
     *
     * @param gameName the name of the game to be created
     * @param numberOfPlayers the number of players the game needs to start
     * @return boolean whether or not the game was created successfully
     */
    boolean createGame(String gameName, int numberOfPlayers)
    {
        Game game = new Game(gameName, numberOfPlayers);
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

    boolean joinGame(String gameName, Player player)
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
                player.setColor(Player.BLUE);
                player.setTurn(true);
                player.setCurrentState(MOVE_STATE.MY_TURN);
                break;
            case 1:
                player.setColor(Player.RED);
                player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
                break;
            case 2:
                player.setColor(Player.GREEN);
                player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
                break;
            case 3:
                player.setColor(Player.YELLOW);
                player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
                break;
            case 4:
                player.setColor(Player.BLACK);
                player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
                break;
        }

        game.addPlayer(player);

        System.out.println("Player \"" + player.getUserName() + "\" joined the game " + game.getGameName()
                + " (" + game.getCurrentPlayerSize() + "/" + game.getPlayerCount() + " players).");
        return true;
    }

    void startGame(String gameName)
    {
        Game game = gameList.getGameByName(gameName);
        game.setStatus(GAME_STATUS.ONGOING);

        game.getPlayers().forEach(Player::initializeGame);

        //initialize all the game and player decks
        game.dealInitialGameCards();
        game.importRoutes();
        game.importCities();

        System.out.println(gameName + " has been started by " + getTempUser().getUsername());
    }

    boolean endGame(String gameName)
    {
        Game game = gameList.getGameByName(gameName);
        if(game != null)
            game.setStatus(GAME_STATUS.FINISHED);
        else
            return false;
        return true;
    }

    /**
     * Gets the current list of games on the server
     * @return this.gameList
     */
    GameList getGameList()
    {
        return gameList;
    }

//    ----END GAME PREP METHODS---------------------------------------------------------

//    private List<City> getCities()
//    {
//        Gson gson = new Gson();
//        ArrayList<City> cities = null;
//        try
//        {
//            cities = (ArrayList<City>) gson.fromJson(new FileReader("cities.json"), ArrayList.class);
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//
//        return cities;
//    }
}
