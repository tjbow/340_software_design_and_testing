package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.DrawTrainCardsCommandData;

/**
 * Created by tyler on 5/30/17.
 */

public class DrawTrainCardsCommand extends DrawTrainCardsCommandData implements IServerCommand
{
    private Results drawTrainCards()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.drawFaceDownTrainCard(super.getUserName());
    }
    @Override
    public Results execute()
    {
        return drawTrainCards();
    }
}
