package com.group4.shared.Model.Game;

import java.io.Serializable;

/**
 * Created by Russell Fitzpatrick on 5/24/2017.
 */

public class GameStats implements Serializable
{

    private String playerCurrentTurn;
    private String playerLongestPath;
    private String playerWinning;

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
}
