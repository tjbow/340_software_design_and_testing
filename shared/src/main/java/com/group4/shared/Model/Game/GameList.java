package com.group4.shared.Model.Game;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class GameList {

    private List<Game> gameList;

    public GameList(List<Game> gameList)
    {
        this.gameList = gameList;
    }

    public boolean add(Game game)
    {
        return gameList.add(game);
    }

    public boolean remove(Game game)
    {
        return gameList.remove(game);
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Game getGameByUsername(String username)
    {
        for (Game game : gameList)
        {
//            if (game.getPlayers().containsKey(username))
//            {
//                return game;
//            }
            if(game.getPlayerByUserName(username) != null)
            {
                return game;
            }
        }
        return null;
    }

    public Game getGameByName(String gameName)
    {
        for(Game game : gameList)
        {
            if(game.getGameName().equals(gameName))
            {
                return game;
            }
        }
        return null;
    }
}
