package com.group4.tickettoride.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.tickettoride.R;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LobbyActivity extends AppCompatActivity implements ILobbyActivity {

    private RecyclerView playerListView;
    private Button startButton;
    private TextView playerStatusView;

    private String playerStatus;
    private int maxPlayers = 0;
    private int currentPlayers = 0;


    private final String playerStatusTemplate = "%d/%d players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        startButton = (Button)findViewById(R.id.start_button);
        playerStatusView = (TextView)findViewById(R.id.player_status_label);

        playerListView = (RecyclerView)findViewById(R.id.player_list);

        playerListView.setAdapter(new Lobby_List_Adaptor(new LinkedList<Player>()));
        playerListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setPlayerList(List<Player> playerList) {
        playerListView.setAdapter(new Lobby_List_Adaptor(playerList));
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
}
