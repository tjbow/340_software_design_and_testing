package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;

import java.util.List;

/**
 * Defines the functions of the destination card picker presenter
 */
public interface IDestCardPickerPresenter
{
    /**
     * Adds a destination to the client model
     * @param cards Cards picked in the view
     */
    void addDestination(List<DestinationCard> cards);

    /**
     * Returns the selected destination cards to the deck
     * @param selCards list of selected cards
     */
    void returnSelectedDestCards(List<Integer> selCards);

    /**
     * Draws three new cards from the deck
     * @return a list of destination cards
     */
    List<DestinationCard> drawThreeNewCards();

    /**
     * Get list of destination cards associated with the picker
     * @return list of destination cards
     */
    public List<DestinationCard> getCards();

    /**
     * Sets the players first destination cards selected
     */
    public void setFirstDestCardsSelected();

}
