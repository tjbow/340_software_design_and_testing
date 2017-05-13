package com.group4.shared.Model;


import java.util.Map;

public class Game {

    private Map<String, Player> players;
    private int playerCount;


    public Game(){}

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
