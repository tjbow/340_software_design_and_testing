package com.group4.tickettoride.Command;

import com.group4.shared.command.Client.CRegisterCommandData;
import com.group4.shared.command.IClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CRegisterCommand extends CRegisterCommandData implements IClientCommand{
    boolean register(){
        return true;
    }

    @Override
    public void execute() {

    }
}
