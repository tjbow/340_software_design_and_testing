package com.group4.shared.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Game {

    private Map<String, Player> players;
    private int gameId;
    private int playerCount;
    private String gameName;
    private String gameID;

    public Game(Map<String, Player> players, int gameId, String gameName, int playerCount) {
        this.players = players;
        this.gameId = gameId;
        this.gameName = gameName;
        this.playerCount = playerCount;
    }

    public Game(String gameName, int playerCount)
    {
        this.gameName = gameName;
        this.playerCount = playerCount;
        this.players = new HashMap<>();
    }

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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
