package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.DrawFaceUpCardCmdData;

/**
 * Created by tyler on 6/2/17.
 */

public class DrawFaceUpTrainCardCommand extends DrawFaceUpCardCmdData implements IServerCommand
{
    private Results drawFaceUpTrainCard()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.drawFaceUpTrainCard(super.getUserName(), super.getCardPosition());
    }

    @Override
    public Results execute()
    {
        return drawFaceUpTrainCard();
    }
}
