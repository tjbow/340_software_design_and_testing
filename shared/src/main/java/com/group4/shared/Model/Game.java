package com.group4.shared.Model;

import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Game
{

    private Map<String, Player> players;
    private int playerCount;
    private String gameName;
    private GAME_STATUS status;

    private List<Message> turnHistory;
    private List<Message> chatHistory;
    private CommandList commandList;

    private String playerCurrentTurn;
    private String playerLongestPath;
    private String playerWinning;

    public Game(String gameName, int playerCount)
    {
        this.gameName = gameName;
        this.playerCount = playerCount;
        this.players = new HashMap<>();
        this.status = GAME_STATUS.WAITING;

        this.chatHistory = new ArrayList<>();
        this.turnHistory = new ArrayList<>();
        this.commandList = new CommandList();
    }

//    PLAYERS
    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public void addPlayer(String username, Player player){
        players.put(username, player);
    }

    public int getCurrentPlayerSize(){
        return players.size();
    }


//    GAME SETUP
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public GAME_STATUS getStatus()
    {
        return status;
    }

    public void setStatus(GAME_STATUS status)
    {
        this.status = status;
    }

//    GAMEPLAY

//    TURN HISTORY
    public List<Message> getTurnHistory()
    {
        return turnHistory;
    }

    public void setTurnHistory(List<Message> turnHistory)
    {
        this.turnHistory = turnHistory;
    }

    public boolean addTurn(Message turn)
    {
        return turnHistory.add(turn);
    }

//    CHAT HISTORY
    public List<Message> getChatHistory()
    {
        return chatHistory;
    }

    public void setChatHistory(List<Message> chatHistory)
    {
        this.chatHistory = chatHistory;
    }

    public boolean addMessage(Message message)
    {
        return chatHistory.add(message);
    }

//    COMMAND LIST
    public CommandList getCommandList()
    {
        return commandList;
    }

    public void setCommandList(CommandList commandList)
    {
        this.commandList = commandList;
    }

    public boolean addCommand(ClientCommand command)
    {
        return commandList.add(command);
    }


// Player Highlights
    public String getPlayerCurrentTurn() {
        return playerCurrentTurn;
    }

    public void setPlayerCurrentTurn(String playerCurrentTurn) {
        this.playerCurrentTurn = playerCurrentTurn;
    }

    public String getPlayerLongestPath() {
        return playerLongestPath;
    }

    public void setPlayerLongestPath(String playerLongestPath) {
        this.playerLongestPath = playerLongestPath;
    }

    public String getPlayerWinning() {
        return playerWinning;
    }

    public void setPlayerWinning(String playerWinning) {
        this.playerWinning = playerWinning;
    }

    /*---------------Deprecated-------------------------------*/
    @Deprecated
    private int gameId;
    @Deprecated
    private String gameID;

    @Deprecated
    public int getGameId() {
        return gameId;
    }

    @Deprecated
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Deprecated
    public String getGameID() {
        return gameID;
    }

    @Deprecated
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    @Deprecated
    public Game(Map<String, Player> players, int gameId, String gameName, int playerCount) {
        this.players = players;
        this.gameId = gameId;
        this.gameName = gameName;
        this.playerCount = playerCount;
    }
}
