package com.group4.tickettoride.Game.GameFragments;

/*
 * Created by Tom on 5/24/2017.
 */

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
}
