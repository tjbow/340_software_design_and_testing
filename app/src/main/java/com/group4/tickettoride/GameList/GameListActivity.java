package com.group4.tickettoride.GameList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.tickettoride.R;

import java.util.List;

public class GameListActivity extends AppCompatActivity implements IGameListActivity {

    private RecyclerView gameList;
    private Button createGameButton;
    private GameAdapter adapter;
    View decorView;
    private IGameListPresenter presenter;

    private static final String CREATE_GAME_DIALOG = "CreateGameDialog";
    private final String FULL_GAME_ERROR = "Sorry, this game is full.";


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
        setContentView(R.layout.activity_game_list);

        setBackground(this, findViewById(R.id.game_list_main), R.drawable.papersmall);

        decorView = getWindow().getDecorView();


        this.presenter = new GameListPresenter(this);
        onWindowFocusChanged(hasWindowFocus());

        //fullScreen();

        gameList = (RecyclerView) findViewById(R.id.gameList_recyclerView);
        gameList.setLayoutManager(new LinearLayoutManager(this));

        createGameButton = (Button) findViewById(R.id.gameList_createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
//        super.onBackPressed();
        presenter.onBackPressed();
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
            gameName = (TextView) itemView.findViewById(R.id.gameList_listItem_gameName);
            playerCount = (TextView) itemView.findViewById(R.id.gameList_listItem_playerCount);
        }

        public void bindGame(Game game)
        {
            this.game = game;
            gameName.setText(game.getGameName());
            playerCount.setText(game.getCurrentPlayerSize() + "/" + game.getPlayerCount());
            if (game.getCurrentPlayerSize() >= game.getPlayerCount())
            {
                //the game cannot be joined because it is full or ongoing
                gameName.setTypeface(null, Typeface.ITALIC);
                gameName.setTextColor(ContextCompat.getColor(GameListActivity.this, R.color.colorDarkGray));
                playerCount.setTypeface(null, Typeface.ITALIC);
                playerCount.setTextColor(ContextCompat.getColor(GameListActivity.this, R.color.colorDarkGray));
            }
            else //the game is not full
            {
                gameName.setTypeface(null, Typeface.NORMAL);
                gameName.setTextColor(ContextCompat.getColor(GameListActivity.this, R.color.colorBlack));
                playerCount.setTypeface(null, Typeface.NORMAL);
                playerCount.setTextColor(ContextCompat.getColor(GameListActivity.this, R.color.colorBlack));
            }
        }

        @Override
        public void onClick(View v) {
            //call presenter's joinGame()
            presenter.joinGame(game.getGameName());
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

    public void displayError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSelectedGame() {
        //TODO @john: implement
    }

}
