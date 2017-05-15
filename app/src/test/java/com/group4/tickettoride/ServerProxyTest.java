package com.group4.tickettoride;

import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ServerProxy.ServerProxy;

import org.junit.Test;

/**
 * Created by tyler on 5/15/17.
 */

public class ServerProxyTest
{
    @Test
    public void testRegister()
    {
        //create a user to be registered
        User user = new User("tyler", "pwd");

        //call register on the ServerProxy
        Results results = ServerProxy.SINGLETON.register(user);

        //if the results are not null than there is an error
        if(results != null)
        {
            System.out.println("Error: " + results.getErrorInfo());
        }
        else
        {
            System.out.println("Success");
        }
    }

    @Test
    public void testLogin()
    {
        //create a user to be logged in
        User user = new User("tyler", "pwd");

        //call login on the ServerProxy
        Results results = ServerProxy.SINGLETON.login(user);

        //if the results are not null than there is an error
        if(results != null)
        {
            System.out.println("Error: " + results.getErrorInfo());
        }
        else
        {
            System.out.println("Success");
        }

    }
}
