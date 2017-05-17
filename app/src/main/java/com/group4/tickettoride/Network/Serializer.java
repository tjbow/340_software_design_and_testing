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
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.command.IClientCommand;
import com.group4.shared.command.Server.LoginCommandData;
import com.group4.tickettoride.Command.CCreateGameCommand;
import com.group4.tickettoride.Command.CGetGameListCommand;
import com.group4.tickettoride.Command.CJoinGameCommand;
import com.group4.tickettoride.Command.CLoginCommand;
import com.group4.tickettoride.Command.CRegisterCommand;

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
        return treeParse(response);
    }

    private static Results treeParse(String response){
        ObjectMapper mapper = new ObjectMapper();
        List<ClientCommand> iCommands = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        Results results = null;
        try {
            JsonNode root = mapper.readTree(response.getBytes());
            JsonNode successNode = root.path("success");
            JsonNode dataNode = root.path("data");
            JsonNode errorInfoNode = root.path("errorInfo");
            JsonNode commandLst = root.path("commandList");
            JsonNode commands = commandLst.path("commandList");
            Iterator<JsonNode> itr = commands.iterator();
            while (itr.hasNext()){
                JsonNode next = itr.next();
                Command c = mapper.treeToValue(next,Command.class);
                switch (c.getType())
                {
                    case "login":
                        iCommands.add(mapper.treeToValue(next,CLoginCommand.class));
                        break;
                    case "register":
                        iCommands.add(mapper.treeToValue(next,CRegisterCommand.class));
                        break;
                    case "creategame":
                        iCommands.add(mapper.treeToValue(next,CCreateGameCommand.class));
                        break;
                    case "joingame":
                        iCommands.add(mapper.treeToValue(next,CJoinGameCommand.class));
                        break;
                    case "getgamelist":
                        ClientCommand clientCommand = new Gson().fromJson(next.toString(), CGetGameListCommand.class);
                        iCommands.add(clientCommand);
                        break;
                    default:
                        System.out.println("no command");
                        break;
                }
            }

            CommandList cmdList = new CommandList();
            cmdList.setCommandList(iCommands);

            results = new Results(successNode.asBoolean(), dataNode.asText(), errorInfoNode.asText(), cmdList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return results;
    }
}