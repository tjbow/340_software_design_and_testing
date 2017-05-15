package com.group4.shared.command.Server;

import com.group4.shared.Model.User;
import com.group4.shared.command.Command;

/**
 * Created by beefhead on 5/12/2017.
 */

public class LoginCommandData extends Command {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
