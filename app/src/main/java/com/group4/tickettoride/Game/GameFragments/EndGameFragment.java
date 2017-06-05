package com.group4.tickettoride.Game.GameFragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.tickettoride.GameList.GameListPresenter;
import com.group4.tickettoride.GameList.IGameListPresenter;
import com.group4.tickettoride.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGameFragment extends DialogFragment{

    //rank1 Views
    private TextView rank1Username;
    private TextView rank1RoutesClaimedPoints;
    private TextView rank1DestinationsPositivePoints;
    private TextView rank1DestinationsNegativePoints;
    private TextView rank1LongestPathPoints;
    private TextView rank1TotalPoints;
    private ImageView rank1WinnerIcon;

    //rank4 Views
    private TextView rank2Username;
    private TextView rank2RoutesClaimedPoints;
    private TextView rank2DestinationsPositivePoints;
    private TextView rank2DestinationsNegativePoints;
    private TextView rank2LongestPathPoints;
    private TextView rank2TotalPoints;
    private ImageView rank2WinnerIcon;

    //rank3 Views
    private TextView rank3Username;
    private TextView rank3RoutesClaimedPoints;
    private TextView rank3DestinationsPositivePoints;
    private TextView rank3DestinationsNegativePoints;
    private TextView rank3LongestPathPoints;
    private TextView rank3TotalPoints;
    private ImageView rank3WinnerIcon;

    //rank4 Views
    private TextView rank4Username;
    private TextView rank4RoutesClaimedPoints;
    private TextView rank4DestinationsPositivePoints;
    private TextView rank4DestinationsNegativePoints;
    private TextView rank4LongestPathPoints;
    private TextView rank4TotalPoints;
    private ImageView rank4WinnerIcon;

    //rank5 Views
    private TextView rank5Username;
    private TextView rank5RoutesClaimedPoints;
    private TextView rank5DestinationsPositivePoints;
    private TextView rank5DestinationsNegativePoints;
    private TextView rank5LongestPathPoints;
    private TextView rank5TotalPoints;
    private ImageView rank5WinnerIcon;



    private Dialog dialog;
    private IEndGamePresenter presenter;
    private View v;

    public EndGameFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_end_game, null);

        Iconify.with(new FontAwesomeModule());
        initializeViews();
        this.presenter = new EndGamePresenter(this);


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(v);
        dialogBuilder.setTitle(R.string.game_over);
        dialogBuilder.setPositiveButton(R.string.finish_game, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onFinishGame();
            }
        });

        dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return true;
            }
        });

        return dialog;
    }

    /**
    * since we don't want to set the columns for more than the number of players, this function is given
    *  the number of players in the game so the correct number of rows in the table can be prepared
    *
     * @param numPlayers the number of players in the game
    */
    public void setRanks(int numPlayers)
    {
        switch (numPlayers)
        {
            case 5:
                TextView rank5Num = (TextView) v.findViewById(R.id.rank5_rank);
                rank5Num.setText(R.string.rank5);
            case 4:
                TextView rank4Num = (TextView) v.findViewById(R.id.rank4_rank);
                rank4Num.setText(R.string.rank4);
            case 3:
                TextView rank3Num = (TextView) v.findViewById(R.id.rank3_rank);
                rank3Num.setText(R.string.rank3);
            case 2:
                TextView rank2Num = (TextView) v.findViewById(R.id.rank2_rank);
                rank2Num.setText(R.string.rank2);

                TextView rank1Num = (TextView) v.findViewById(R.id.rank1_rank);
                rank1Num.setText(R.string.rank1);
                break;
            default:
                break;
        }
    }

    public void initializeViews()
    {
        Drawable trophyIcon = new IconDrawable(this.getActivity(), FontAwesomeIcons.fa_trophy)
                .colorRes(R.color.colorBlack).sizeDp(20);

        //rank1 Views
        rank1WinnerIcon = (ImageView) v.findViewById(R.id.winnerIcon);
        rank1WinnerIcon.setImageDrawable(trophyIcon);
        rank1Username = (TextView) v.findViewById(R.id.rank1_username);
        rank1RoutesClaimedPoints = (TextView) v.findViewById(R.id.rank1_rcPts);
        rank1DestinationsPositivePoints = (TextView) v.findViewById(R.id.rank1_destPosPts);
        rank1DestinationsNegativePoints = (TextView) v.findViewById(R.id.rank1_destNegPts);
        rank1LongestPathPoints = (TextView) v.findViewById(R.id.rank1_longPathPts);
        rank1TotalPoints = (TextView) v.findViewById(R.id.rank1_total);

        //rank2 Views
        rank2Username = (TextView) v.findViewById(R.id.rank2_username);
        rank2RoutesClaimedPoints = (TextView) v.findViewById(R.id.rank2_rcPts);
        rank2DestinationsPositivePoints = (TextView) v.findViewById(R.id.rank2_destPosPts);
        rank2DestinationsNegativePoints = (TextView) v.findViewById(R.id.rank2_destNegPts);
        rank2LongestPathPoints = (TextView) v.findViewById(R.id.rank2_longPathPts);
        rank2TotalPoints = (TextView) v.findViewById(R.id.rank2_total);

        //rank3 Views
        rank3Username = (TextView) v.findViewById(R.id.rank3_username);
        rank3RoutesClaimedPoints = (TextView) v.findViewById(R.id.rank3_rcPts);
        rank3DestinationsPositivePoints = (TextView) v.findViewById(R.id.rank3_destPosPts);
        rank3DestinationsNegativePoints = (TextView) v.findViewById(R.id.rank3_destNegPts);
        rank3LongestPathPoints = (TextView) v.findViewById(R.id.rank3_longPathPts);
        rank3TotalPoints = (TextView) v.findViewById(R.id.rank3_total);

        //rank4 Views
        rank4Username = (TextView) v.findViewById(R.id.rank4_username);
        rank4RoutesClaimedPoints = (TextView) v.findViewById(R.id.rank4_rcPts);
        rank4DestinationsPositivePoints = (TextView) v.findViewById(R.id.rank4_destPosPts);
        rank4DestinationsNegativePoints = (TextView) v.findViewById(R.id.rank4_destNegPts);
        rank4LongestPathPoints = (TextView) v.findViewById(R.id.rank4_longPathPts);
        rank4TotalPoints = (TextView) v.findViewById(R.id.rank4_total);

        //rank5 Views
        rank5Username = (TextView) v.findViewById(R.id.rank5_username);
        rank5RoutesClaimedPoints = (TextView) v.findViewById(R.id.rank5_rcPts);
        rank5DestinationsPositivePoints = (TextView) v.findViewById(R.id.rank5_destPosPts);
        rank5DestinationsNegativePoints = (TextView) v.findViewById(R.id.rank5_destNegPts);
        rank5LongestPathPoints = (TextView) v.findViewById(R.id.rank5_longPathPts);
        rank5TotalPoints = (TextView) v.findViewById(R.id.rank5_total);

    }

    //Rank 1 -----------------------------------------------------------------------------
    public void setRank1Username(String rank1Username) {
        this.rank1Username.setText(rank1Username);
    }

    public void setRank1RoutesClaimedPoints(String rank1RoutesClaimedPoints) {
        this.rank1RoutesClaimedPoints.setText(rank1RoutesClaimedPoints);
    }

    public void setRank1DestinationsPositivePoints(String rank1DestinationsPositivePoints) {
        this.rank1DestinationsPositivePoints.setText(rank1DestinationsPositivePoints);
    }

    public void setRank1DestinationsNegativePoints(String rank1DestinationsNegativePoints) {
        this.rank1DestinationsNegativePoints.setText(rank1DestinationsNegativePoints);
    }

    public void setRank1LongestPathPoints(String rank1LongestPathPoints) {
        this.rank1LongestPathPoints.setText(rank1LongestPathPoints);
    }

    public void setRank1TotalPoints(String rank1TotalPoints) {
        this.rank1TotalPoints.setText(rank1TotalPoints);
    }

    //Rank 2 -----------------------------------------------------------------------------
    public void setRank2Username(String rank2Username) {
        this.rank2Username.setText(rank2Username);
    }

    public void setRank2RoutesClaimedPoints(String rank2RoutesClaimedPoints) {
        this.rank2RoutesClaimedPoints.setText(rank2RoutesClaimedPoints);
    }

    public void setRank2DestinationsPositivePoints(String rank2DestinationsPositivePoints) {
        this.rank2DestinationsPositivePoints.setText(rank2DestinationsPositivePoints);
    }

    public void setRank2DestinationsNegativePoints(String rank2DestinationsNegativePoints) {
        this.rank2DestinationsNegativePoints.setText(rank2DestinationsNegativePoints);
    }

    public void setRank2LongestPathPoints(String rank2LongestPathPoints) {
        this.rank2LongestPathPoints.setText(rank2LongestPathPoints);
    }

    public void setRank2TotalPoints(String rank2TotalPoints) {
        this.rank2TotalPoints.setText(rank2TotalPoints);
    }

    //Rank 3 -----------------------------------------------------------------------------
    public void setRank3Username(String rank3Username) {
        this.rank3Username.setText(rank3Username);
    }

    public void setRank3RoutesClaimedPoints(String rank3RoutesClaimedPoints) {
        this.rank3RoutesClaimedPoints.setText(rank3RoutesClaimedPoints);
    }

    public void setRank3DestinationsPositivePoints(String rank3DestinationsPositivePoints) {
        this.rank3DestinationsPositivePoints.setText(rank3DestinationsPositivePoints);
    }

    public void setRank3DestinationsNegativePoints(String rank3DestinationsNegativePoints) {
        this.rank3DestinationsNegativePoints.setText(rank3DestinationsNegativePoints);
    }

    public void setRank3LongestPathPoints(String rank3LongestPathPoints) {
        this.rank3LongestPathPoints.setText(rank3LongestPathPoints);
    }

    public void setRank3TotalPoints(String rank3TotalPoints) {
        this.rank3TotalPoints.setText(rank3TotalPoints);
    }

    //Rank 4 -----------------------------------------------------------------------------
    public void setRank4Username(String rank4Username) {
        this.rank4Username.setText(rank4Username);
    }

    public void setRank4RoutesClaimedPoints(String rank4RoutesClaimedPoints) {
        this.rank4RoutesClaimedPoints.setText(rank4RoutesClaimedPoints);
    }

    public void setRank4DestinationsPositivePoints(String rank4DestinationsPositivePoints) {
        this.rank4DestinationsPositivePoints.setText(rank4DestinationsPositivePoints);
    }

    public void setRank4DestinationsNegativePoints(String rank4DestinationsNegativePoints) {
        this.rank4DestinationsNegativePoints.setText(rank4DestinationsNegativePoints);
    }

    public void setRank4LongestPathPoints(String rank4LongestPathPoints) {
        this.rank4LongestPathPoints.setText(rank4LongestPathPoints);
    }

    public void setRank4TotalPoints(String rank4TotalPoints) {
        this.rank4TotalPoints.setText(rank4TotalPoints);
    }

    //Rank 5 -----------------------------------------------------------------------------
    public void setRank5Username(String rank5Username) {
        this.rank5Username.setText(rank5Username);
    }

    public void setRank5RoutesClaimedPoints(String rank5RoutesClaimedPoints) {
        this.rank5RoutesClaimedPoints.setText(rank5RoutesClaimedPoints);
    }

    public void setRank5DestinationsPositivePoints(String rank5DestinationsPositivePoints) {
        this.rank5DestinationsPositivePoints.setText(rank5DestinationsPositivePoints);
    }

    public void setRank5DestinationsNegativePoints(String rank5DestinationsNegativePoints) {
        this.rank5DestinationsNegativePoints.setText(rank5DestinationsNegativePoints);
    }

    public void setRank5LongestPathPoints(String rank5LongestPathPoints) {
        this.rank5LongestPathPoints.setText(rank5LongestPathPoints);
    }

    public void setRank5TotalPoints(String rank5TotalPoints) {
        this.rank5TotalPoints.setText(rank5TotalPoints);
    }

}
