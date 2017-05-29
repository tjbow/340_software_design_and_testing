package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.Deck.TrainCard;

import java.util.List;

/**
 * Defines the functions of the train deck view
 */
public interface ITrainDeckFragment
{
    /**
     * Sets the face up cards in the view
     * @param cards the list of face up cards
     */
    void setFaceUpCards(List<TrainCard> cards);

    /**
     * Sets the number of cards remaining to be displayed for the train card deck
     * @param numRemain number of cards remaining
     */
    void setTrainDeckCardsRemaining(int numRemain);

    /**
     * Sets the number of cards remaining to be displayed for the destination card deck
     * @param numRemain number of cards remaining
     */
    void setDestCardsRemaining(int numRemain);
}
