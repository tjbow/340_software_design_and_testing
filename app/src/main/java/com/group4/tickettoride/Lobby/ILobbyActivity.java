package com.group4.tickettoride.Lobby;

import com.group4.shared.Model.Player;

import java.util.List;

/**
 * Created by abgill on 5/16/2017.
 */

public interface ILobbyActivity {
    void setPlayerList(List<Player> playerList);
    void setStartButtonEnabled(boolean isEnabled);
    void setMaxPlayers(int maxPlayers);
    void setCurrentPlayers(int currentPlayers);
    void setGameName(String gameName);
}
