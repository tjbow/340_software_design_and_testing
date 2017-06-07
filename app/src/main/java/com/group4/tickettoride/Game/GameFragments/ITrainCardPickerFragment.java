package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Map.RouteSegment;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public interface ITrainCardPickerFragment {

    void setColor(CARD_COLOR color);

    void setCardNumber(int number);

    void setSelectedRoute(RouteSegment route);

    List<TrainCard> getSelectedCards();

}
