package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CRegisterCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by abgill on 5/12/2017.
*/

public class CRegisterCommand extends CRegisterCommandData implements IClientCommand{
    boolean register(){
        return true;
    }

    @Override
    public Results execute() {
        return ClientFacade.SINGLETON.onRegister(super.getAuthToken(), super.getUsername());
    }
}
