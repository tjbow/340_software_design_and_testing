package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.DrawFaceDownCardCmdData;

/**
 * Created by tyler on 6/2/17.
 */

public class DrawFaceDownTrainCardCommand extends DrawFaceDownCardCmdData implements IServerCommand
{
    private Results drawFaceDownCard()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.drawFaceDownTrainCard(super.getUserName());
    }

    @Override
    public Results execute()
    {
        return drawFaceDownCard();
    }
}
