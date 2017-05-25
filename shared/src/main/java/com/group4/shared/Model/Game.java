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

    private TurnHistory turnHistory;
    private ChatHistory chatHistory;
    private Decks decks;
    private CommandList commandList;

    private RouteList routes;
    private List<City> cities;

    private GameStats gameStats;

    public Game(String gameName, int playerCount)
    {
        this.gameName = gameName;
        this.playerCount = playerCount;
        this.players = new HashMap<>();
        this.status = GAME_STATUS.WAITING;

        turnHistory = new TurnHistory();
        chatHistory = new ChatHistory();
        decks = new Decks();
        commandList = new CommandList();

        routes = new RouteList();
        cities = new ArrayList<>();

        //initialize the first player as player at pos 0
        gameStats = new GameStats();
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

    public List<Player> getPlayerList()
    {
        List<Player> playerList = new ArrayList<>();
        for (String key : players.keySet())
        {
            playerList.add(players.get(key));
        }

        return playerList;
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

    public Decks getDecks() {
        return decks;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }

    public RouteList getRoutes() {
        return routes;
    }

    public void setRoutes(RouteList routes) {
        this.routes = routes;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

//    GAMEPLAY

    public void dealInitialCards()
    {
        //init the game decks with all the cards
        decks.startGameDeck();

        //give four train cards to each player

        //give four destination cards to each player (0 or 1 to be returned)
    }

//    TURN HISTORY
    public TurnHistory getTurnHistory()
    {
        return turnHistory;
    }

    public void setTurnHistory(TurnHistory turnHistory)
    {
        this.turnHistory = turnHistory;
    }

    public void addTurn(Message turn)
    {
        turnHistory.add(turn);
    }

    public void moveToNextPlayer()
    {
        String currentPlayer = gameStats.getPlayerCurrentTurn();
        List<Player> list = getPlayerList();
        String getNewTurnPlayer = null;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getUserName().equals(currentPlayer))
            {
                if(i == list.size() - 1)
                {
                    getNewTurnPlayer = list.get(0).getUserName();
                    gameStats.setPlayerCurrentTurn(getNewTurnPlayer);
                    return;
                }
                else
                {
                    getNewTurnPlayer = list.get(i + 1).getUserName();
                    gameStats.setPlayerCurrentTurn(getNewTurnPlayer);
                    return;
                }
            }
        }
        gameStats.setPlayerCurrentTurn(null);
    }

//    CHAT HISTORY
    public ChatHistory getChatHistory()
    {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory)
    {
        this.chatHistory = chatHistory;
    }

    public void addMessage(Message message)
    {
        chatHistory.add(message);
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

    public int getNewCommandIndex()
    {
        int index = commandList.size();
        System.out.println("index is: " + index);
        return index;
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
