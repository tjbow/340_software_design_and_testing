package com.group4.Network;

import com.group4.shared.Model.Results;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

/**
 * Created by tyler on 5/12/17.
 */

public class HandlerBase implements HttpHandler
{
    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {
        System.out.println("HandlerBase initiated");
        String query = httpExchange.getRequestURI().getPath();
        if (!query.contains("/execcommand")) // it isn't a command
        {
            //Results result = new Results(false, null, "invalid API name");
            //String response = Serializer.serializeResults(result);
            //httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, response.length());

            //Utilities.sendStreamToClient(httpExchange, response);
        }
        else // it is a command
        {
            //get the request body
            String request = readString(httpExchange.getRequestBody());

            //handle the command
            ExecCommandHandler cmdHandler = new ExecCommandHandler();
            Results results = cmdHandler.handleCommand(request);

            //serialize the results
            String response = Serializer.serializeResults(results);

            //send the result back to the client
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

            writeString(httpExchange, response);
        }
    }

    private String readString(InputStream is)
    {
        Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void writeString(HttpExchange exchange, String response) throws IOException
    {
        BufferedOutputStream out = new BufferedOutputStream(exchange.getResponseBody());
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());

        byte [] buffer = new byte [4096];
        int count;
        while ((count = bis.read(buffer)) != -1)
        {
            out.write(buffer, 0, count);
        }
        out.flush();
        out.close();
    }
}
