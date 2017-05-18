package com.group4.tickettoride.Lobby;

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

import java.util.LinkedList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity implements ILobbyActivity {

    private RecyclerView playerListView;
    private Button startButton;
    private TextView playerStatusView;
    private TextView gameNameView;

    private String playerStatus;
    private int maxPlayers = 0;
    private int currentPlayers = 0;

    private ILobbyPresenter presenter;

    private static final String EXTRA_GAME_ID = "com.group4.tickettoride.Lobby.game_id";
    private final String playerStatusTemplate = "%d/%d players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

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

        playerListView.setAdapter(new Lobby_List_Adapter(new LinkedList<Player>()));
        playerListView.setLayoutManager(new LinearLayoutManager(this));

        gameNameView = (TextView) findViewById(R.id.game_name);

        this.presenter = new LobbyPresenter(this);
    }

    @Override
    public void setPlayerList(List<Player> playerList) {
        playerListView.setAdapter(new Lobby_List_Adapter(playerList));
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
