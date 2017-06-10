package com.group4.shared.command.Server;

import com.group4.shared.Model.Deck.Decks;
import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.command.Command;

import java.util.ArrayList;

/**
 * Created by tyler on 6/9/17.
 */

public class GetSnapshotCommandData extends Command
{
    String userName;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
