package com.group4.tickettoride.Network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.group4.shared.Model.Results;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by tyler on 5/12/17.
 */

public class ClientCommunicator
{
    private static final String TAG = "ClientCommunicator";

    private static Gson gson = new Gson();

    private String URL_PREFIX;
    private int responseCode;
    private static final String HTTP_POST = "POST";
    private static final String COMMAND_HANDLER_DESIGNATOR = "";
    public static final String AUTHORIZATION_KEY = "authorization";

    public Results send(String urlPath, Object originalObject)
    {
        //TODO: make sure to replace the IP below with the IP where you are running the server
        URL_PREFIX = "http://" + "10.9.18.29" + ":" + "8080";

        HttpURLConnection connection =
                openConnection("/" + urlPath, HTTP_POST, null, true);

        sendToServer(connection, originalObject);

        Results results = (Results) getResult(connection, Results.class);

        return results;
    }

    private HttpURLConnection openConnection(String contextIdentifier, String requestMethod, String authToken, boolean sendingSomthingToServer)
    {
        HttpURLConnection result = null;
        try
        {
            String urlStr = URL_PREFIX + contextIdentifier;
            URL url = new URL(URL_PREFIX + contextIdentifier);
            result = (HttpURLConnection)url.openConnection();
            result.setConnectTimeout(60000); //timeout set at 10 seconds
            result.setReadTimeout(60000);
            result.setRequestMethod(requestMethod);
            result.setDoOutput(sendingSomthingToServer);
            result.setRequestProperty(AUTHORIZATION_KEY, authToken);
            result.connect();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (SocketTimeoutException e)
        {
            System.out.println("Connection timeout.");
            //Log.e("Connection", "Timeout");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private void sendToServer(HttpURLConnection connection, Object objectToSend)
    {
        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter(connection.getOutputStream());
            //Encoding in JSON
            //gson.toJson(objectToSend, printWriter);
            String jsonString = Serializer.serializeCommand(objectToSend);
            printWriter.write(jsonString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(printWriter != null)
            {
                printWriter.close();
            }
        }
    }

    private Object getResult(HttpURLConnection connection, Class<?> klass)
    {
        Object result = null;
        try
        {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                responseCode = HttpURLConnection.HTTP_OK;
            }
            else if(connection.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST)
            {
                responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
            }
            else
            {
                Log.d(TAG, "What in tarnation is this response code.");
            }

            if(connection.getContentLength() == 0)
            {
                Log.d(TAG, "Response body was empty");
            }
            else if(connection.getContentLength() != 0)
            {
                InputStream inputStream = connection.getInputStream();
                //result = gson.fromJson(inputStreamReader, klass);

                result = Serializer.deserializeResults(streamToString(inputStream));
            }
        }
        catch (JsonSyntaxException | JsonIOException | IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private String streamToString(InputStream is)
    {
        Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
