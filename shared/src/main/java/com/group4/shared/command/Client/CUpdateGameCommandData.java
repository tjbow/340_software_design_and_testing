package com.group4.shared.command.Client;

import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.command.ClientCommand;

import java.util.List;
import java.util.Map;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CUpdateGameCommandData extends ClientCommand
{
    private Game game;
    private GAME_STATUS status;

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public GAME_STATUS getStatus()
    {
        return status;
    }

    public void setStatus(GAME_STATUS status)
    {
        this.status = status;
    }
}
