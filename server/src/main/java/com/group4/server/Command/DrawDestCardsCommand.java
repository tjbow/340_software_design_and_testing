package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.DrawDestCardsCommandData;

/**
 * Created by tyler on 5/23/17.
 */

public class DrawDestCardsCommand extends DrawDestCardsCommandData implements IServerCommand
{
    private Results drawDestCards()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.drawDestinationCards(super.getUserName(), super.getSelectedCards());
    }

    @Override
    public Results execute()
    {
        return null;
    }
}
