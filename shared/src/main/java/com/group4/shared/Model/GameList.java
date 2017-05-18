package com.group4.shared.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class GameList {

    private List<Game> gameList;

    public GameList(List<Game> gameList) {

        this.gameList = gameList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    // Finds the game based on the position in the GameList
    public Game getGame(int position){
        return gameList.get(position);
    }

    // Overrides the game based on the position of the Game given
    public void setGame(Game game, int position){
        gameList.set(position, game);
    }

    public int findGame(int gameId){
        for(int i = 0; i < gameList.size(); i++){
            if(gameList.get(i).getGameId() == gameId){
                return i;
            }
        }

        return 0;
    }

    public Game getGameByUsername(String username)
    {
        for (Game game : gameList)
        {
            if (game.getPlayers().containsKey(username))
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
