package com.group4.shared.Proxy;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;


/**
 * Created by abgill on 5/15/2017.
 */

public interface IComandExec {
    Results onJoinGame(String gameName);
    Results onCreateGame();
    Results onLogin(String authToken, String username);
    Results onRegister(String authToken, String username);
    Results onStartGame();
    Results onEndGame();
    Results onGetGameList(GameList gameList);
    Results onReportGameState(Game gameState);
}
