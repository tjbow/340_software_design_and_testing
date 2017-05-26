package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.DestinationCardDeck;

import java.util.List;

/**
 * Defines the functions of the destination card picker presenter
 */
public interface IDestCardPickerPresenter
{
    /**
     * Adds a destination to the client model
     * @param cardIDs the IDs associated with the cards picked in the view
     */
    void addDestination(List<String> cardIDs);

    /**
     * Draws three new cards from the deck
     * @return a list of destination cards
     */
    List<DestinationCard> drawThreeNewCards();

    /**
     * Returns the cards back to the deck
     * @param cards return destination cards
     */
    void returnDestinationCards(DestinationCardDeck cards);
}
