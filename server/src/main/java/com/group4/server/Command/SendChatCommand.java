package com.group4.server.Command;

import com.group4.server.ServerModel.ServerFacade;
import com.group4.shared.Model.Results;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.command.IServerCommand;
import com.group4.shared.command.Server.SendChatCommandData;

/**
 * Created by tyler on 5/23/17.
 */

public class SendChatCommand extends SendChatCommandData implements IServerCommand
{
    private Results sendChat()
    {
        IServer serverFacade = new ServerFacade();
        return serverFacade.sendChat(super.getMessage());
    }

    @Override
    public Results execute()
    {
        return sendChat();
    }
}
