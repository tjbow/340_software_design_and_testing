package com.group4.tickettoride.Network;

import com.google.gson.Gson;
import com.group4.shared.Model.Results;

/**
 * Created by tyler on 5/12/17.
 */

public class Serializer
{
    protected static String serializeCommand(Object o)
    {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    protected static Results deserializeResults(String response)
    {
        Gson gson = new Gson();
        return gson.fromJson(response, Results.class);
    }
}
