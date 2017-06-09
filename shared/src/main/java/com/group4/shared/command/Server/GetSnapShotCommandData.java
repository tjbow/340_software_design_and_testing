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

public class GetSnapShotCommandData extends Command
{
    User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
