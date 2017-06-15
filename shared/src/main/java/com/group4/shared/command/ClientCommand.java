package com.group4.shared.command;

import java.io.Serializable;

/**
 * Created by beefhead on 5/12/2017.
 */

public class ClientCommand extends Command implements Serializable
{
    private int commandNumber;

    public int getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }
}
