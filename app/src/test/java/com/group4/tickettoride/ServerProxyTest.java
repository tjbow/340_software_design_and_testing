package com.group4.tickettoride;

import com.group4.shared.Model.Player;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxyNoAsync;
import com.group4.tickettoride.Network.ServerProxyNoAsync;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

        //call register on the ServerProxyNoAsync
        mResults = ServerProxyNoAsync.SINGLETON.register(user);

        assertTrue(mResults.isSuccess());
        assertNotNull(ClientModel.SINGLETON.getAuthToken());
    }

    @Test
    public void testLogin()
    {
        //create a user to be logged in
        User user = new User("tyler", "pwd");

        //call login on the ServerProxyNoAsync
        mResults = ServerProxyNoAsync.SINGLETON.login(user);

        assertTrue(mResults.isSuccess());
        assertNotNull(ClientModel.SINGLETON.getAuthToken());
    }

    @Test
    public void testCreateGame()
    {
        String gameName = "TestGame";
        int numberOfPlayers = 4;

        mResults = ServerProxyNoAsync.SINGLETON.createGame(gameName, numberOfPlayers);
    }

    @Test
    public void testJoinGame()
    {
        int gameId = 1;
        User user = new User("tyler", "pwd");
        Player player = new Player(user);

        mResults = ServerProxyNoAsync.SINGLETON.joinGame(gameId);
    }
}
