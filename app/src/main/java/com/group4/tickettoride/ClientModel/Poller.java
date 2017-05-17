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

    ClientModel clientModel; //Todo: Tom: Singleton
    /**
     * Gets the list of commands since the command at commandID
     * Client model needs to hold an int of current command or keep a list of commands it has executed
     * IServer needs a getNewCommands method
     * ServerModel needs to hold a list of all commands
     * @return
     */
    CommandList getNewCommands(int commandID)
    {
        //Results results = ServerProxy.SINGLETON.getCommandsSinceIndex(commandID);
        return null;
    }

    /**
     * Gets the game list and updates the client model through the facade
     */
    void getGameList()
    {
        Results results = ServerProxy.SINGLETON.getGameList();
        if(results.isSuccess())
        {
            //TODO: Tom: Develop a way of getting the game list out of results
            // for now use a blank game list
            GameList gameList = new GameList(new ArrayList<Game>() );
            ClientFacade.SINGLETON.onGetGameList(gameList);
        }
    }

    @Override
    public void run()
    {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(1);
        //execService.scheduleAtFixedRate(()->{pollServer();}, 0, PERIOD_SECONDS, TimeUnit.SECONDS);
        // this might need to be callable if we need to return
        execService.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                getNewCommands(0); //Todo: Tom: add clientModel.currentCommand
            }
        }, 0, PERIOD_SECONDS, TimeUnit.SECONDS);
    }



}
