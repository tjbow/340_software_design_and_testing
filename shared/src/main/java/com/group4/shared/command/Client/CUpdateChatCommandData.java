package com.group4.shared.command.Client;

import com.group4.shared.Model.Message;
import com.group4.shared.Model.MessageList;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateChatCommandData extends ClientCommand
{
    MessageList messageList;

    public MessageList getMessageList()
    {
        return messageList;
    }

    public void setMessageList(MessageList messageList)
    {
        this.messageList = messageList;
    }
}
