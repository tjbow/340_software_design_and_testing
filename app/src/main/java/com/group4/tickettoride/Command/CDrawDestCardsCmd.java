package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CDrawDestCardsCmdData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 6/5/17.
 */

public class CDrawDestCardsCmd extends CDrawDestCardsCmdData implements IClientCommand
{
    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onDrawDestinationCards(super.getReceivedCards());
    }
}
