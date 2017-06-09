package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.BlackTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.BlueTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.GreenTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.LocomotiveTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.OrangeTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.PurpleTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.RedTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.TrainCardPickerImage;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.WhiteTrainCardPicker;
import com.group4.tickettoride.Game.GameFragments.TrainCardPickerImages.YellowTrainCardPicker;
import com.group4.tickettoride.Game.GamePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.group4.shared.Model.Deck.CARD_COLOR.RAINBOW;

/**
 * Created by Russell Fitzpatrick on 6/8/2017.
 */

public class TrainCardPickerPresenter2 implements ITrainCardPickerPresenter{

    private TrainCardPickerFragment fragment;
    private RouteSegment route;
    private GamePresenter gamePresenter;
    private Map<CARD_COLOR, Integer> cardMap;
    private Map<CARD_COLOR, ArrayList<TrainCard>> playerCards;
    private Map<CARD_COLOR, TrainCardPickerImage> imageMap = new HashMap<>();

    public TrainCardPickerPresenter2(TrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
        createPlayerCardsMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards().getCardDeck());
        fillMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards());

    }

    private void createPlayerCardsMap(List<TrainCard> cards)
    {
        playerCards = new HashMap<>();
        for (CARD_COLOR color : CARD_COLOR.values())
        {
            playerCards.put(color, new ArrayList<TrainCard>());
        }

        for (TrainCard card : cards)
        {
            playerCards.get(card.getColor()).add(card);
        }
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

    private void setRouteInfo()
    {
        String routeTitle = route.getCityA() + " - " + route.getCityB();
        fragment.setRouteTitle(routeTitle);

        String routeRequirements = route.getLength() + " ";
        String color = "not caught by switch";
        switch (route.getRouteColor())
        {
            case PINK:
                color = "Purple";
                break;
            case WHITE:
                color = "White";
                break;
            case BLUE:
                color = "Blue";
                break;
            case YELLOW:
                color = "Yellow";
                break;
            case ORANGE:
                color = "Orange";
                break;
            case BLACK:
                color = "Black";
                break;
            case RED:
                color = "Red";
                break;
            case GREEN:
                color = "Green";
                break;
            case GRAY:
                color = "Any color";
                break;
        }
        routeRequirements += color;
        fragment.setRequiredRouteCards(routeRequirements);
    }

    @Override
    public RouteSegment getSelectedRoute() {
        return null;
    }

    @Override
    public int getCardNumbers() {
        return 0;
    }

    @Override
    public void claimRoute() {}

    @Override
    public void onClaim() {
        gamePresenter.getState().claimRoute(route.getRouteId(), claim());
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

    @Override
    public void afterFragmentCreation() {
        setRouteInfo();
        initializePickers();
    }

    private void initializePickers(){
        switch (route.getRouteColor())
        {
            case PINK:
                if(cardMap.containsKey(CARD_COLOR.PURPLE)) {
                    showCards(CARD_COLOR.PURPLE);
                }
                break;
            case GREEN:
                if(cardMap.containsKey(CARD_COLOR.GREEN)) {
                    showCards(CARD_COLOR.GREEN);
                }
                break;
            case BLUE:
                if(cardMap.containsKey(CARD_COLOR.BLUE)) {
                    showCards(CARD_COLOR.BLUE);
                }
                break;
            case BLACK:
                if(cardMap.containsKey(CARD_COLOR.BLACK)) {
                    showCards(CARD_COLOR.BLACK);
                }
                break;
            case YELLOW:
                if(cardMap.containsKey(CARD_COLOR.YELLOW)) {
                    showCards(CARD_COLOR.YELLOW);
                }
                break;
            case WHITE:
                if(cardMap.containsKey(CARD_COLOR.WHITE)) {
                    showCards(CARD_COLOR.WHITE);
                }
                break;
            case ORANGE:
                if(cardMap.containsKey(CARD_COLOR.ORANGE)) {
                    showCards(CARD_COLOR.ORANGE);
                }
                break;
            case RED:
                if(cardMap.containsKey(CARD_COLOR.RED)) {
                    showCards(CARD_COLOR.RED);
                }
                break;
            case GRAY:
                for(CARD_COLOR color: cardMap.keySet()){
                    showCards(color);
                }
                break;
        }

        if(cardMap.containsKey(RAINBOW)){
            TrainCardPickerImage image = new LocomotiveTrainCardPicker(fragment, this, RAINBOW);
            imageMap.put(RAINBOW, image);
        }
    }

    private void showCards(CARD_COLOR color){

        TrainCardPickerImage image = null;

        switch (color){
            case PURPLE:
                image = new PurpleTrainCardPicker(fragment, this, color);
                break;
            case GREEN:
                image = new GreenTrainCardPicker(fragment, this, color);
                break;
            case ORANGE:
                image = new OrangeTrainCardPicker(fragment, this, color);
                break;
            case BLUE:
                image = new BlueTrainCardPicker(fragment, this, color);
                break;
            case BLACK:
                image = new BlackTrainCardPicker(fragment, this, color);
                break;
            case RED:
                image = new RedTrainCardPicker(fragment, this, color);
                break;
            case WHITE:
                image = new WhiteTrainCardPicker(fragment, this, color);
                break;
            case YELLOW:
                image = new YellowTrainCardPicker(fragment, this, color);
                break;
        }

        imageMap.put(color, image);

    }

    private void enablePickers(CARD_COLOR color, boolean bool){
        for(CARD_COLOR card_color: imageMap.keySet()){
            if(card_color != color && card_color != RAINBOW){
                imageMap.get(card_color).setAsEnabled(bool);
            }
        }
    }

    private List<TrainCard> claim(){
        List<TrainCard> cardsUsed = new ArrayList<>();
        for(CARD_COLOR color : imageMap.keySet()){
            for (int i = 0; i < imageMap.get(color).getCount(); i++)
            {
                cardsUsed.add(playerCards.get(color).get(i));
            }
        }
        return cardsUsed;
    }

    private void checkClaimButton(){

        int numberOfCardsUsed = 0;
        for(CARD_COLOR image : imageMap.keySet()){
            if(cardMap.get(image) == imageMap.get(image).getCount()){
                imageMap.get(image).enablePlus(false);
            }
            if(imageMap.get(image).getCount() > 0){
                imageMap.get(image).enableMinus(true);
            }
            numberOfCardsUsed += imageMap.get(image).getCount();
        }

        if(numberOfCardsUsed == route.getLength()) {
            for(CARD_COLOR image : imageMap.keySet()){
                imageMap.get(image).enablePlus(false);
                fragment.setClaimButtonEnabled(true);
            }
        }


//            if(imageMap.get(color).getCount() == route.getLength()){
//                imageMap.get(color).enablePlus(false);
//                fragment.setClaimButtonEnabled(true);
//            }
//
//        else if(imageMap.get(RAINBOW).getCount() + imageMap.get(color).getCount() == route.getLength()){
//            imageMap.get(color).enablePlus(false);
//            fragment.setClaimButtonEnabled(true);
//        }
    }

    @Override
    public void onIncrement(CARD_COLOR color){

        imageMap.get(color).enableMinus(true);

        if(imageMap.get(color).getCount() == 1 && color != RAINBOW){
            enablePickers(color, false);
        }

        if(cardMap.get(color) == imageMap.get(color).getCount()){
            imageMap.get(color).enablePlus(false);
        }

        checkClaimButton();
    }

    @Override
    public void onDecrement(CARD_COLOR color){

        imageMap.get(color).enablePlus(true);
        fragment.setClaimButtonEnabled(false);

        if(imageMap.get(color).getCount() == 0){
            enablePickers(color, true);
            imageMap.get(color).enableMinus(false);
        }

        checkClaimButton();

    }

}
