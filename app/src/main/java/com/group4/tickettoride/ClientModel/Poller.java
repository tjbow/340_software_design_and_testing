package com.group4.tickettoride.ClientModel;

/*
 * Created by Tom on 5/14/2017.
 */

import android.util.Log;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Results;
import com.group4.tickettoride.Network.ServerProxy;
import com.group4.tickettoride.Network.ServerProxyNoAsync;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Used by the client to poll the server for new commands
 */
public class Poller implements Runnable
{
    private final String TAG = "Poller";
    static int PERIOD_SECONDS = 1;
    private boolean updateGameList = false;
    private boolean updateGameInfo = false;

    /**
     * Polls the server checking for new entries in the game list and new commands
     */
    private void pollForResults()
    {
        if(updateGameList)
        {
            Log.d(TAG, "poll for gameList");
            importGameList();
        }
        if(updateGameInfo)
        {
            Log.d(TAG, "poll for gameInfo");
            importNewCommands(ClientModel.SINGLETON.getCommandIDIndex());
        }
    }

//    -----------IMPORT METHODS-----------------------
    /**
     * Gets the gameList from the server
     */
    private void importGameList()
    {
        ServerProxy.SINGLETON.getGameList();
    }

    /**
     * Gets the commandList from the server since the command at lastCmdExecuted
     */
    private void importNewCommands(int lastCmdExecuted)
    {
        Log.d(TAG, "lastCmdExecuted is: " + lastCmdExecuted);
        ServerProxy.SINGLETON.getPendingCommands(ClientModel.SINGLETON.getUser(), lastCmdExecuted);
    }


//    ---------POLLING SETTERS-----------------------
    protected void setUpdateGameList(boolean update)
    {
        updateGameList = update;
    }

    protected void setUpdateGameInfo(boolean update)
    {
        updateGameInfo = update;
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
