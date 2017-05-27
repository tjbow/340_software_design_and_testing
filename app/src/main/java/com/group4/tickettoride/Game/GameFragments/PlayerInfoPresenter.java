package com.group4.tickettoride.Game.GameFragments;


import android.view.View;

import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


import static android.graphics.Typeface.BOLD_ITALIC;
import static android.graphics.Typeface.NORMAL;
import static com.group4.tickettoride.Game.GameFragments.PlayerInfoFragment.*;

public class PlayerInfoPresenter implements Observer {


    private PlayerInfoFragment fragment;
    private List<Player> players;

    public PlayerInfoPresenter(PlayerInfoFragment fragment)
    {
        this.fragment = fragment;
        this.players = ClientModel.SINGLETON.getGame().getPlayers();
        fragment.setPlayers(getPlayerUsernames(this.players));
        updateFragment(players);
        ClientModel.SINGLETON.addObserver(this);
    }

    @Override
    public void update(Observable o, final Object arg) {
        if(arg.getClass() == String.class)
        {
            //nothing for now
        }
        else if(arg.getClass() == Game.class)
        {
//            updateFragment(((Game) arg).getPlayers());
        }
        else if (arg.getClass() == ArrayList.class)
        {
            ArrayList<Player> players = (ArrayList<Player>) arg;
            updateFragment(players);
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

        for (Player player : players)
        {
            fragment.setPlayerTextField(player.getUserName(), POINTS, Integer.toString(player.getScore()));
            fragment.setPlayerTextField(player.getUserName(), TRAIN_CARDS, Integer.toString(player.getPlayerHand().getTrainCardCount()));
            fragment.setPlayerTextField(player.getUserName(), DEST_CARDS, Integer.toString(player.getPlayerHand().getDestinationCardCount()));
            fragment.setPlayerTextField(player.getUserName(), TRAIN_CARS, Integer.toString(player.getTrainCarsRemaining()));
            fragment.setPlayerImageField(player.getUserName(), WINNING_ICON, player.isWinning() ?
                    View.VISIBLE : View.INVISIBLE);
            fragment.setPlayerImageField(player.getUserName(), LONGEST_ICON, player.isLongestPath() ?
                    View.VISIBLE : View.INVISIBLE);
            fragment.setTurnIndicator(player.getUserName(), player.isTurn() ? BOLD_ITALIC : NORMAL);
        }
    }


}
