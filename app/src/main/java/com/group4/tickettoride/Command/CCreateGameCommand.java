package com.group4.tickettoride.Command;

import com.group4.shared.command.Client.CCreateGameCommandData;
import com.group4.shared.command.IClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CCreateGameCommand extends CCreateGameCommandData implements IClientCommand {
    @Override
    public void execute() {

    }

    boolean createGame(){
        return true;
    }
}
