package com.group4.shared.Proxy;

import com.group4.shared.Model.Game;

import java.util.List;

/**
 * Created by Andrew Gill on 5/14/2017.
 */

public interface IClient {
    Game reportGameState();
    List<String> getGameList();
    //TODO: ALL: review return types of methods
}
