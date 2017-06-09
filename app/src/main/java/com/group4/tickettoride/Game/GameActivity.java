package com.group4.tickettoride.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameFragments.Chat_GameHistoryFragment;
import com.group4.tickettoride.Game.GameFragments.DestCardPickerFragment;
import com.group4.tickettoride.Game.GameFragments.ITrainCardPickerPresenter;
import com.group4.tickettoride.Game.GameFragments.EndGameFragment;
import com.group4.tickettoride.Game.GameFragments.PlayerHandFragment;
import com.group4.tickettoride.Game.GameFragments.PlayerInfoFragment;
import com.group4.tickettoride.Game.GameFragments.CardDecksFragment;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerFragment;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerPresenter;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerPresenter2;
import com.group4.tickettoride.R;
import com.group4.tickettoride.State.NotMyTurnState;

public class GameActivity extends AppCompatActivity implements IGameActivity, IMapActivity {

    private GamePresenter presenter;
    private String gameName;
    private Game game;
    private PlayerInfoFragment playerInfoFragment;
    private CardDecksFragment mCardDecksFragment;
    private PlayerHandFragment playerHandFragment;

    View decorView;

    private static final String END_GAME_DIALOG = "EndGameDialog";

    public final String CHAT_GAME_HISTORY_DIALOG = "Chat_GameHistoryDialog";
    private static final String EXTRA_GAME_NAME = "com.group4.tickettoride.Game.game_name";

    public static Intent newIntent(Context packageContext, String gameName)
    {
        Intent i = new Intent(packageContext, GameActivity.class);
        i.putExtra(EXTRA_GAME_NAME, gameName);
        return i;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        decorView = getWindow().getDecorView();

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


        mCardDecksFragment = (CardDecksFragment) fm.findFragmentById(R.id.trainDeck_fragment);
        if (mCardDecksFragment == null) {
            mCardDecksFragment = mCardDecksFragment.newInstance();
        }

        playerHandFragment = (PlayerHandFragment) fm.findFragmentById(R.id.playerHand_fragment);
        if(playerHandFragment == null)
        {
            playerHandFragment = playerHandFragment.newInstance();
        }

        fm.beginTransaction()
                .add(R.id.playerInfo_fragment, playerInfoFragment)
                .add(R.id.trainDeck_fragment, mCardDecksFragment)
                .add(R.id.playerHand_fragment, playerHandFragment)
                .commit();

        // run the destination card picker at the beginning of play, 2 minimum at beginning of game
        if(!ClientModel.SINGLETON.getPlayer().isFirstDestCardsSelected())
        {
            DialogFragment destPickerDialog = DestCardPickerFragment.newInstance(2);
            destPickerDialog.show(this.getSupportFragmentManager(), "dialog");
        }
    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        presenter.onBackPressed();
    }

    private String setPlayersText()
    {
        return game.getPlayers().toString();
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

    @Override
    public void showEndGame()
    {
        FragmentManager manager = getSupportFragmentManager();
        EndGameFragment dialog = new EndGameFragment();
        dialog.show(manager, END_GAME_DIALOG);
    }

    @Override
    public void onOpenChat() {
        showChat_GameHistoryDialog();
    }

    @Override
    public void onClickRoute(RouteSegment r) {
        //build dialog and presenter

        if(presenter.getState().getClass() == NotMyTurnState.class){
            presenter.getState().claimRoute(null, null);
        }

        else {

//            Toast.makeText(this, "Route was clicked yo: " + r.getCityA() + " to " + r.getCityB(), Toast.LENGTH_SHORT).show();
            FragmentManager manager = getSupportFragmentManager();
            TrainCardPickerFragment dialog = new TrainCardPickerFragment();
            dialog.show(manager, "TrainCardPickerDialog");

            ITrainCardPickerPresenter presenter = new TrainCardPickerPresenter2(dialog, r, this.presenter);

            dialog.setPresenter(presenter);

        }


    }

    void showChat_GameHistoryDialog()
    {
        FragmentManager manager = getSupportFragmentManager();
        Chat_GameHistoryFragment dialog = Chat_GameHistoryFragment
                .newInstance(Chat_GameHistoryFragment.CHAT);
        dialog.show(manager, CHAT_GAME_HISTORY_DIALOG);
    }
}
