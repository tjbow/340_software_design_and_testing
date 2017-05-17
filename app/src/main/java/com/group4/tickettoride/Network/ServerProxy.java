package com.group4.tickettoride.Network;

import android.os.AsyncTask;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Server.CreateGameCommandData;
import com.group4.shared.command.Server.GetGameListCommandData;
import com.group4.shared.command.Server.JoinGameCommandData;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.shared.command.Server.RegisterCommandData;
import com.group4.tickettoride.ClientModel.ClientModel;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 *
 * The proxy methods within will return a Results object to their caller *IF*
 * there is an error message.  Otherwise, they will return null as the ClientFacade will
 * handle the Results object.
 *
 */

public class ServerProxy implements IServer
{
    private ServerProxy(){}

    public static ServerProxy SINGLETON = new ServerProxy();

    /**
     * @pre The user must already be registered with the server
     *
     * @param user The user to be logged in
     * @return If success: returns null. If failure: returns result object with failure message
     *
     * When calling this method, check if the result is null. If not null, display an error toast.
     *
     */
    @Override
    public Results login(User user)
    {
        LoginCommandData cmd = new LoginCommandData();
        cmd.setType("login");
        cmd.setUser(user);

        threadIt(cmd);

        return null;
    }

    /**
     * @pre The username must not already be registered on the server
     *
     * @param user The user to be registered
     * @return If success: returns null. If failure: returns result object with failure message
     *
     * When calling this method, check if the result is null. If not null, display an error toast.
     *
     */
    @Override
    public Results register(User user)
    {
        RegisterCommandData cmd = new RegisterCommandData();
        cmd.setType("register");
        cmd.setUser(user);

        threadIt(cmd);

        return null;
    }

    /** Creates a new game on the server.
     *
     * @param gameName The name for the game
     * @param numberOfPlayers The max number of players for the game
     * @return If success: returns null. If failure: returns result object with failure message
     *
     *
     */
    @Override
    public Results createGame(String gameName, int numberOfPlayers)
    {
        CreateGameCommandData cmd = new CreateGameCommandData();
        cmd.setType("creategame");
        cmd.setGameName(gameName);
        cmd.setNumberOfPlayers(numberOfPlayers);

        threadIt(cmd);

        return null;
    }

    @Override
    public Results joinGame(String gameName)
    {
        JoinGameCommandData cmd = new JoinGameCommandData();
        cmd.setType("joingame");
        cmd.setGameName(gameName);

        threadIt(cmd);

        return null;
    }

    @Override
    public Results startGame(int gameId)
    {
        return null;
    }

    @Override
    public Results getGameList()
    {
        GetGameListCommandData cmd = new GetGameListCommandData();
        cmd.setType("getgamelist");
        threadIt(cmd);
        return null;
    }

    @Override
    public Results getCommandsSinceIndex(int commandID)
    {
        return null;
    }

    private void threadIt(Object o)
    {
        NetworkTask task = new NetworkTask();
        task.execute(o);
    }

    public class NetworkTask extends AsyncTask<Object, Void, Void>
    {
        private Results mResults;

        @Override
        protected Void doInBackground(Object... objects)
        {
            for(Object o : objects)
            {
                mResults = new ClientCommunicator().send("execcommand", o);
            }
            if(!mResults.isSuccess())
            {
                //TYLER: Get this error toast working
//                Login_RegisterActivity activity = new Login_RegisterActivity();
//                activity.displayError(mResults.getErrorInfo());
                ClientModel.SINGLETON.sendToObservers(mResults.getErrorInfo());

            }
            else
            {
                //do nothing - ClientFacade is handling results
            }
            return null;
        }
    }
}
