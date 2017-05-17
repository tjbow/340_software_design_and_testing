package com.group4.shared.Model;

import java.util.List;

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

    public void addPlayer(String username, Player player, int position){
        Game game = gameList.get(position);
        game.addPlayer(username, player);
        setGame(game, position);
    }
}
