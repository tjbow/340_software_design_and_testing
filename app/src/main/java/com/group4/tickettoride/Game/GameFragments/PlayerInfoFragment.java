package com.group4.tickettoride.Game.GameFragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Typeface.BOLD;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerInfoFragment extends Fragment implements IPlayerInfoFragment{

    //Player 1 Views
    TextView player1Username;
    ImageView player1WinningIcon;
    ImageView player1LongestPathIcon;
    TextView player1Points;
    TextView player1TrainCards;
    TextView player1DestCards;
    TextView player1TrainCars;

    //Player 2 Views
    TextView player2Username;
    ImageView player2WinningIcon;
    ImageView player2LongestPathIcon;
    TextView player2Points;
    TextView player2TrainCards;
    TextView player2DestCards;
    TextView player2TrainCars;

    //Player 3 Views
    TextView player3Username;
    ImageView player3WinningIcon;
    ImageView player3LongestPathIcon;
    TextView player3Points;
    TextView player3TrainCards;
    TextView player3DestCards;
    TextView player3TrainCars;

    //Player 4 Views
    TextView player4Username;
    ImageView player4WinningIcon;
    ImageView player4LongestPathIcon;
    TextView player4Points;
    TextView player4TrainCards;
    TextView player4DestCards;
    TextView player4TrainCars;

    //Player 5 Views
    TextView player5Username;
    ImageView player5WinningIcon;
    ImageView player5LongestPathIcon;
    TextView player5Points;
    TextView player5TrainCards;
    TextView player5DestCards;
    TextView player5TrainCars;

    //for tracking down each player's views
    List<String> players;
    //Map keys
    public static final String USERNAME = "username";
    public static final String WINNING_ICON = "winning";
    public static final String LONGEST_ICON = "longest";
    public static final String POINTS = "points";
    public static final String TRAIN_CARDS = "trainCards";
    public static final String DEST_CARDS = "destinations";
    public static final String TRAIN_CARS = "trains";

    private static final String CHAT_GAME_HISTORY_DIALOG = "Chat_GameHistoryDialog";

    private LinearLayout layout;

    private PlayerInfoPresenter presenter;


    private View v;


    public PlayerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_player_info, container, false);
        Iconify.with(new FontAwesomeModule());

        initializePlayerViews(v);

        layout = (LinearLayout) v.findViewById(R.id.player_info_fragment_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        this.presenter = new PlayerInfoPresenter(this);

        return v;
    }

    void showDialog()
    {
        FragmentManager manager = getFragmentManager();
        Chat_GameHistoryFragment dialog = new Chat_GameHistoryFragment();
        dialog.show(manager, CHAT_GAME_HISTORY_DIALOG);
    }

    public static PlayerInfoFragment newInstance()
    {
        return new PlayerInfoFragment();
    }

    public void setPlayerTextField(String username, String field, String value)
    {
        switch(players.indexOf(username))
        {
            case 0:
                switch(field)
                {
                    case POINTS:
                        player1Points.setText(value);
                        break;
                    case TRAIN_CARDS:
                        player1TrainCards.setText(value);
                        break;
                    case DEST_CARDS:
                        player1DestCards.setText(value);
                        break;
                    case TRAIN_CARS:
                        player1TrainCars.setText(value);
                        break;
                }
                break;

            case 1:
                switch(field)
                {
                    case POINTS:
                        player2Points.setText(value);
                        break;
                    case TRAIN_CARDS:
                        player2TrainCards.setText(value);
                        break;
                    case DEST_CARDS:
                        player2DestCards.setText(value);
                        break;
                    case TRAIN_CARS:
                        player2TrainCars.setText(value);
                        break;
                }
                break;
            case 2:
                switch(field)
                {
                    case POINTS:
                        player3Points.setText(value);
                        break;
                    case TRAIN_CARDS:
                        player3TrainCards.setText(value);
                        break;
                    case DEST_CARDS:
                        player3DestCards.setText(value);
                        break;
                    case TRAIN_CARS:
                        player3TrainCars.setText(value);
                        break;
                }
                break;
            case 3:
                switch(field)
                {
                    case POINTS:
                        player4Points.setText(value);
                        break;
                    case TRAIN_CARDS:
                        player4TrainCards.setText(value);
                        break;
                    case DEST_CARDS:
                        player4DestCards.setText(value);
                        break;
                    case TRAIN_CARS:
                        player4TrainCars.setText(value);
                        break;
                }
                break;
            case 4:
                switch(field)
                {
                    case POINTS:
                        player5Points.setText(value);
                        break;
                    case TRAIN_CARDS:
                        player5TrainCards.setText(value);
                        break;
                    case DEST_CARDS:
                        player5DestCards.setText(value);
                        break;
                    case TRAIN_CARS:
                        player5TrainCars.setText(value);
                        break;
                }
                break;
        }
    }

    @Override
    public void setPlayerImageField(String username, String field, int visibility) {
        switch (players.indexOf(username))
        {
            case 0:
                switch (field)
                {
                    case WINNING_ICON:
                        player1WinningIcon.setVisibility(visibility);
                        break;
                    case LONGEST_ICON:
                        player1LongestPathIcon.setVisibility(visibility);
                        break;
                }
                break;
            case 1:
                switch (field)
                {
                    case WINNING_ICON:
                        player2WinningIcon.setVisibility(visibility);
                        break;
                    case LONGEST_ICON:
                        player2LongestPathIcon.setVisibility(visibility);
                        break;
                }
                break;
            case 2:
                switch (field)
                {
                    case WINNING_ICON:
                        player3WinningIcon.setVisibility(visibility);
                        break;
                    case LONGEST_ICON:
                        player3LongestPathIcon.setVisibility(visibility);
                        break;
                }
                break;
            case 3:
                switch (field)
                {
                    case WINNING_ICON:
                        player4WinningIcon.setVisibility(visibility);
                        break;
                    case LONGEST_ICON:
                        player4LongestPathIcon.setVisibility(visibility);
                        break;
                }
                break;
            case 4:
                switch (field)
                {
                    case WINNING_ICON:
                        player5WinningIcon.setVisibility(visibility);
                        break;
                    case LONGEST_ICON:
                        player5LongestPathIcon.setVisibility(visibility);
                        break;
                }
                break;


        }
    }

    public void setTurnIndicator(String username, int style)
    {
        switch(players.indexOf(username))
        {
            case 0:
                player1Username.setTypeface(null, style);
                break;
            case 1:
                player2Username.setTypeface(null, style);
                break;
            case 2:
                player3Username.setTypeface(null, style);
                break;
            case 3:
                player4Username.setTypeface(null, style);
                break;
            case 4:
                player5Username.setTypeface(null, style);
                break;
            default:
                System.out.println("too many players");


        }
    }

    private void initializePlayerViews(View v)
    {

        //we know there will be at least 2 players, after that we need to check the number of players

        //PLAYER 1

        Drawable trophyIcon = new IconDrawable(this.getActivity(), FontAwesomeIcons.fa_trophy)
                .colorRes(R.color.colorBlack).sizeDp(20);
        Drawable roadIcon = new IconDrawable(this.getActivity(), FontAwesomeIcons.fa_road)
                .colorRes(R.color.colorBlack).sizeDp(20);

        player1Username = (TextView) v.findViewById(R.id.player1_username);
        player1WinningIcon = (ImageView) v.findViewById(R.id.player1_winningIcon);
        player1WinningIcon.setImageDrawable(trophyIcon);
        player1WinningIcon.setVisibility(View.INVISIBLE);
        player1LongestPathIcon = (ImageView) v.findViewById(R.id.player1_longestPathIcon);
        player1LongestPathIcon.setImageDrawable(roadIcon);
        player1LongestPathIcon.setVisibility(View.INVISIBLE);
        player1Points = (TextView) v.findViewById(R.id.player1_points);
        player1TrainCards = (TextView) v.findViewById(R.id.player1_trainCards);
        player1DestCards = (TextView) v.findViewById(R.id.player1_destCards);
        player1TrainCars = (TextView) v.findViewById(R.id.player1_trainCars);

        //PLAYER 2

        player2Username = (TextView) v.findViewById(R.id.player2_username);
        player2WinningIcon = (ImageView) v.findViewById(R.id.player2_winningIcon);
        player2WinningIcon.setImageDrawable(trophyIcon);
        player2WinningIcon.setVisibility(View.INVISIBLE);
        player2LongestPathIcon = (ImageView) v.findViewById(R.id.player2_longestPathIcon);
        player2LongestPathIcon.setImageDrawable(roadIcon);
        player2LongestPathIcon.setVisibility(View.INVISIBLE);
        player2Points = (TextView) v.findViewById(R.id.player2_points);
        player2TrainCards = (TextView) v.findViewById(R.id.player2_trainCards);
        player2DestCards = (TextView) v.findViewById(R.id.player2_destCards);
        player2TrainCars = (TextView) v.findViewById(R.id.player2_trainCars);

        //PLAYER 3

        player3Username = (TextView) v.findViewById(R.id.player3_username);
        player3WinningIcon = (ImageView) v.findViewById(R.id.player3_winningIcon);
        player3WinningIcon.setImageDrawable(trophyIcon);
        player3WinningIcon.setVisibility(View.INVISIBLE);
        player3LongestPathIcon = (ImageView) v.findViewById(R.id.player3_longestPathIcon);
        player3LongestPathIcon.setImageDrawable(roadIcon);
        player3LongestPathIcon.setVisibility(View.INVISIBLE);
        player3Points = (TextView) v.findViewById(R.id.player3_points);
        player3TrainCards = (TextView) v.findViewById(R.id.player3_trainCards);
        player3DestCards = (TextView) v.findViewById(R.id.player3_destCards);
        player3TrainCars = (TextView) v.findViewById(R.id.player3_trainCars);



        //PLAYER 4

        player4Username = (TextView) v.findViewById(R.id.player4_username);
        player4WinningIcon = (ImageView) v.findViewById(R.id.player4_winningIcon);
        player4WinningIcon.setImageDrawable(trophyIcon);
        player4WinningIcon.setVisibility(View.INVISIBLE);
        player4LongestPathIcon = (ImageView) v.findViewById(R.id.player4_longestPathIcon);
        player4LongestPathIcon.setImageDrawable(roadIcon);
        player4LongestPathIcon.setVisibility(View.INVISIBLE);
        player4Points = (TextView) v.findViewById(R.id.player4_points);
        player4TrainCards = (TextView) v.findViewById(R.id.player4_trainCards);
        player4DestCards = (TextView) v.findViewById(R.id.player4_destCards);
        player4TrainCars = (TextView) v.findViewById(R.id.player4_trainCars);



        //PLAYER 5


        player5Username = (TextView) v.findViewById(R.id.player5_username);
        player5WinningIcon = (ImageView) v.findViewById(R.id.player5_winningIcon);
        player5WinningIcon.setImageDrawable(trophyIcon);
        player5WinningIcon.setVisibility(View.INVISIBLE);
        player5LongestPathIcon = (ImageView) v.findViewById(R.id.player5_longestPathIcon);
        player5LongestPathIcon.setImageDrawable(roadIcon);
        player5LongestPathIcon.setVisibility(View.INVISIBLE);
        player5Points = (TextView) v.findViewById(R.id.player5_points);
        player5TrainCards = (TextView) v.findViewById(R.id.player5_trainCards);
        player5DestCards = (TextView) v.findViewById(R.id.player5_destCards);
        player5TrainCars = (TextView) v.findViewById(R.id.player5_trainCars);



    }

    public void setPlayers(List<String> players) {
        this.players = players;
        //initialize the usernames here so we don't have to do it again unless they change somehow
        //also, this switch statement doesn't have breaks for each case on purpose. This way, it will
        //start with the maximum and work down
        switch(players.size())
        {
            case 5:
                player5Username.setText(players.get(4));
            case 4:
                player4Username.setText(players.get(3));
            case 3:
                player3Username.setText(players.get(2));
            case 2:
                player2Username.setText(players.get(1));
                player1Username.setText(players.get(0));
                break;
            default:
                System.out.println("Error: Not enough players in list.");

        }
    }

    public List<String> getPlayers() {return players;}

}
