package com.group4.tickettoride.Game.GameFragments;


import android.content.Intent;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.GameList.GameListActivity;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

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
//        NextLayerFacade.SINGLETON.endGame(game.getGameName());
//        fragment.startActivity(new Intent(fragment.getActivity(), GameListActivity.class));
//        fragment.getActivity().finish();
    }

    private void fillEndGameTable()
    {
        game = SINGLETON.getGame();

        fragment.setRanks(game.getPlayerCount());
        List<Player> players = game.getPlayers();
        //TODO @john: fix this hardcoded method to be more reactive
        fragment.setRank1Username(players.get(0).getUserName());
        fragment.setRank1RoutesClaimedPoints(Integer.toString(players.get(0).getScore()));
        fragment.setRank1DestinationsPositivePoints("50");
        fragment.setRank1DestinationsNegativePoints("10");
        fragment.setRank1LongestPathPoints("10");
        fragment.setRank1TotalPoints("the most");
        fragment.setRank2Username(players.get(1).getUserName());
        fragment.setRank2RoutesClaimedPoints(Integer.toString(players.get(1).getScore()));
        fragment.setRank2DestinationsPositivePoints("50");
        fragment.setRank2DestinationsNegativePoints("10");
        fragment.setRank2LongestPathPoints("0");
        fragment.setRank2TotalPoints("some");
        fragment.setRank3Username("dummy");
        fragment.setRank3RoutesClaimedPoints("--");
        fragment.setRank3DestinationsPositivePoints("50");
        fragment.setRank3DestinationsNegativePoints("10");
        fragment.setRank3LongestPathPoints("0");
        fragment.setRank3TotalPoints("some");
        fragment.setRank4Username("dummy");
        fragment.setRank4RoutesClaimedPoints("--");
        fragment.setRank4DestinationsPositivePoints("50");
        fragment.setRank4DestinationsNegativePoints("0");
        fragment.setRank4LongestPathPoints("10");
        fragment.setRank4TotalPoints("some");
        fragment.setRank5Username("dummy");
        fragment.setRank5RoutesClaimedPoints("--");
        fragment.setRank5DestinationsPositivePoints("50");
        fragment.setRank5DestinationsNegativePoints("0");
        fragment.setRank5LongestPathPoints("10");
        fragment.setRank5TotalPoints("the least");
    }
}
