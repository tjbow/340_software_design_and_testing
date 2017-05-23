package com.group4.shared.command.Server;

import com.group4.shared.Model.Message;
import com.group4.shared.command.Command;

/**
 * Created by tyler on 5/23/17.
 */

public class SendChatCommandData extends Command
{
    private Message message;

    public Message getMessage()
    {
        return message;
    }

    public void setMessage(Message message)
    {
        this.message = message;
    }
}
