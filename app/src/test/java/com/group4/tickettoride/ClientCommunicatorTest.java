package com.group4.tickettoride;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Command;
import com.group4.tickettoride.Network.ClientCommunicator;

import org.junit.Test;
/**
 * Created by tyler on 5/12/17.
 */

public class ClientCommunicatorTest
{
    @Test
    public void sendObject()
    {
        ClientCommunicator communicator = new ClientCommunicator();
        Command cmd = new Command();
        cmd.setType("test");
        Results results = communicator.send("execcommand", cmd);
        System.out.println("Result successful: " + results.isSuccess());
        System.out.println("Data: " + results.getData());
        System.out.println("Error Info: " + results.getErrorInfo());
    }
}
