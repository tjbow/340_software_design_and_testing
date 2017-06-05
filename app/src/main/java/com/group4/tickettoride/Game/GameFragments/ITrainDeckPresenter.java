package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;

import java.util.List;

/**
 * Defines the functions of the train deck presenter
 */
public interface ITrainDeckPresenter
{
    /**
     * Draws the numth card from the face up cards
     * @param num selects the particular face up card to draw (0th through 4th)
     * @return the selected train card
     */
    void drawFaceUpCard(int num);

    /**
     * Draws a train card from the face down deck
     * @return the drawn train card is returned from the Server Side
     */
    void drawTrainCard();

    /**
     * Draws destination cards from destination card deck
     * @return destination cards are returned from the Server Side
     */
    void drawDestCards();

    /**
     * Gets the face up cards in the deck in the model
     * @return face up cards
     */
    List<TrainCard> getFaceUpCards();

    /**
     * Gets the number of train deck cards remaining
     * @return the number of train deck cards remaining
     */
    int getTrainDeckCardsRemaining();

    /**
     * Gets the number of destination deck cards remaining
     * @return the number of destination deck cards remaining
     */
    int getDestDeckCardsRemaining();

}
