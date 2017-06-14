package com.group4.tickettoride;

import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tyler on 5/15/17.
 *
 * ****DEPRECATED*****
 */

public class ServerProxyTest
{
    Results mResults;

    @Before
    public void setUp()
    {
        mResults = null;
    }

    @After
    public void cleanUp()
    {
        if(mResults != null)
        {
            System.out.println("Error: " + mResults.getErrorInfo());
        }
        else
        {
            System.out.println("Success");
        }
    }

    @Test
    public void testRegister()
    {
        //create a user to be registered
        User user = new User("tyler", "pwd");

        //call register on the ServerProxyNoAsync
        ServerProxy.SINGLETON.register(user);

    }

    @Test
    public void testLogin()
    {
        //create a user to be logged in
        User user = new User("tyler", "pwd");

        //call login on the ServerProxyNoAsync
        ServerProxy.SINGLETON.login(user);

    }

    @Test
    public void testCreateGame()
    {
        String gameName = "TestGame";
        int numberOfPlayers = 1;
        ServerProxy.SINGLETON.createGame(gameName, numberOfPlayers);
    }

    @Test
    public void testJoinGame()
    {
        String gameName = "testgame123";
        User user = new User("tyler", "pwd");
        Player player = new Player(user);

        ServerProxy.SINGLETON.joinGame(gameName);
    }

    @Test
    public void testSendChat()
    {
        User user = new User("tyler", "pwd");
        ServerProxy.SINGLETON.register(user);

        Message message = new Message("This is a message", "tbow", "red");

        ServerProxy.SINGLETON.sendChat(message);

    }

    @Test
    public void testGetPendingCommands()
    {
        User user = new User("tyler", "pwd");
        ServerProxy.SINGLETON.login(user);

        ServerProxy.SINGLETON.getPendingCommands(user, 0);
    }

    @Test
    public void testCommandSequence()
    {
        //tyler registers
        User user = new User("tyler", "pwd");
        ServerProxy.SINGLETON.register(user);

        //tyler creates game
        String gameName = "TestGame";
        int numberOfPlayers = 2;
        ServerProxy.SINGLETON.createGame(gameName, numberOfPlayers);

        //john registers
        User user1 = new User("john", "pwd");
        ServerProxy.SINGLETON.register(user1);

        //john joins game
        Player player = new Player(user1);
        ServerProxy.SINGLETON.joinGame(gameName);

        //john starts game
        ServerProxy.SINGLETON.startGame(gameName);

        //tyler login
        ServerProxy.SINGLETON.login(user);

        //tyler sendChat
        Message message = new Message("This is a message", "tyler", "red");
        ServerProxy.SINGLETON.sendChat(message);

        //get the game list
        ServerProxy.SINGLETON.getGameList();

        //print the message
//        System.out.println(ClientModel.SINGLETON.getGame().getChatHistory().get(0).getMessage());
    }
}
