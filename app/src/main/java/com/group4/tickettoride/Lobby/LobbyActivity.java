package com.group4.tickettoride.Lobby;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group4.shared.Model.Player;
import com.group4.tickettoride.R;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.LinkedList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity implements ILobbyActivity {

    private RecyclerView playerListView;
    private Button startButton;
    private TextView playerStatusView;
    private TextView gameNameView;
    private View decorView;

    private String playerStatus;
    private int maxPlayers = 0;
    private int currentPlayers = 0;

    private ILobbyPresenter presenter;

    private static final String EXTRA_GAME_ID = "com.group4.tickettoride.Lobby.game_id";
    private final String playerStatusTemplate = "%d/%d players";

    public void setBackground(Context context, View view, int drawableId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        view.setBackground(bitmapDrawable);
    }


    private void fullScreen() {

        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            fullScreen();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        setBackground(this,findViewById(R.id.lobby_main),R.drawable.papersmall);

        Iconify.with(new FontAwesomeModule());


        decorView = getWindow().getDecorView();

        startButton = (Button)findViewById(R.id.start_button);
        setStartButtonEnabled(false);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: @john add something fun here
                presenter.startGame();
            }
        });
        playerStatusView = (TextView)findViewById(R.id.player_status_label);

        playerListView = (RecyclerView)findViewById(R.id.player_list);

        playerListView.setAdapter(new Lobby_List_Adapter(this,new LinkedList<Player>()));
        playerListView.setLayoutManager(new LinearLayoutManager(this));

        gameNameView = (TextView) findViewById(R.id.game_name);

        this.presenter = new LobbyPresenter(this);
    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        presenter.onBackPressed();
    }

    @Override
    public void setPlayerList(List<Player> playerList) {
        playerListView.setAdapter(new Lobby_List_Adapter(this, playerList));
    }

    @Override
    public void setStartButtonEnabled(boolean isEnabled) {
        startButton.setEnabled(isEnabled);
        startButton.setClickable(isEnabled);
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        playerStatus = String.format(playerStatusTemplate,this.currentPlayers,this.maxPlayers);
        playerStatusView.setText(this.playerStatus);

    }

    @Override
    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
        playerStatus = String.format(playerStatusTemplate,this.currentPlayers,this.maxPlayers);
        playerStatusView.setText(this.playerStatus);

    }

    @Override
    public void setGameName(String gameName) {
        gameNameView.setText(gameName);
    }


    public void displayError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
