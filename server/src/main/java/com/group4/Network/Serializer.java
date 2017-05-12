package com.group4.Network;

import com.google.gson.Gson;
import com.group4.shared.Model.Results;
import com.group4.shared.command.Command;

import java.io.Reader;

/**
 * Created by tyler on 5/12/17.
 */

public class Serializer
{
    protected static String serializeResults(Results r)
    {
        Gson gson = new Gson();
        return gson.toJson(r);
    }

    protected static Command deserializeCommand(String str)
    {
        Gson gson = new Gson();
        Command cmd = gson.fromJson(str, Command.class);
        return cmd;
    }
}
