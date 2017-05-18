package com.group4.shared.command.Client;

import com.group4.shared.command.ClientCommand;

/**
 * Created by tyler on 5/18/17.
 */

public class CEndGameCommandData extends ClientCommand
{
    private boolean wasSuccessful;

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }
}
