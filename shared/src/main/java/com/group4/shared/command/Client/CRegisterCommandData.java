package com.group4.shared.command.Client;

import com.group4.shared.command.ClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CRegisterCommandData extends ClientCommand {
    private String authToken;
    private String username;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
