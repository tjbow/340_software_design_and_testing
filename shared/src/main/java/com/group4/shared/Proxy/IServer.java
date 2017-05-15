package com.group4.shared.Proxy;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public interface IServer {

    public Results login(User user);
    public Results register(User user);
    public Results createGame();
    public Results joinGame(int gameId);
    public Results startGame(int gameId);
    //Todo: public CommandList getCommandsSinceIndex(int commandID);

}
