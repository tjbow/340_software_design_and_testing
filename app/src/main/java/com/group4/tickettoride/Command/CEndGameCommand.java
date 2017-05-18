package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CEndGameCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 5/18/17.
 */

public class CEndGameCommand extends CEndGameCommandData implements IClientCommand
{
    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onEndGame();
    }
}
