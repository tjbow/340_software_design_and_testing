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
    //TODO @john: added this for gameName support
    public int getGameIndexByName(String gameName)
    {
        for(int i = 0; i < gameList.size(); i++)
        {
            if(gameList.get(i).getGameName().equals(gameName))
            {
                return i;
            }
        }
        return -1;
    }
    //TODO @john: modified this for gameName support
    public void addPlayer(String username, Player player, String gameName){
        Game game = getGameByName(gameName);
        game.addPlayer(username, player);
        setGame(game, getGameIndexByName(gameName) );
    }
}
