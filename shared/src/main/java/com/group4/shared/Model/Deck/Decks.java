package com.group4.shared.Model.Deck;

import java.util.Iterator;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class Decks {

    private TrainCardDeck trainCardDeck;
    private DestinationCardDeck destinationCardDeck;
    private FaceUpDeck faceUpDeck;

    public TrainCardDeck getTrainCardDeck() {
        return trainCardDeck;
    }

    public void setTrainCardDeck(TrainCardDeck trainCardDeck) {
        this.trainCardDeck = trainCardDeck;
    }

    public DestinationCardDeck getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public void setDestinationCardDeck(DestinationCardDeck destinationCardDeck) {
        this.destinationCardDeck = destinationCardDeck;
    }

    public FaceUpDeck getFaceUpDeck() {
        return faceUpDeck;
    }

    public void setFaceUpDeck(FaceUpDeck faceUpDeck) {
        this.faceUpDeck = faceUpDeck;
    }

    public void startGameDeck()
    {
        initializeTrainCardDeck();
        initializeDestCardDeck();
        initializeFaceUpDeck();
    }

    private void initializeTrainCardDeck()
    {
        trainCardDeck = new TrainCardDeck();
        for(int i = 0; i < 12; i++)
        {
            trainCardDeck.add(new TrainCard(CARD_COLOR.BLACK, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.BLUE, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.GREEN, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.YELLOW, false));

            trainCardDeck.add(new TrainCard(CARD_COLOR.ORANGE, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.PURPLE, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.RED, false));
            trainCardDeck.add(new TrainCard(CARD_COLOR.WHITE, false));

            trainCardDeck.add(new TrainCard(CARD_COLOR.RAINBOW, false));
        }
        trainCardDeck.shuffle();
    }

    private void initializeDestCardDeck()
    {
        destinationCardDeck = new DestinationCardDeck("newgame");
        destinationCardDeck.shuffle();
    }

    private void initializeFaceUpDeck()
    {
        faceUpDeck = new FaceUpDeck();

        int count = 5;
        for(Iterator<TrainCard> iterator = trainCardDeck.getCardDeck().iterator(); iterator.hasNext();)
        {
            TrainCard current = iterator.next();
            iterator.remove();
            faceUpDeck.add(current);
            count--;
            if(count == 0) break;
        }

        for(TrainCard card : faceUpDeck.getFaceUpCards())
        {
            card.setVisible(true);
        }
    }
}
