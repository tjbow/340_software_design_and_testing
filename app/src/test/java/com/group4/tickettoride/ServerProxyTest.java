package com.group4.tickettoride;

import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ServerProxy.ServerProxy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tyler on 5/15/17.
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

        //call register on the ServerProxy
        mResults = ServerProxy.SINGLETON.register(user);
    }

    @Test
    public void testLogin()
    {
        //create a user to be logged in
        User user = new User("tyler", "pwd");

        //call login on the ServerProxy
        mResults = ServerProxy.SINGLETON.login(user);
    }

    @Test
    public void testCreateGame()
    {
        String gameName = "TestGame";
        int numberOfPlayers = 4;

        mResults = ServerProxy.SINGLETON.createGame(gameName, numberOfPlayers);
    }

    @Test
    public void testJoinGame()
    {
        int gameId = 1;
        User user = new User("tyler", "pwd");
        Player player = new Player(user);

        mResults = ServerProxy.SINGLETON.joinGame(gameId);
    }
}
