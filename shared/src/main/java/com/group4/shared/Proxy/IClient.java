package com.group4.shared.Proxy;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Decks;
import com.group4.shared.Model.GAME_STATUS;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.GameStats;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.RouteList;
import com.group4.shared.Model.TurnHistory;

import java.util.List;
import java.util.Map;


/**
 * Created by abgill on 5/15/2017.
 */

public interface IClient
{
    Results onLogin(String authToken, String username);
    Results onRegister(String authToken, String username);
    Results onJoinGame(String gameName);
    Results onCreateGame();
    Results onGetGameList(GameList gameList);

//    PHASE 2
    Results onStartGame();
    Results onEndGame();

    Results onUpdateChat(ChatHistory chatHistory);
    Results onUpdateTurnHistory(TurnHistory turnHistory);
    Results onUpdateGame(GAME_STATUS status, Decks deckState);
    Results onUpdateGameStats(GameStats gameStats);

    Results onUpdatePlayers(List<Player> playerData);
    Results onUpdateMapData(RouteList routeSegments, List<City> cities);
}
