package com.group4.tickettoride.Command;

import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.IClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CLoginCommand extends CLoginCommandData implements IClientCommand {
    @Override
    public void execute() {

    }

    boolean login(){
        return true;
    }

}
