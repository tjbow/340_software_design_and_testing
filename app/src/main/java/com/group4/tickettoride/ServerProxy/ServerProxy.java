package com.group4.tickettoride.ServerProxy;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.shared.command.Server.RegisterCommandData;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ClientCommunicator;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ServerProxy implements IServer {

    private ServerProxy(){}

    public static ServerProxy SINGLETON = new ServerProxy();

    /**
     * @pre The user must already be registered with the server
     *
     * @post
     *
     *
     * @param user The user to be logged in
     * @return NOTE:
     */
    @Override
    public Results login(User user)
    {
        LoginCommandData cmd = new LoginCommandData();
        cmd.setType("login");
        cmd.setUser(user);

        Results results = new ClientCommunicator().send("execcommand", cmd);

        // if the result is a failure, return the result with the error
        // message is returned back to the presenter. Else return null.
        if(!results.isSuccess())
        {
            return results;
        }
        else return null;
    }

    @Override
    public Results register(User user)
    {
        RegisterCommandData cmd = new RegisterCommandData();
        cmd.setType("register");
        cmd.setUser(user);

        Results results = new ClientCommunicator().send("execcommand", cmd);
        return null;
    }

    @Override
    public Results createGame() {
        return null;
    }

    @Override
    public Results joinGame(int gameId) {
        return null;
    }

    @Override
    public Results startGame(int gameId) {
        return null;
    }
}
