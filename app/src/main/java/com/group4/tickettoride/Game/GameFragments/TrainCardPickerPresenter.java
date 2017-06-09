package com.group4.tickettoride.Game.GameFragments;

import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Deck.TrainCardDeck;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Map.PLAYER_COLOR;
import com.group4.shared.Model.Map.ROUTE_COLOR;
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
import com.group4.tickettoride.Game.IGamePresenter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static com.group4.shared.Model.Deck.CARD_COLOR.RAINBOW;

/**
 * Created by Russell Fitzpatrick on 6/6/2017.
 */

public class TrainCardPickerPresenter implements Observer, ITrainCardPickerPresenter{

    private TrainCardPickerFragment fragment;
    private RouteSegment route;
    private GamePresenter gamePresenter;
    private Map<CARD_COLOR, Integer> cardMap;
    private Map<CARD_COLOR, Integer> pickedMap;
    private Map<CARD_COLOR, ArrayList<TrainCard>> playerCards;
    private Map<CARD_COLOR, TrainCardPickerImage> imageMap;

    public TrainCardPickerPresenter(TrainCardPickerFragment fragment, RouteSegment route, GamePresenter gamePresenter) {
        this.fragment = fragment;
        this.route = route;
        this.gamePresenter = gamePresenter;
        createPlayerCardsMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards().getCardDeck());
        fillMap(ClientModel.SINGLETON.getPlayer().getPlayerHand().getTrainCards());
        initializePickedMap();

    }

    public void afterFragmentCreation()
    {
        setRouteInfo();
        setInitialPickers();
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

    private void setInitialPickers()
    {

        switch (route.getRouteColor())
        {
            case PINK:
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
        if(cardMap.containsKey(RAINBOW)){
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
        gamePresenter.getState().claimRoute(route.getRouteId(), getPickedCards());
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void onClaim() {
        claimRoute();
    }

    private List<TrainCard> getPickedCards()
    {
        List<TrainCard> cardsUsed = new ArrayList<>();
        //figure out what combination of cards they picked
        int locomotiveCount = pickedMap.get(RAINBOW);
        for (int i = 0; i < locomotiveCount; i++)
        {
            cardsUsed.add(playerCards.get(RAINBOW).get(i));
        }

        int regularCardCount = 0;
        CARD_COLOR cardColor = null;
        for (CARD_COLOR color : pickedMap.keySet())
        {
            if (pickedMap.get(color) > 0 && color != CARD_COLOR.RAINBOW)
            {
                regularCardCount = pickedMap.get(color);
                cardColor = color;
            }
        }

        for (int i = 0; i < regularCardCount; i++)
        {
            cardsUsed.add(playerCards.get(cardColor).get(i));
        }

        return cardsUsed;
    }

    private void initializePickedMap()
    {
        pickedMap = new HashMap<>();
        for (CARD_COLOR color : CARD_COLOR.values()){
            pickedMap.put(color, 0);
        }
    }

    /**
     * this function checks to see if the cards the user's selected could be used to claim the route
     * in question
     */
    private void checkRouteClaimStatus()
    {
        int regularCardCount = 0;
        CARD_COLOR cardColor = null;
        int locomotiveCardCount = pickedMap.get(RAINBOW);
        switch (route.getRouteColor())
        {
            case GRAY:
                for (CARD_COLOR color : pickedMap.keySet())
                {
                    if (color != RAINBOW && pickedMap.get(color) > 0)
                    {
                        regularCardCount = pickedMap.get(color);
                        cardColor = color;
                        break;
                    }
                }

                break;
            case PINK:
                regularCardCount = pickedMap.get(CARD_COLOR.PURPLE);
                cardColor = CARD_COLOR.PURPLE;
                break;
            case WHITE:
                regularCardCount = pickedMap.get(CARD_COLOR.WHITE);
                cardColor = CARD_COLOR.WHITE;
                break;
            case BLUE:
                regularCardCount = pickedMap.get(CARD_COLOR.BLUE);
                cardColor = CARD_COLOR.BLUE;
                break;
            case YELLOW:
                regularCardCount = pickedMap.get(CARD_COLOR.YELLOW);
                cardColor = CARD_COLOR.YELLOW;
                break;
            case ORANGE:
                regularCardCount = pickedMap.get(CARD_COLOR.ORANGE);
                cardColor = CARD_COLOR.ORANGE;
                break;
            case BLACK:
                regularCardCount = pickedMap.get(CARD_COLOR.BLACK);
                cardColor = CARD_COLOR.BLACK;
                break;
            case RED:
                regularCardCount = pickedMap.get(CARD_COLOR.RED);
                cardColor = CARD_COLOR.RED;
                break;
            case GREEN:
                regularCardCount = pickedMap.get(CARD_COLOR.GREEN);
                cardColor = CARD_COLOR.GREEN;
                break;
        }
        if (regularCardCount + locomotiveCardCount == route.getLength())
        {
            //route could be claimed at this point
//            if (regularCardCount > 0){
//                //this.disablePlusButton(cardColor);
//            }
//            if (locomotiveCardCount > 0) {
//                //disablePlusButton(CARD_COLOR.RAINBOW);
//            }
            fragment.setClaimButtonEnabled(true);
        }
        else
        {
            fragment.setClaimButtonEnabled(false);
        }

    }

    public void disablePlusButton(CARD_COLOR color)
    {


        switch (color){
                case PURPLE:
                    fragment.setPurpleCardPlusEnabled(false);
                    break;
                case GREEN:
                    fragment.setGreenCardPlusEnabled(false);
                    break;
                case ORANGE:
                    fragment.setOrangeCardPlusEnabled(false);
                    break;
                case BLUE:
                    fragment.setBlueCardPlusEnabled(false);
                    break;
                case BLACK:
                    fragment.setBlackCardPlusEnabled(false);
                    break;
                case RED:
                    fragment.setRedCardPlusEnabled(false);
                    break;
                case WHITE:
                    fragment.setWhiteCardPlusEnabled(false);
                    break;
                case YELLOW:
                    fragment.setYellowCardPlusEnabled(false);
                    break;
                case RAINBOW:
                    fragment.setLocomotiveCardPlusEnabled(false);
            }

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
            if(card_color != color || card_color == RAINBOW){
                imageMap.get(card_color).setAsEnabled(bool);
            }
        }
    }

    public List<TrainCard> claim(){
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

        if(imageMap.get(color).getCount() == 1){
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







    @Override
    public void onPurpleIncrement() {

        int purpleCount = fragment.getPurpleCardCount();
        purpleCount++;

        if(purpleCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.PURPLE);
        }
        fragment.setPurpleCardCount(Integer.toString(purpleCount));
        pickedMap.put(CARD_COLOR.PURPLE, purpleCount);

        //if the player has now selected all the cards they have of a color
        if (purpleCount == cardMap.get(CARD_COLOR.PURPLE))
        {
            fragment.setPurpleCardPlusEnabled(false);
        }
        fragment.setPurpleCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onPurpleDecrement() {
        int purpleCount = fragment.getPurpleCardCount();
        purpleCount--;
        if (purpleCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setPurpleCardMinusEnabled(false);
        }
        fragment.setPurpleCardCount(Integer.toString(purpleCount));
        pickedMap.put(CARD_COLOR.PURPLE, purpleCount);
        fragment.setPurpleCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onWhiteIncrement() {

        int whiteCount = fragment.getWhiteCardCount();
        whiteCount++;

        if(whiteCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.WHITE);
        }
        fragment.setWhiteCardCount(Integer.toString(whiteCount));
        pickedMap.put(CARD_COLOR.WHITE, whiteCount);

        //if the player has now selected all the cards they have of a color
        if (whiteCount == cardMap.get(CARD_COLOR.WHITE))
        {
            fragment.setWhiteCardPlusEnabled(false);
        }
        fragment.setWhiteCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onWhiteDecrement() {
        int whiteCount = fragment.getWhiteCardCount();
        whiteCount--;
        if (whiteCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setWhiteCardMinusEnabled(false);
        }
        fragment.setWhiteCardCount(Integer.toString(whiteCount));
        pickedMap.put(CARD_COLOR.WHITE, whiteCount);
        fragment.setWhiteCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onBlueIncrement() {

        int blueCount = fragment.getBlueCardCount();
        blueCount++;

        if(blueCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.BLUE);
        }
        fragment.setBlueCardCount(Integer.toString(blueCount));
        pickedMap.put(CARD_COLOR.BLUE, blueCount);

        //if the player has now selected all the cards they have of a color
        if (blueCount == cardMap.get(CARD_COLOR.BLUE))
        {
            fragment.setBlueCardPlusEnabled(false);
        }
        fragment.setBlueCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onBlueDecrement() {
        int blueCount = fragment.getBlueCardCount();
        blueCount--;
        if (blueCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setBlueCardMinusEnabled(false);
        }
        fragment.setBlueCardCount(Integer.toString(blueCount));
        pickedMap.put(CARD_COLOR.BLUE, blueCount);
        fragment.setBlueCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onYellowIncrement() {

        int yellowCount = fragment.getYellowCardCount();
        yellowCount++;

        if(yellowCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.YELLOW);
        }
        fragment.setYellowCardCount(Integer.toString(yellowCount));
        pickedMap.put(CARD_COLOR.YELLOW, yellowCount);

        //if the player has now selected all the cards they have of a color
        if (yellowCount == cardMap.get(CARD_COLOR.YELLOW))
        {
            fragment.setYellowCardPlusEnabled(false);
        }
        fragment.setYellowCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onYellowDecrement() {
        int yellowCount = fragment.getYellowCardCount();
        yellowCount--;
        if (yellowCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setYellowCardMinusEnabled(false);
        }
        fragment.setYellowCardCount(Integer.toString(yellowCount));
        pickedMap.put(CARD_COLOR.YELLOW, yellowCount);
        fragment.setYellowCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onOrangeIncrement() {

        int orangeCount = fragment.getOrangeCardCount();
        orangeCount++;

        if(orangeCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.ORANGE);
        }
        fragment.setOrangeCardCount(Integer.toString(orangeCount));
        pickedMap.put(CARD_COLOR.ORANGE, orangeCount);

        //if the player has now selected all the cards they have of a color
        if (orangeCount == cardMap.get(CARD_COLOR.ORANGE))
        {
            fragment.setOrangeCardPlusEnabled(false);
        }
        fragment.setOrangeCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onOrangeDecrement() {
        int orangeCount = fragment.getOrangeCardCount();
        orangeCount--;
        if (orangeCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setOrangeCardMinusEnabled(false);
        }
        fragment.setOrangeCardCount(Integer.toString(orangeCount));
        pickedMap.put(CARD_COLOR.ORANGE, orangeCount);
        fragment.setOrangeCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onBlackIncrement() {

        int blackCount = fragment.getBlackCardCount();
        blackCount++;

        if(blackCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.BLACK);
        }
        fragment.setBlackCardCount(Integer.toString(blackCount));
        pickedMap.put(CARD_COLOR.BLACK, blackCount);

        //if the player has now selected all the cards they have of a color
        if (blackCount == cardMap.get(CARD_COLOR.BLACK))
        {
            fragment.setBlackCardPlusEnabled(false);
        }
        fragment.setBlackCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onBlackDecrement() {
        int blackCount = fragment.getBlackCardCount();
        blackCount--;
        if (blackCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setBlackCardMinusEnabled(false);
        }
        fragment.setBlackCardCount(Integer.toString(blackCount));
        pickedMap.put(CARD_COLOR.BLACK, blackCount);
        fragment.setBlackCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onRedIncrement() {

        int redCount = fragment.getRedCardCount();
        redCount++;

        if(redCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.RED);
        }
        fragment.setRedCardCount(Integer.toString(redCount));
        pickedMap.put(CARD_COLOR.RED, redCount);

        //if the player has now selected all the cards they have of a color
        if (redCount == cardMap.get(CARD_COLOR.RED))
        {
            fragment.setRedCardPlusEnabled(false);
        }
        fragment.setRedCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onRedDecrement() {
        int redCount = fragment.getRedCardCount();
        redCount--;
        if (redCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setRedCardMinusEnabled(false);
        }
        fragment.setRedCardCount(Integer.toString(redCount));
        pickedMap.put(CARD_COLOR.RED, redCount);
        fragment.setRedCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onGreenIncrement() {

        int greenCount = fragment.getGreenCardCount();
        greenCount++;

        if(greenCount == 1){
            fragment.disableAllCurrentPickers();
            setCardVisibilty(CARD_COLOR.GREEN);
        }
        fragment.setGreenCardCount(Integer.toString(greenCount));
        pickedMap.put(CARD_COLOR.GREEN, greenCount);

        //if the player has now selected all the cards they have of a color
        if (greenCount == cardMap.get(CARD_COLOR.GREEN))
        {
            fragment.setGreenCardPlusEnabled(false);
        }
        fragment.setGreenCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onGreenDecrement() {
        int greenCount = fragment.getGreenCardCount();
        greenCount--;
        if (greenCount == 0)
        {
            fragment.setAllCurrentPickersEnabled();
            fragment.setGreenCardMinusEnabled(false);
        }
        fragment.setGreenCardCount(Integer.toString(greenCount));
        pickedMap.put(CARD_COLOR.GREEN, greenCount);
        fragment.setGreenCardPlusEnabled(true);
        checkRouteClaimStatus();
    }

    @Override
    public void onLocomotiveIncrement() {

        int locomotiveCount = fragment.getLocomotiveCardCount();
        locomotiveCount++;

//        if(locomotiveCount == 1){
//            fragment.disableAllCurrentPickers();
//            setCardVisibilty(CARD_COLOR.RAINBOW);
//        }
        fragment.setLocomotiveCardCount(Integer.toString(locomotiveCount));
        pickedMap.put(RAINBOW, locomotiveCount);

        //if the player has now selected all the cards they have of a color
        if (locomotiveCount == cardMap.get(RAINBOW))
        {
            fragment.setLocomotiveCardPlusEnabled(false);
        }
        fragment.setLocomotiveCardMinusEnabled(true);
        checkRouteClaimStatus();

    }

    @Override
    public void onLocomotiveDecrement() {
        int locomotiveCount = fragment.getLocomotiveCardCount();
        locomotiveCount--;
        if (locomotiveCount == 0)
        {
            fragment.setLocomotiveCardMinusEnabled(false);
        }
        fragment.setLocomotiveCardCount(Integer.toString(locomotiveCount));
        pickedMap.put(RAINBOW, locomotiveCount);
        fragment.setLocomotiveCardPlusEnabled(true);
        checkRouteClaimStatus();
    }




}
