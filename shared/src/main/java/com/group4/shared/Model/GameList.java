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

    public Game findGame(int id){
        return gameList.get(id);
    }
}
