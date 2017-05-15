package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.IClientCommand;

/**
 * Created by abgill on 5/12/2017.
 */

public class CLoginCommand extends CLoginCommandData implements IClientCommand {
    @Override
    public Results execute() {
        return null;
    }

    boolean login(){
        return true;
    }

}
