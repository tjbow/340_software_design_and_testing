package com.group4.tickettoride;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Command;
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
}
