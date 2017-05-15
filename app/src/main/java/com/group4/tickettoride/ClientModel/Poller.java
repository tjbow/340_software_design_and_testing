package com.group4.tickettoride.ClientModel;

/*
 * Created by Tom on 5/14/2017.
 */

import com.group4.shared.Model.CommandList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Used by the client to poll the server for new commands
 */
public class Poller
{
    private final ScheduledExecutorService pollServer = Executors.newScheduledThreadPool(1);

    /**
     * Gets the list of commands since the command at commandID
     * Client model needs to hold an int of current command or keep a list of commands it has executed
     * IServer needs a getNewCommands method
     * ServerModel needs to hold a list of all commands
     * @return
     */
    CommandList getNewCommands(int commandID)
    {
        return null;
    }
}
