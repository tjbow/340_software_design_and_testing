package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Decks;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.GameStats;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.Model.User;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

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
    private ScheduledExecutorService execService;

    public static ClientModel SINGLETON = new ClientModel();

    private ClientModel() {}

    public ClientModel(User user, GameList gameList) {
        this.user = user;
        this.gameList = gameList;
        this.commandIDIndex = 0;
    }

    public void destructor()
    {
        this.user = null;
        this.gameList = null;
        this.authToken = null;
        this.commandIDIndex = 0;
        this.player = null;
        this.game = null;
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

        Map<String, Player> players = game.getPlayers();
        for(Map.Entry<String, Player> entry : players.entrySet())
        {
            if(entry.getKey().equals(userName))
            {
                this.player = entry.getValue();
                return;
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ScheduledExecutorService getExecService() {
        return execService;
    }

    public void setExecService(ScheduledExecutorService execService) {
        this.execService = execService;
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

    public void updatePlayerInfo(List<Player> players){
        // TODO: Russell implement Player interation to update Map in game
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
     * Runs the commands in the commandList on the client model
     *
     * @param commandList list of new commands to run
     */
    //public void runCommands(CommandList commandList)
    //{
    //for(Command command : commandList.commandList)
    //    {
    //        ((IClientCommand) command).execute();
    //    }
    //}

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
