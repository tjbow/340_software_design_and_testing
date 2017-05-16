package com.group4.tickettoride.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.tickettoride.R;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        //TODO: Drew: this is just test code below.  move code to correct places
        List<Player> players = new LinkedList<>();
        Player p1 = new Player(new UUID(4,5),new User("s","s",new UUID(1l,2l)));
        p1.setColor("red");
        p1.setUserName("username");
        players.add(p1);

        RecyclerView r = (RecyclerView)findViewById(R.id.player_list);
        r.setAdapter(new Lobby_List_Adaptor(players));
        r.setLayoutManager(new LinearLayoutManager(this));
    }
}
