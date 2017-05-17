package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;
    private int commandIDIndex; // last command run
    private String authToken;
    private Player player;

    public static ClientModel SINGLETON = new ClientModel();

    private ClientModel() {}

    public ClientModel(User user, GameList gameList) {
        this.user = user;
        this.gameList = gameList;
        this.commandIDIndex = 0;
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
        setChanged();
        notifyObservers(gameList);
        clearChanged();
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
        this.player = new Player(UUID.randomUUID(), user);
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
        this.commandIDIndex = commandIDIndex;
    }

    public void sendToObservers(Object arg)
    {
        setChanged();
        notifyObservers(arg);
        clearChanged();
    }
}
