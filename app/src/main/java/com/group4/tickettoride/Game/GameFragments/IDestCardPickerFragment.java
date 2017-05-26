package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import com.group4.shared.Model.DestinationCard;

import java.util.List;

/**
 * Defines the functions of the destination card picker view
 */
public interface IDestCardPickerFragment
{
    /**
     * Sets the minimum number of cards selected
     * @param num number of cards selected
     */
    void setMinNumSelected(int num);

    /**
     * Gets the list of selected cards
     * @return a list of the selected card IDs
     */
    List<Integer> getSelectedCards();

    /**
     * Sets the cards in the destination view
     * @param cards the card to be set
     */
    void setCards(List<DestinationCard> cards);

    /**
     * Sets the confirm button on the view to enabled
     * @param enabled sets whether the button is enabled or disabled
     */
    void setConfirmButtonEnabled(boolean enabled);
}
