package com.group4.tickettoride.Game.GameFragments;


import android.view.View;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientFacade;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.BOLD_ITALIC;
import static android.graphics.Typeface.NORMAL;
import static com.group4.tickettoride.Game.GameFragments.PlayerInfoFragment.*;

public class PlayerInfoPresenter implements Observer {


    private PlayerInfoFragment fragment;

    public PlayerInfoPresenter(PlayerInfoFragment fragment)
    {
        this.fragment = fragment;
    }

    @Override
    public void update(Observable o, final Object arg) {
        if(arg.getClass() == String.class)
        {
            //nothing for now
        }
        else if(arg.getClass() == Game.class)
        {
            updateFragment(((Game) arg).getPlayerList());
        }
        else if (arg.getClass() == List.class)
        {
            updateFragment((List)arg);
        }
    }

    private List<String> getPlayerUsernames(List<Player> players)
    {
        List<String> playerUsernames = new ArrayList<>();
        for (Player player : players)
        {
            playerUsernames.add(player.getUserName());
        }

        return playerUsernames;
    }

    private void updateFragment(List<Player> players)
    {
        setPlayerInfo(players);
        //TODO @john: add other updates here
    }

    private void setPlayerInfo(List<Player> players)
    {
        if (!fragment.isInitialized())
        {
            fragment.setPlayers(getPlayerUsernames(players));
        }

        for (Player player : players)
        {
            fragment.setPlayerTextField(player.getUserName(), POINTS, player.getScore());
            fragment.setPlayerTextField(player.getUserName(), TRAIN_CARDS, player.getTrainCardCount());
            fragment.setPlayerTextField(player.getUserName(), DEST_CARDS, player.getDestinationCardCount());
            fragment.setPlayerTextField(player.getUserName(), TRAIN_CARS, player.getTrainCarsRemaining());
            fragment.setPlayerImageField(player.getUserName(), WINNING_ICON, player.isWinning() ?
                    View.VISIBLE : View.INVISIBLE);
            fragment.setPlayerImageField(player.getUserName(), LONGEST_ICON, player.isLongestPath() ?
                    View.VISIBLE : View.INVISIBLE);
            fragment.setTurnIndicator(player.getUserName(), player.isTurn() ? BOLD_ITALIC : NORMAL);
        }
    }


}
