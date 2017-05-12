package com.group4.tickettoride.Command;

import com.group4.shared.command.Client.CStartGameCommandData;
import com.group4.shared.command.IClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CStartGameCommand extends CStartGameCommandData implements IClientCommand {
    @Override
    public void execute() {

    }

    boolean startGame(){
        return true;
    }
}
