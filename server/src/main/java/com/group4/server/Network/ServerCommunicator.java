package com.group4.server.Network;

import com.group4.server.ServerModel.ServerFacade;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by tyler on 5/12/17.
 */

public class ServerCommunicator
{
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    private void run(String portNumber, String persistencePluginName)
    {
        System.out.println("Initializing HTTP Server");

        try
        {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/", new HandlerBase());
        //server.createContext("/execcommand", new ExecCommandHandler());

        System.out.println("Starting server");
        server.start();
        System.out.println("Server started");

        ServerFacade.loadPersistencePlugin(persistencePluginName);
    }

    // args should contain the port number
    public static void main(String[] args)
    {
        String portNumber = args[0];
        String persistencePluginName = args[1];
        new ServerCommunicator().run(portNumber, persistencePluginName);
    }
}
