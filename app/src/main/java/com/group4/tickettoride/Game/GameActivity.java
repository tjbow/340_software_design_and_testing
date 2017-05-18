package com.group4.tickettoride.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.group4.shared.Model.Game;
import com.group4.tickettoride.R;

public class GameActivity extends AppCompatActivity implements IGameActivity {

    private IGamePresenter presenter;
    private String gameName;
    private Game game;


    private static final String EXTRA_GAME_NAME = "com.group4.tickettoride.Game.game_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.presenter = new GamePresenter(this);

        //TODO @john: add textviews to display game data
        //set presenter
        //set presenter game to game from extra

        //get game from model
    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        presenter.onBackPressed();
    }

    public static Intent newIntent(Context packageContext, String gameName)
    {
        Intent i = new Intent(packageContext, GameActivity.class);
        i.putExtra(EXTRA_GAME_NAME, gameName);
        return i;
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

}
