package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.ROUTE_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public class TrainCardPickerPresenter implements Observer, ITrainCardPickerPresenter{

    private TrainCardPickerFragment fragment;
    private RouteSegment route;
    private GamePresenter gamePresenter;
    private Map<CARD_COLOR, Integer> cardMap;

    public TrainCardPickerPresenter(TrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
        fillMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards());
    }

    public void fillMap(TrainCardDeck playerCards){
        cardMap = new HashMap<>();
        List<TrainCard> playerHand = playerCards.getCardDeck();
        for(int i = 0; i < playerHand.size(); i++){
            if(cardMap.containsKey(playerHand.get(i).getColor())){
                cardMap.put(playerHand.get(i).getColor(), cardMap.get(playerHand.get(i).getColor()) + 1);
            }
            else{
                cardMap.put(playerHand.get(i).getColor(), 1);
            }
        }
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

        int purpleCount = fragment.getPurpleCardCount();
        purpleCount++;
        fragment.setPurpleCardCount(Integer.toString(purpleCount));
        //if the player has now selected all the cards they have of a color
        if (purpleCount == cardMap.get(CARD_COLOR.PURPLE))
        {
            fragment.setPurpleCardPlusEnabled(false);
        }
        fragment.setPurpleCardMinusEnabled(true);

        //tell fragment only purple and locomotive should be enabled
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
