package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Map.RouteSegment;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public interface ITrainCardPickerPresenter {

    RouteSegment getSelectedRoute();

    int getCardNumbers();

    void claimRoute();

    /**
     * this function is called when a user has clicked "claim"
     */
    void onClaim();

    void onPurpleIncrement();

    void onPurpleDecrement();

    void onWhiteIncrement();

    void onWhiteDecrement();

    void onBlueIncrement();

    void onBlueDecrement();

    void onYellowIncrement();

    void onYellowDecrement();

    void onOrangeIncrement();

    void onOrangeDecrement();

    void onBlackIncrement();

    void onBlackDecrement();

    void onRedIncrement();

    void onRedDecrement();

    void onGreenIncrement();

    void onGreenDecrement();

    void onLocomotiveIncrement();

    void onLocomotiveDecrement();

    void afterFragmentCreation();

    void onIncrement(CARD_COLOR color);

    void onDecrement(CARD_COLOR color);

}
