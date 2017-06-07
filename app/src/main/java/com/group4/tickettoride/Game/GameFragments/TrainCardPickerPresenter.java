package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public class TrainCardPickerPresenter implements Observer, ITrainCardPickerPresenter{

    private ITrainCardPickerFragment fragment;
    private RouteSegment route;
    private GamePresenter gamePresenter;

    public TrainCardPickerPresenter(ITrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
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
        gamePresenter.getState().claimRoute(route.getRouteId(), fragment.getSelectedCards());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
