package com.group4.server.Network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.group4.shared.Model.Results;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

/**
 * Created by tyler on 5/12/17.
 */

public class Serializer
{
    protected static String serializeResults(Results r)
    {
        Gson gson = new Gson();
        String str = null;
        try
        {
            str = gson.toJson(r);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Results failure = new Results(false, null, "The server serializer broke", null);
            return gson.toJson(failure);
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String str = null;
//        try
//        {
//            str = mapper.writeValueAsString(r);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }


        return str;
    }

//    protected static String serializeResults(Results r)
//    {
//        return treeParse(r);
//    }

    private static String treeParse(Results results)
    {
        ObjectMapper mapper = new ObjectMapper();

        String res = null;
        try
        {
            ObjectNode resultsObj = buildResults(results, mapper);
            res = mapper.writeValueAsString(resultsObj);
        } catch (Exception e)
        {
            e.printStackTrace();
            return new Gson().toJson(new Results(false, null, "The server serializer broke", null));
        }


        return res;
    }

    private static ObjectNode buildResults(Results results, ObjectMapper mapper) throws Exception
    {
        ObjectNode resultsObj = mapper.createObjectNode();
        resultsObj.putPOJO("success", results.isSuccess());
        resultsObj.putPOJO("data", results.getData());
        resultsObj.putPOJO("errorInfo", results.getErrorInfo());
        resultsObj.putPOJO("commandList", results.getCommandList());

//        ArrayNode commandListArray = mapper.createArrayNode();
//        for (ClientCommand cmd : results.getCommandList().getCommandList())
//        {
//            commandListArray.add(buildCommand(cmd, mapper));
//        }
//
//        ObjectNode catalogObj = mapper.createObjectNode();
//        catalogObj.put("COMMANDLIST", commandListArray);
////        catalogObj.putPOJO("CATALOG", cdArr);

        return resultsObj;
    }

    private static ObjectNode buildCommand(ClientCommand cmd, ObjectMapper mapper) throws Exception {

        ObjectNode commandObj = mapper.createObjectNode();

//        commandObj.put("type", cmd.getType());
//        commandObj.put("ARTIST", cd.getArtist());
//        commandObj.put("COUNTRY", cd.getCountry());
//        commandObj.put("COMPANY", cd.getCompany());
//        commandObj.put("PRICE", cd.getPrice());
//        commandObj.put("YEAR", cd.getYear());

        return commandObj;
    }

    protected static Command deserializeCommand(String str)
    {
        Gson gson = new Gson();
        Command cmd = gson.fromJson(str, Command.class);
        return cmd;
    }
}
