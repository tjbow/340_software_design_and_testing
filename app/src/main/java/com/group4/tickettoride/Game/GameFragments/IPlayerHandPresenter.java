package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.DestinationCardDeck;
import com.group4.shared.Model.TrainCardDeck;

/**
 * Defines the functions of the player hand presenter
 */
public interface IPlayerHandPresenter
{
    void addTrainCards(TrainCardDeck playerTrainCardDeck);

    void addDestCards(DestinationCardDeck playerDestCardDeck);
}
