package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CUpdateMapCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateMapCommand extends CUpdateMapCommandData implements IClientCommand
{
    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onUpdateMapData(super.getRouteSegments(), super.getCities());
    }
}
