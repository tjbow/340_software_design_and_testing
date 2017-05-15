package com.group4.shared.command.Client;

import com.group4.shared.Model.GameList;
import com.group4.shared.command.ClientCommand;

import java.util.List;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CGetGameListCommandData  extends ClientCommand{
    private GameList gameList;

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
    }
}
