package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CUpdateClientIndexCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 6/9/17.
 */

public class CUpdateClientIndexCommand extends CUpdateClientIndexCommandData implements IClientCommand
{

    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onUpdateIndexState(super.getCurrentIndex());
    }
}
