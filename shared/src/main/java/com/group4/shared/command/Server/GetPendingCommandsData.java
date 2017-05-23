package com.group4.shared.command.Server;

import com.group4.shared.Model.User;
import com.group4.shared.command.Command;

/**
 * Created by tyler on 5/23/17.
 */

public class GetPendingCommandsData extends Command
{
    User user;
    int lastCmdExecuted;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public int getLastCmdExecuted()
    {
        return lastCmdExecuted;
    }

    public void setLastCmdExecuted(int lastCmdExecuted)
    {
        this.lastCmdExecuted = lastCmdExecuted;
    }
}
