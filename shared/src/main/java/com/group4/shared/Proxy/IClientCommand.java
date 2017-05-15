package com.group4.shared.Proxy;

import com.group4.shared.Model.Results;


/**
 * Created by abgill on 5/15/2017.
 */

public interface IClientCommand {
    Results onJoinGame();
    Results onCreateGame();
    Results onLogin();
    Results onRegister();
    Results onStartGame();
}
