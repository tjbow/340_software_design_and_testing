package com.group4.shared.command.Client;

import com.group4.shared.Model.GameStats;
import com.group4.shared.command.ClientCommand;

/**
 * Created by tyler on 5/24/17.
 */

public class CUpdateGameStatsCommandData extends ClientCommand
{
    GameStats gameStats;

    public GameStats getGameStats()
    {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats)
    {
        this.gameStats = gameStats;
    }
}
