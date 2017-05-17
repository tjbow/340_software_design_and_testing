package com.group4.shared.Proxy;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;

import java.util.List;

/**
 * Created by Andrew Gill on 5/14/2017.
 */

public interface IClient {

    /**
     * Return the current game state
     */
    Results reportGameState();
}
