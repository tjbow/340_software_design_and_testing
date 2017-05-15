package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CGetGameListCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by abgill on 5/12/2017.
 */

public class CGetGameListCommand extends CGetGameListCommandData implements IClientCommand {
    @Override
    public Results execute() {
        return new ClientFacade().onGetGameList(super.getGameList());
    }

    void updateGamelist(){

    }
}
