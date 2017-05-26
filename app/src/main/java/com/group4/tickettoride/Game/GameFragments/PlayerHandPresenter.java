package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.DestinationCardDeck;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.TrainCardDeck;
import com.group4.tickettoride.ClientModel.ClientModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by tyler on 5/25/17.
 */

public class PlayerHandPresenter implements Observer, IPlayerHandPresenter
{
    private IPlayerHandFragment fragment;
    private Player player;

    public PlayerHandPresenter(IPlayerHandFragment fragment)
    {
        this.fragment = fragment;
        player = ClientModel.SINGLETON.getPlayer();
        ClientModel.SINGLETON.addObserver(this);
        fragment.updateTrainCards(player.getTrainCardDeck().getCardDeck());
    }

    @Override
    public void addTrainCards(TrainCardDeck playerTrainCardDeck)
    {
        fragment.updateTrainCards(playerTrainCardDeck.getCardDeck());
    }

    @Override
    public void addDestCards(DestinationCardDeck playerDestCardDeck)
    {
        fragment.updateDestinationCards(playerDestCardDeck.getDestDeck());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (arg.getClass() == Game.class)
        {
            //
        }
        else if (arg.getClass() == String.class)
        {
            // nothing for now
        }
        else if (arg.getClass() == Boolean.class)
        {
            //
        }
    }
}
