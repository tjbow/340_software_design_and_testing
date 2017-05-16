package com.group4.tickettoride.Network;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.tickettoride.Command.CLoginCommand;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
//        Gson gson = new Gson();
//
//        return gson.fromJson(response, Results.class);

        treeParse(response);
        return null;
    }

    protected static List<IClientCommand> treeParse(String response){
        ObjectMapper mapper = new ObjectMapper();
        List<IClientCommand> iCommands = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        try {
            JsonNode root = mapper.readTree(response.getBytes());
            JsonNode commandLst = root.path("commandList");
            JsonNode commands = commandLst.path("commandList");
            Iterator<JsonNode> itr = commands.iterator();
            while (itr.hasNext()){
                JsonNode next = itr.next();
                Command c = mapper.treeToValue(next,Command.class);
                if(c.getType().equals("login")){
                    iCommands.add(mapper.treeToValue(next,CLoginCommand.class));
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return iCommands;
    }
}