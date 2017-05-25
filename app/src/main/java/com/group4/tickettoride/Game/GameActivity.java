package com.group4.tickettoride.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameFragments.PlayerInfoFragment;
import com.group4.tickettoride.Game.GameFragments.TrainDeckFragment;
import com.group4.tickettoride.R;

import java.util.Map;

public class GameActivity extends AppCompatActivity implements IGameActivity {

    private IGamePresenter presenter;
    private String gameName;
    private Game game;
    private PlayerInfoFragment playerInfoFragment;
    private TrainDeckFragment trainDeckFragment;


    private static final String EXTRA_GAME_NAME = "com.group4.tickettoride.Game.game_name";

    public static Intent newIntent(Context packageContext, String gameName)
    {
        Intent i = new Intent(packageContext, GameActivity.class);
        i.putExtra(EXTRA_GAME_NAME, gameName);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null)
                gameName = null;
            else
                gameName = extras.getString(EXTRA_GAME_NAME);
        }
        else
            this.gameName = (String) savedInstanceState.getSerializable(EXTRA_GAME_NAME);

        this.presenter = new GamePresenter(this);

        FragmentManager fm = this.getSupportFragmentManager();
        playerInfoFragment = (PlayerInfoFragment) fm.findFragmentById(R.id.playerInfo_fragment);
        if (playerInfoFragment == null) {
            playerInfoFragment = playerInfoFragment.newInstance();

        }


        trainDeckFragment = (TrainDeckFragment) fm.findFragmentById(R.id.trainDeck_fragment);
        if (trainDeckFragment == null) {
            trainDeckFragment = trainDeckFragment.newInstance();
        }

        fm.beginTransaction()
                .add(R.id.playerInfo_fragment, playerInfoFragment)
                .add(R.id.trainDeck_fragment, trainDeckFragment)
                .commit();

    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        presenter.onBackPressed();
    }

    private String setPlayersText()
    {
        StringBuilder players = new StringBuilder("Players: ");

        Map<String, Player> currPlayers = game.getPlayers();

        for(Player player : currPlayers.values())
        {
            players.append(player.getUserName() + "   ");
        }

        return players.toString();
    }

    private void endGame()
    {
        presenter.endGame(gameName);
    }

    protected void displayError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    //------------------- INTERFACE IMPLEMENTATION --------------------

    @Override
    public void setGame(Game game)
    {
        this.game = game;
        this.gameName = game.getGameName();
    }

    @Override
    public void startNextActivity(Class nextClass) {
        Intent i = new Intent(GameActivity.this, nextClass);
        startActivity(i);
        finish();
    }
}
