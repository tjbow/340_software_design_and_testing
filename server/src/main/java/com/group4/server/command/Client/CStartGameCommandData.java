package com.group4.server.command.Client;

import com.group4.server.command.ClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CStartGameCommandData extends ClientCommand {
    private boolean wasSuccessful;

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }
}
