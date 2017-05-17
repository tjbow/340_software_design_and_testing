package com.group4.tickettoride;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Command;
import com.group4.shared.command.Server.CreateGameCommandData;
import com.group4.shared.command.Server.JoinGameCommandData;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.shared.command.Server.RegisterCommandData;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Command.CLoginCommand;
import com.group4.tickettoride.Network.ClientCommunicator;

import org.junit.Test;
/**
 * Created by tyler on 5/12/17.
 */

public class ClientCommunicatorTest
{
    /**
     * Username must not be taken for registration to succeed
     *
     */
    @Test
    public void testRegister()
    {
        ClientCommunicator communicator = new ClientCommunicator();

        User user = new User("tyler", "pwd");

        RegisterCommandData cmd = new RegisterCommandData();
        cmd.setType("register");
        cmd.setUser(user);

        Results results = communicator.send("execcommand", cmd);

        System.out.println("Result successful: " + results.isSuccess());
        System.out.println("Data: " + results.getData());
        System.out.println("Error Info: " + results.getErrorInfo());
        System.out.println("AuthToken from model: " + ClientModel.SINGLETON.getAuthToken());
    }

    /**
     * The user must be registered in order to receive an authToken
     *
     */
    @Test
    public void testLogin()
    {
        ClientCommunicator communicator = new ClientCommunicator();

        User user = new User("tyler", "pwd");

        LoginCommandData cmd = new LoginCommandData();
        cmd.setType("login");
        cmd.setUser(user);

        Results results = communicator.send("execcommand", cmd);

        System.out.println("Result successful: " + results.isSuccess());
        System.out.println("Data: " + results.getData());
        System.out.println("Error Info: " + results.getErrorInfo());
        System.out.println("AuthToken from model: " + ClientModel.SINGLETON.getAuthToken());
    }

    @Test
    public void testJoinGame()
    {
        ClientCommunicator communicator = new ClientCommunicator();

        User user = new User("tyler", "pwd");
        RegisterCommandData registerCommandData = new RegisterCommandData();
        registerCommandData.setType("register");
        registerCommandData.setUser(user);
        communicator.send("execcommand", registerCommandData);
        CreateGameCommandData createGameCommandData = new CreateGameCommandData();
        createGameCommandData.setType("creategame");
        createGameCommandData.setNumberOfPlayers(2);
        createGameCommandData.setGameName("testgame1");
        communicator.send("execcommand", createGameCommandData);

        User user1 = new User("joseph", "pwd");
        RegisterCommandData registerCommandData1 = new RegisterCommandData();
        registerCommandData1.setType("register");
        registerCommandData1.setUser(user1);
        communicator.send("execcommand", registerCommandData1);
        JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
        joinGameCommandData.setType("joingame");
        joinGameCommandData.setGameName("testgame1");
        communicator.send("execcommand", joinGameCommandData);

        User user2 = new User("katie", "pwd");
        RegisterCommandData registerCommandData2 = new RegisterCommandData();
        registerCommandData2.setType("register");
        registerCommandData2.setUser(user2);
        communicator.send("execcommand", registerCommandData2);
        JoinGameCommandData joinGameCommandData1 = new JoinGameCommandData();
        joinGameCommandData1.setType("joingame");
        joinGameCommandData1.setGameName("testgame1");
        communicator.send("execcommand", joinGameCommandData1);

        CreateGameCommandData createGameCommandData1 = new CreateGameCommandData();
        createGameCommandData1.setType("creategame");
        createGameCommandData1.setGameName("testgame2");
        createGameCommandData1.setNumberOfPlayers(3);
        communicator.send("execcommand", createGameCommandData1);

        System.out.println(ClientModel.SINGLETON.getAuthToken());
        System.out.println();
    }
}
