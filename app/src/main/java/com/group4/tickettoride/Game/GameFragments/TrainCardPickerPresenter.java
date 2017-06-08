package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.ROUTE_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.TrainCardPickerImage;
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
    private Map<CARD_COLOR, TrainCardPickerImage> imageMap;

    public TrainCardPickerPresenter(TrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
        fillMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards());
    }

    private void fillMap(TrainCardDeck playerCards){
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
                if(cardMap.containsKey(CARD_COLOR.PURPLE)) {
                    initializeCards(CARD_COLOR.PURPLE);
                }
                break;
            case GREEN:
                if(cardMap.containsKey(CARD_COLOR.GREEN)) {
                    initializeCards(CARD_COLOR.GREEN);
                }
                break;
            case BLUE:
                if(cardMap.containsKey(CARD_COLOR.BLUE)) {
                    initializeCards(CARD_COLOR.BLUE);
                }
                break;
            case BLACK:
                if(cardMap.containsKey(CARD_COLOR.BLACK)) {
                    initializeCards(CARD_COLOR.BLACK);
                }
                break;
            case YELLOW:
                if(cardMap.containsKey(CARD_COLOR.YELLOW)) {
                    initializeCards(CARD_COLOR.YELLOW);
                }
                break;
            case WHITE:
                if(cardMap.containsKey(CARD_COLOR.WHITE)) {
                    initializeCards(CARD_COLOR.WHITE);
                }
                break;
            case ORANGE:
                if(cardMap.containsKey(CARD_COLOR.ORANGE)) {
                    initializeCards(CARD_COLOR.ORANGE);
                }
                break;
            case RED:
                if(cardMap.containsKey(CARD_COLOR.RED)) {
                    initializeCards(CARD_COLOR.RED);
                }
                break;
            case GRAY:
                for(CARD_COLOR color: cardMap.keySet()){
                    initializeCards(color);
                }
                break;
        }
        if(cardMap.containsKey(CARD_COLOR.RAINBOW)){
            fragment.showLocomotiveCardPicker();
        }
    }

    private void initializeCards(CARD_COLOR color){
        switch (color){
            case PURPLE:
                fragment.showPurpleCardPicker();
                break;
            case GREEN:
                fragment.showGreenCardPicker();
                break;
            case ORANGE:
                fragment.showOrangeCardPicker();
                break;
            case BLUE:
                fragment.showBlueCardPicker();
                break;
            case BLACK:
                fragment.showBlackCardPicker();
                break;
            case RED:
                fragment.showRedCardPicker();
                break;
            case WHITE:
                fragment.showWhiteCardPicker();
                break;
            case YELLOW:
                fragment.showYellowCardPicker();
                break;
        }
    }

    private void setCardVisibilty(CARD_COLOR color){
        switch (color){
            case PURPLE:
                fragment.setPurplePickerEnabled(true);
                break;
            case GREEN:
                fragment.setGreenPickerEnabled(true);
                break;
            case ORANGE:
                fragment.setOrangePickerEnabled(true);
                break;
            case BLUE:
                fragment.setBluePickerEnabled(true);
                break;
            case BLACK:
                fragment.setBlackPickerEnabled(true);
                break;
            case RED:
                fragment.setRedPickerEnabled(true);
                break;
            case WHITE:
                fragment.setWhitePickerEnabled(true);
                break;
            case YELLOW:
                fragment.setYellowPickerEnabled(true);
                break;
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

        if(purpleCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.PURPLE);
        }

        fragment.setPurpleCardCount(Integer.toString(purpleCount));
        //if the player has now selected all the cards they have of a color
        if (purpleCount == cardMap.get(CARD_COLOR.PURPLE))
        {
            fragment.setPurpleCardPlusEnabled(false);
        }
        fragment.setPurpleCardMinusEnabled(true);

        //tell fragment only purple and locomotive should be enabled
    }

    public void onIncrement(CARD_COLOR color){
        if(cardMap.get(color) == imageMap.get(color).getCount()){

        }
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
