package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.ROUTE_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public class TrainCardPickerPresenter implements Observer, ITrainCardPickerPresenter{

    private TrainCardPickerFragment fragment;
    private RouteSegment route;
    private GamePresenter gamePresenter;
    private TrainCardDeck playerCards;

    public TrainCardPickerPresenter(TrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
        this.playerCards = ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards();
    }

    private void setInitialPickers()
    {

        switch (route.getRouteColor())
        {
            case PURPLE:
                fragment.showPurpleCardPicker();

        }
    }

    @Override
    public RouteSegment getSelectedRoute() {
        return route;
    }

    @Override
    public int getCardNumbers() {
        return route.getLength();
    }

    @Override
    public void claimRoute() {
        //gamePresenter.getState().claimRoute(route.getRouteId(), fragment.getSelectedCards());
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void onClaim() {

    }

    @Override
    public void onPurpleIncrement() {

    }

    @Override
    public void onPurpleDecrement() {

    }

    @Override
    public void onWhiteIncrement() {

    }

    @Override
    public void onWhiteDecrement() {

    }

    @Override
    public void onBlueIncrement() {

    }

    @Override
    public void onBlueDecrement() {

    }

    @Override
    public void onYellowIncrement() {

    }

    @Override
    public void onYellowDecrement() {

    }

    @Override
    public void onOrangeIncrement() {

    }

    @Override
    public void onOrangeDecrement() {

    }

    @Override
    public void onBlackIncrement() {

    }

    @Override
    public void onBlackDecrement() {

    }

    @Override
    public void onRedIncrement() {

    }

    @Override
    public void onRedDecrement() {

    }

    @Override
    public void onGreenIncrement() {

    }

    @Override
    public void onGreenDecrement() {

    }

    @Override
    public void onLocomotiveIncrement() {

    }

    @Override
    public void onLocomotiveDecrement() {

    }


}
