package com.group4.server.command.Client;

import com.group4.server.command.ClientCommand;

/**
 * Created by beefhead on 5/12/2017.
 */

public class CRegisterCommandData extends ClientCommand {
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
