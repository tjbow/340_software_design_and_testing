package com.group4.shared.command.Client;

import com.group4.shared.Model.Game;
import com.group4.shared.command.ClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CGetGameStateCommandData extends ClientCommand {
    private Game gameState;

    public Game getGameState() {
        return gameState;
    }

    public void setGameState(Game gameState) {
        this.gameState = gameState;
    }
}
