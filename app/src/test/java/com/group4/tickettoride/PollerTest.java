package com.group4.tickettoride;

import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.Poller;
import org.junit.Test;

import static java.lang.Thread.sleep;

/**
 * Created by Tom on 5/16/2017.
 */

public class PollerTest
{
    /**
     * Test that the poller is running regularly
     */
    @Test
    public void testRegularUpdates() throws InterruptedException
    {
        Poller poller = new Poller();
        Thread thread1 = new Thread(poller, "Thread 1");

        thread1.start();
        Thread.sleep(20000);
    }

    @Test
    public void testClientFacade() throws InterruptedException
    {
        //ClientFacade.SINGLETON.reportGameState();
        Thread.sleep(20000);
    }
}
