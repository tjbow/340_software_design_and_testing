package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.City;
import com.group4.shared.Model.Decks;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.GameStats;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.Model.User;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;
    private int commandIDIndex; // last command run
    private String authToken;
    private Player player;
    private Game game;

    public static ClientModel SINGLETON = new ClientModel();

    private ClientModel() {}

    public ClientModel(User user, GameList gameList) {
        this.user = user;
        this.gameList = gameList;
        this.commandIDIndex = 0;
    }

    public void clear()
    {
        user = null;
        gameList = null;
        authToken = null;
        player = null;
        game = null;
        commandIDIndex = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
        if(user != null)
        {
            this.game = this.gameList.getGameByUsername(user.getUsername());
        }
        sendToObservers(gameList);
    }


    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer() {
        String userName = user.getUsername();

        this.player = game.getPlayerByUserName(userName);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setGameIfUserIsPlaying()
    {
        if (gameList.getGameByUsername(user.getUsername()) != null)
        {
            this.game = gameList.getGameByUsername(user.getUsername());
            setPlayer();
        }
        else
        {
            this.game = null;
        }
    }

    public void setChatHistory(ChatHistory messageList){
        game.setChatHistory(messageList);
        sendToObservers(messageList);
    }

    public void setTurnHistory(TurnHistory turnHistory){
        game.setTurnHistory(turnHistory);
        sendToObservers(turnHistory);
    }

    public void setDecks(Decks decks){
        game.setDecks(decks);
        sendToObservers(decks);
    }

    public void updateMap(RouteList routeList, List<City> cities){
        game.setRoutes(routeList);
        sendToObservers(routeList);
        game.setCities(cities);
        sendToObservers(cities);
    }

    public void updateStats(GameStats stats){
        game.setGameStats(stats);
        sendToObservers(stats);
    }

    public void updatePlayerInfo(List<Player> players)
    {
//        Map<String, Player> playerMap = new HashMap<>();
//        for(Player player : players)
//        {
//            playerMap.put(player.getUserName(), player);
//
//            //also update the model player
//            if(user.getUsername().equals(player.getUserName()))
//            {
//                this.player = player;
//            }
//        }
        game.setPlayers(players);

        //also update the model player
        player = game.getPlayerByUserName(user.getUsername());

        sendToObservers(game.getPlayers());
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    /**
     * Get the current command index for the model
     * @return the current command index
     */
    public int getCommandIDIndex()
    {
        return commandIDIndex;
    }

    /**
     * Sets the command list index, the index of the last command run
     * @param commandIDIndex the index of the last command run
     */
    public void setCommandIDIndex(int commandIDIndex)
    {
        if(this.commandIDIndex < commandIDIndex)
        {
            this.commandIDIndex = commandIDIndex;
        }
    }

    public void sendToObservers(Object arg)
    {
        setChanged();
        notifyObservers(arg);
        clearChanged();
    }
}
