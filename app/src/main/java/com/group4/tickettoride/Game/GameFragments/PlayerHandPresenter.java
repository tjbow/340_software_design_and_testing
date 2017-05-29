package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Deck.PlayerHand;
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
    }

    @Override
    public void updatePlayerHand(PlayerHand hand)
    {
        fragment.updateDestinationCards(hand.getDestinationCards().getDestDeck());
        fragment.updateTrainCards(hand.getTrainCards().getCardDeck());
    }

    @Override
    public PlayerHand getPlayerHandCards()
    {
        return player.getPlayerHand();
    }

    @Override
    public void testAction()
    {
        ClientModel.SINGLETON.testActions();
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
        else if(arg.getClass() == PlayerHand.class)
        {
            PlayerHand playerHand = (PlayerHand) arg;
            updatePlayerHand(playerHand);
        }
    }
}
