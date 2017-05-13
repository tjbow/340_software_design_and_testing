package com.group4.shared.Model;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class PlayerList {

    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public PlayerList(List<Player> players) {

        this.players = players;
    }
}
