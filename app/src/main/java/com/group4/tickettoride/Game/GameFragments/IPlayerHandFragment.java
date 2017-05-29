package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;

import java.util.List;

/**
 * Defines the functions of the player hand view
 */
public interface IPlayerHandFragment
{
    /**
     * Adds the train cards to the players hand in the view
     * @param cards the list of cards to be added
     */
    void updateTrainCards(List<TrainCard> cards);

    /**
     * Removes the train cards from the players hand in the view
     * @param cards he list of cards to be removed
     */
//    void removedTrainCards(List<TrainCard> cards);

    /**
     * Adds the destination to the players list of destinations in the view
     * @param cards the destination card to be added
     */
    void updateDestinationCards(List<DestinationCard> cards);
}
