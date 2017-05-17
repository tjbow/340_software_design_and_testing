package com.group4.tickettoride.Login_Register;



import android.content.Intent;

import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;


class Login_RegisterPresenter implements ILogin_RegisterPresenter, Observer {

    private Login_RegisterActivity activity;

    Login_RegisterPresenter(Login_RegisterActivity loginRegisterActivity)
    {
        this.activity = loginRegisterActivity;
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void login()
    {
        String username = activity.getUsername();
        String password = activity.getPassword();

        NextLayerFacade.SINGLETON.login(username, password);
    }

    @Override
    public void register()
    {
        String username = activity.getUsername();
        String password = activity.getPassword();

        NextLayerFacade.SINGLETON.register(username, password);
    }


    @Override
    public void displayError(String error) {
        activity.displayError(error);
    }


    @Override
    public void update(Observable o, final Object arg) {
        if (arg.getClass() == Boolean.class)
        {
            //things worked properly, move on to gameList
            o.deleteObserver(this);

            Intent i = new Intent(activity, GameListActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
        else if (arg.getClass() == String.class)
        {
            //it's an error
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayError( (String) arg);
                }
            });

        }


    }
}
