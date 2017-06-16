package com.group4.shared.plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;

/**
 * Created by Russell Fitzpatrick on 6/14/2017.
 */

public interface IGameDao {

    public void saveGame(Game game);
    public GameList getGames();
    public void clear();
    public void deleteGame(Game game);

}
