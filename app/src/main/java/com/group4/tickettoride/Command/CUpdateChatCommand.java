package com.group4.tickettoride.Command;

import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CUpdateChatCommandData;
import com.group4.shared.command.IClientCommand;
import com.group4.tickettoride.ClientModel.ClientFacade;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateChatCommand extends CUpdateChatCommandData implements IClientCommand
{
    private void updateChat()
    {

    }

    @Override
    public Results execute()
    {
        return ClientFacade.SINGLETON.onUpdateChat(super.getMessageList());
    }
}
