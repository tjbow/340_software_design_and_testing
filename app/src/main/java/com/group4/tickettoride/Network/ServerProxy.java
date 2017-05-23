package com.group4.tickettoride.Network;

import android.os.AsyncTask;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.Server.CreateGameCommandData;
import com.group4.shared.command.Server.DrawDestCardsCommandData;
import com.group4.shared.command.Server.EndGameCommandData;
import com.group4.shared.command.Server.GetGameListCommandData;
import com.group4.shared.command.Server.GetPendingCommandsData;
import com.group4.shared.command.Server.JoinGameCommandData;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.shared.command.Server.RegisterCommandData;
import com.group4.shared.command.Server.ReturnDestCardCommandData;
import com.group4.shared.command.Server.SendChatCommandData;
import com.group4.shared.command.Server.StartGameCommandData;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.List;

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
    public Results startGame(String gameName)
    {
        StartGameCommandData cmd = new StartGameCommandData();
        cmd.setType("startgame");
        cmd.setGameName(gameName);

        threadIt(cmd);

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
    public Results endGame(String gameName)
    {
        EndGameCommandData cmd = new EndGameCommandData();
        cmd.setType("endgame");
        cmd.setGameName(gameName);
        threadIt(cmd);
        return null;
    }

    @Override
    public Results sendChat(Message message)
    {
        SendChatCommandData cmd = new SendChatCommandData();
        cmd.setType("sendchat");
        cmd.setMessage(message);
        threadIt(cmd);
        return null;
    }

    @Override
    public Results getPendingCommands(User user, int lastCmdExecuted)
    {
        GetPendingCommandsData cmd = new GetPendingCommandsData();
        cmd.setType("getpending");
        cmd.setUser(user);
        cmd.setLastCmdExecuted(lastCmdExecuted);
        threadIt(cmd);
        return null;
    }

    @Override
    public Results drawDestinationCards(String userName, List<DestinationCard> selectedCards)
    {
        DrawDestCardsCommandData cmd = new DrawDestCardsCommandData();
        cmd.setType("drawdestcards");
        cmd.setUserName(userName);
        cmd.setSelectedCards(selectedCards);
        threadIt(cmd);
        return null;
    }

    @Override
    public Results returnDestinationCard(List<DestinationCard> returnedCard)
    {
        ReturnDestCardCommandData cmd = new ReturnDestCardCommandData();
        cmd.setType("returndestcard");
        cmd.setReturnedCard(returnedCard);
        threadIt(cmd);
        return null;
    }

    private void threadIt(Object o)
    {
        NetworkTask task = new NetworkTask();
        task.execute(o);
    }

//    /** FOR USE IN UNIT TESTING ONLY
//     *
//     * @param o
//     */
//    private void threadIt(Object o)
//    {
//        Results results = new ClientCommunicator().send("execcommand", o);
//        ClientFacade.SINGLETON.processResults(results);
//    }

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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(mResults != null)
            {
                if(!mResults.isSuccess())
                {
                    ClientModel.SINGLETON.sendToObservers(mResults.getErrorInfo());
                }
                ClientFacade.SINGLETON.processResults(mResults);
            }
        }
    }
}
