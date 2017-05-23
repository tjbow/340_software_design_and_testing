package com.group4.tickettoride.Network;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Server.CreateGameCommandData;
import com.group4.shared.command.Server.GetGameListCommandData;
import com.group4.shared.command.Server.JoinGameCommandData;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.shared.command.Server.RegisterCommandData;

/**
 * Created by tyler on 5/16/17.
 */

public class ServerProxyNoAsync
{
    private ServerProxyNoAsync()
    {
    }

    public static ServerProxyNoAsync SINGLETON = new ServerProxyNoAsync();

    /**
     * @param user The user to be logged in
     * @return If success: returns null. If failure: returns result object with failure message
     * <p>
     * When calling this method, check if the result is null. If not null, display an error toast.
     * @pre The user must already be registered with the server
     */
    public Results login(User user)
    {
        LoginCommandData cmd = new LoginCommandData();
        cmd.setType("login");
        cmd.setUser(user);

        threadIt(cmd);

        return null;
    }

    /**
     * @param user The user to be registered
     * @return If success: returns null. If failure: returns result object with failure message
     * <p>
     * When calling this method, check if the result is null. If not null, display an error toast.
     * @pre The username must not already be registered on the server
     */
    public Results register(User user)
    {
        RegisterCommandData cmd = new RegisterCommandData();
        cmd.setType("register");
        cmd.setUser(user);

        threadIt(cmd);

        return null;
    }

    /**
     * Creates a new game on the server.
     *
     * @param gameName        The name for the game
     * @param numberOfPlayers The max number of players for the game
     * @return If success: returns null. If failure: returns result object with failure message
     */
    public Results createGame(String gameName, int numberOfPlayers)
    {
        CreateGameCommandData cmd = new CreateGameCommandData();
        cmd.setType("creategame");
        cmd.setGameName(gameName);
        cmd.setNumberOfPlayers(numberOfPlayers);

        threadIt(cmd);

        return null;
    }

    public Results joinGame(String gameName)
    {
        JoinGameCommandData cmd = new JoinGameCommandData();
        cmd.setType("joingame");
        cmd.setGameName(gameName);

        threadIt(cmd);

        return null;
    }

    public Results startGame(String gameName)
    {
        return null;
    }

    public Results getGameList()
    {
        GetGameListCommandData cmd = new GetGameListCommandData();
        cmd.setType("getgamelist");
        threadIt(cmd);
        return null;
    }

    public Results endGame(String gameName)
    {
        return null;
    }

    public Results getCommandsSinceIndex(int commandID)
    {
        return null;
    }

    private void threadIt(Object o)
    {
        new ClientCommunicator().send("execcommand", o);
    }
}
