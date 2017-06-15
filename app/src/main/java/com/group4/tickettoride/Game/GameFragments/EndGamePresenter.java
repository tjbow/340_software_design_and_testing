package com.group4.tickettoride.Game.GameFragments;


import android.content.Intent;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.PlayerStats;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.Login_Register.Login_RegisterActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;
import com.group4.tickettoride.R;

import java.util.List;

import static com.group4.tickettoride.ClientModel.ClientModel.*;

class EndGamePresenter implements IEndGamePresenter {

    EndGameFragment fragment;
    Game game;

    EndGamePresenter (EndGameFragment fragment)
    {
        this.fragment = fragment;
        fillEndGameTable();
    }


    public void onFinishGame()
    {
        NextLayerFacade.SINGLETON.endGame(game.getGameName());
        ClientModel.SINGLETON.deleteObservers();
        fragment.startActivity(new Intent(fragment.getActivity(), GameListActivity.class));
        fragment.getActivity().finish();
//        ClientFacade.SINGLETON.setUpdateGameInfo(false);
        ClientFacade.SINGLETON.setUpdateGameList(true);
        ClientModel.SINGLETON.endGameClear();
    }

    public void onBackPressed()
    {
        NextLayerFacade.SINGLETON.endGame(game.getGameName());
        ClientModel.SINGLETON.clear();
        ClientModel.SINGLETON.deleteObservers();
        fragment.getActivity().startActivity(new Intent(fragment.getActivity(), Login_RegisterActivity.class));
        ClientFacade.SINGLETON.setUpdateGameInfo(false);
        ClientFacade.SINGLETON.setUpdateGameList(false);
    }

    private void fillEndGameTable()
    {
        game = SINGLETON.getGame();

        fragment.setRanks(game.getPlayers().size());
        List<Player> players = game.getPlayers();
        //ONLY FOR TESTING!!
        //TODO @john: remove this abomination
        //int i = 1;
        for (Player player : players)
        {
            switch (player.getStats().getRank())
            //vvv remove this
            //switch(i)
            {
                case 1:
                    setRank1Info(player);
                    break;
                case 2:
                    setRank2Info(player);
                    break;
                case 3:
                    setRank3Info(player);
                    break;
                case 4:
                    setRank4Info(player);
                    break;
                case 5:
                    setRank5Info(player);
                    break;
                default:
                    break;

            }
            //vvvv REMOVE THIS
            //i++;
        }

    }

    private void setRank1Info(Player playerInfo)
    {
        PlayerStats stats = playerInfo.getStats();
        fragment.setRank1Username(playerInfo.getUserName());
        fragment.setRank1RoutesClaimedPoints(Integer.toString(stats.getClaimedRouteScore()));
        fragment.setRank1DestinationsPositivePoints(Integer.toString(stats.getCompletedDestinationCards()));
        fragment.setRank1DestinationsNegativePoints(Integer.toString(stats.getIncompleteDestinationCards()));
        fragment.setRank1LongestPathPoints(Integer.toString(stats.getLongestPathScore()));
        fragment.setRank1TotalPoints(Integer.toString(stats.getTotalScore()));
        fragment.setRank1UsernameColor( getColorStringAsInt( playerInfo.getColor() ) );

    }

    private void setRank2Info(Player playerInfo)
    {
        PlayerStats stats = playerInfo.getStats();
        fragment.setRank2Username(playerInfo.getUserName());
        fragment.setRank2RoutesClaimedPoints(Integer.toString(stats.getClaimedRouteScore()));
        fragment.setRank2DestinationsPositivePoints(Integer.toString(stats.getCompletedDestinationCards()));
        fragment.setRank2DestinationsNegativePoints(Integer.toString(stats.getIncompleteDestinationCards()));
        fragment.setRank2LongestPathPoints(Integer.toString(stats.getLongestPathScore()));
        fragment.setRank2TotalPoints(Integer.toString(stats.getTotalScore()));
        fragment.setRank2UsernameColor( getColorStringAsInt( playerInfo.getColor() ) );
    }

    private void setRank3Info(Player playerInfo)
    {
        PlayerStats stats = playerInfo.getStats();
        fragment.setRank3Username(playerInfo.getUserName());
        fragment.setRank3RoutesClaimedPoints(Integer.toString(stats.getClaimedRouteScore()));
        fragment.setRank3DestinationsPositivePoints(Integer.toString(stats.getCompletedDestinationCards()));
        fragment.setRank3DestinationsNegativePoints(Integer.toString(stats.getIncompleteDestinationCards()));
        fragment.setRank3LongestPathPoints(Integer.toString(stats.getLongestPathScore()));
        fragment.setRank3TotalPoints(Integer.toString(stats.getTotalScore()));
        fragment.setRank3UsernameColor( getColorStringAsInt( playerInfo.getColor() ) );
    }

    private void setRank4Info(Player playerInfo)
    {
        PlayerStats stats = playerInfo.getStats();
        fragment.setRank4Username(playerInfo.getUserName());
        fragment.setRank4RoutesClaimedPoints(Integer.toString(stats.getClaimedRouteScore()));
        fragment.setRank4DestinationsPositivePoints(Integer.toString(stats.getCompletedDestinationCards()));
        fragment.setRank4DestinationsNegativePoints(Integer.toString(stats.getIncompleteDestinationCards()));
        fragment.setRank4LongestPathPoints(Integer.toString(stats.getLongestPathScore()));
        fragment.setRank4TotalPoints(Integer.toString(stats.getTotalScore()));
        fragment.setRank4UsernameColor( getColorStringAsInt( playerInfo.getColor() ) );
    }

    private void setRank5Info(Player playerInfo)
    {
        PlayerStats stats = playerInfo.getStats();
        fragment.setRank5Username(playerInfo.getUserName());
        fragment.setRank5RoutesClaimedPoints(Integer.toString(stats.getClaimedRouteScore()));
        fragment.setRank5DestinationsPositivePoints(Integer.toString(stats.getCompletedDestinationCards()));
        fragment.setRank5DestinationsNegativePoints(Integer.toString(stats.getIncompleteDestinationCards()));
        fragment.setRank5LongestPathPoints(Integer.toString(stats.getLongestPathScore()));
        fragment.setRank5TotalPoints(Integer.toString(stats.getTotalScore()));
        fragment.setRank5UsernameColor( getColorStringAsInt( playerInfo.getColor() ) );
    }

    private int getColorStringAsInt(String color)
    {
        switch (color)
        {
            case Player.BLUE:
                return R.color.colorBlue;
            case Player.RED:
                return R.color.colorRed;
            case Player.GREEN:
                return R.color.colorGreen;
            case Player.YELLOW:
                return R.color.colorYellow;
            case Player.BLACK:
                return R.color.colorBlack;
            default:
                return -1;

        }
    }
}
