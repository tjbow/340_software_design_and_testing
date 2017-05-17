package com.group4.tickettoride.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.group4.tickettoride.R;

public class GameActivity extends AppCompatActivity {

    private static final String EXTRA_GAME_NAME = "com.group4.tickettoride.Game.game_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //TODO @john: add textviews to display game data
        //set presenter
        //set presenter game to game from extra

        //get game from model
    }

    public static Intent newIntent(Context packageContext, String gameName)
    {
        Intent i = new Intent(packageContext, GameActivity.class);
        i.putExtra(EXTRA_GAME_NAME, gameName);
        return i;
    }
}
