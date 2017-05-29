package com.group4.tickettoride.Login_Register;



import android.content.Intent;

import com.group4.shared.Model.Game.GAME_STATUS;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameActivity;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.Lobby.LobbyActivity;
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
            //move on to GameListActivity
            o.deleteObserver(this);
            Intent i = new Intent(activity, GameListActivity.class);
            activity.startActivity(i);
            activity.finish();
        }
        else if (arg.getClass() == String.class)
        {
            //Error received
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    displayError( (String) arg);
                }
            });
        }
        else if (arg.getClass() == GAME_STATUS.class)
        {
            GAME_STATUS status = (GAME_STATUS) arg;

            if (status == GAME_STATUS.WAITING)
            {
                //move on to LobbyActivity
                o.deleteObserver(this);
                activity.startActivity(new Intent(activity, LobbyActivity.class));
                activity.finish();
            }
            else if (status == GAME_STATUS.ONGOING)
            {
                //move on to GameActivity
                o.deleteObserver(this);
                activity.startActivity(new Intent(activity, GameActivity.class));
                activity.finish();
            }
        }
    }
}
