package com.group4.tickettoride.GameList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.tickettoride.R;

import java.util.List;

public class GameListActivity extends AppCompatActivity implements IGameListActivity {

    private RecyclerView gameList;
    private Button createGameButton;
    private GameAdapter adapter;
    private IGameListPresenter presenter;

    private static final String CREATE_GAME_DIALOG = "CreateGameDialog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.presenter = new GameListPresenter(this);

        gameList = (RecyclerView) findViewById(R.id.gameList_recyclerView);
        gameList.setLayoutManager(new LinearLayoutManager(this));

        createGameButton = (Button) findViewById(R.id.gameList_createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start create game dialog
                showDialog();
            }
        });
    }

    void showDialog()
    {
        FragmentManager manager = getSupportFragmentManager();
        CreateGameFragment dialog = new CreateGameFragment();
        dialog.show(manager, CREATE_GAME_DIALOG);
    }


    //------------------- RECYCLERVIEW CODE -----------------------

    private class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameName;
        TextView playerCount;

        private Game game;

        public GameHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
            gameName = (TextView) findViewById(R.id.gameList_listItem_gameName);
            playerCount = (TextView) findViewById(R.id.gameList_listItem_playerCount);
        }

        public void bindGame(Game game)
        {
            this.game = game;
            gameName.setText(game.getGameName());
            playerCount.setText(game.getPlayers().size() + "/" + game.getPlayerCount());
        }

        @Override
        public void onClick(View v) {
            //call presenter's joinGame()
        }
    }

    private class GameAdapter extends RecyclerView.Adapter<GameHolder> {
        private List<Game> games;

        public GameAdapter(List<Game> games)
        {
            this.games = games;
        }

        @Override
        public GameHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(GameListActivity.this);
            View view = layoutInflater.inflate(R.layout.game_list_item, parent, false);
            return new GameHolder(view);
        }

        @Override
        public void onBindViewHolder(GameHolder holder, int position)
        {
            Game game = games.get(position);
            holder.bindGame(game);
        }
        @Override
        public int getItemCount()
        {
            return games.size();
        }

        public void setGames(List<Game> games)
        {
            this.games = games;
        }

    }
    //------------------- END RECYCLERVIEW -----------------------

    //------------------- INTERFACE IMPLEMENTATION --------------------
    @Override
    public void setGameList(GameList gameListObj) {

        List<Game> games = gameListObj.getGameList();
        if (adapter == null)
        {
            adapter = new GameAdapter(games);
            gameList.setAdapter(adapter);
        }
        else
        {
            adapter.setGames(games);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSelectedGame() {
        //TODO @john: implement
    }
}
