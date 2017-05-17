package com.group4.tickettoride.ClientModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;
import com.group4.tickettoride.Network.ServerProxy;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Used by the client to poll the server for new commands
 */
public class Poller implements Runnable
{
    static int PERIOD_SECONDS = 3;

    /**
     * Polls the server checking for new entries in the game list and new commands
     */
    private void pollForResults()
    {
        importGameList();
        runNewCommands(ClientModel.SINGLETON.getCommandIDIndex());
    }

    /**
     * Gets and runs the list of commands since the command at commandID
     * @param commandID the last command executed on the client
     */
    private void runNewCommands(int commandID)
    {
        Results results = ServerProxy.SINGLETON.getCommandsSinceIndex(commandID);
        ClientFacade.SINGLETON.processResults(results);
    }

    /**
     * Gets the game list and updates the client model through the facade
     */
    private void importGameList()
    {
        Results results = ServerProxy.SINGLETON.getGameList();
    }

    @Override
    public void run()
    {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(1);
        execService.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                pollForResults(); //Todo: Tom: add clientModel.currentCommand
            }
        }, 0, PERIOD_SECONDS, TimeUnit.SECONDS);
    }



}
