package com.group4.shared.command.Client;

import com.group4.shared.Model.Message;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by tyler on 5/23/17.
 */

public class CUpdateChatCommandData extends ClientCommand
{
    List<Message> messageList;

    public List<Message> getMessageList()
    {
        return messageList;
    }

    public void setMessageList(List<Message> messageList)
    {
        this.messageList = messageList;
    }
}
