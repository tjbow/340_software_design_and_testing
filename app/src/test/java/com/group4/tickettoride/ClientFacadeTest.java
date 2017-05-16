package com.group4.tickettoride;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Results;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by tyler on 5/16/17.
 */

public class ClientFacadeTest
{
    @Test
    public void testProcessResults()
    {
        CLoginCommandData loginData = new CLoginCommandData();
        loginData.setAuthToken("testtoken5");
        loginData.setType("login");

        CommandList cmdList = new CommandList();
        cmdList.commandList.add(loginData);

        Results results = new Results(true, null, null, cmdList);

        ClientFacade.SINGLETON.processResults(results);

        assertEquals("testtoken5", ClientModel.SINGLETON.getAuthToken());
    }
}
