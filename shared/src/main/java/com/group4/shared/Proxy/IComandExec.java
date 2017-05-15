package com.group4.shared.Proxy;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;


/**
 * Created by abgill on 5/15/2017.
 */

public interface IComandExec {
    Results onJoinGame(int gameID);
    Results onCreateGame();
    Results onLogin(String authToken);
    Results onRegister(String authToken);
    Results onStartGame();
    Results onGetGameList(GameList gameList);
    Results onReportGameState(Game gameState);
}
