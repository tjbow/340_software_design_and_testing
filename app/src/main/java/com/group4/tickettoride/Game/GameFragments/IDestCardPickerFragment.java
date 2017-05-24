package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

import java.util.List;

/**
 * Defines the functions of the destination card picker view
 */
public interface IDestCardPickerFragment
{
    /**
     * Gets the list of selected card IDs
     * @return a list of the selected card IDs
     */
    List<String> getSelectedCardIDs();

    /**
     * Sets the cards in the destination view
     * @param cardIDs the card IDs to be set
     */
    void setCards(List<String> cardIDs);

    /**
     * Sets the confirm button on the view to enabled
     * @param enabled sets whether the button is enabled or disabled
     */
    void setConfirmButtonEnabled(boolean enabled);
}
