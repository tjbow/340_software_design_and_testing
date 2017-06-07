package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Map.RouteSegment;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public interface ITrainCardPickerPresenter {

    RouteSegment getSelectedRoute();

    int getCardNumbers();

    void claimRoute();

}
