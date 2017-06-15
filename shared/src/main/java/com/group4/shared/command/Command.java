package com.group4.shared.command;

import java.io.Serializable;

/**
 * Created by beefhead on 5/12/2017.
 */

public class Command  implements Serializable{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
