package com.group4.tickettoride.Login_Register;



import android.content.Intent;

import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;


class Login_RegisterPresenter implements ILogin_RegisterPresenter, Observer {

    private Login_RegisterActivity activity;
    private NextLayerFacade nextLayerFacade;

    Login_RegisterPresenter(Login_RegisterActivity loginRegisterActivity)
    {
        this.activity = loginRegisterActivity;
        nextLayerFacade = NextLayerFacade.SINGLETON;
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void login()
    {
        String username = activity.getUsername();
        String password = activity.getPassword();

        nextLayerFacade.login(username, password);
    }

    @Override
    public void register()
    {
        String username = activity.getUsername();
        String password = activity.getPassword();

        nextLayerFacade.register(username, password);
    }


    @Override
    public void displayError(String error) {
        activity.displayError(error);
    }


    @Override
    public void update(Observable o, Object arg) {
        o.deleteObserver(this);
        Intent i = new Intent(activity, GameListActivity.class);
        activity.startActivity(i);
        activity.finish();
    }
}
