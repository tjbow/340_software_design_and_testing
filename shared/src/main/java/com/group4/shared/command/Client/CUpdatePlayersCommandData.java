package com.group4.shared.command.Client;

import com.group4.shared.Model.Player;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdatePlayersCommandData extends ClientCommand
{
    List<Player> playerData;

    public List<Player> getPlayerData()
    {
        return playerData;
    }

    public void setPlayerData(List<Player> playerData)
    {
        this.playerData = playerData;
    }
}
