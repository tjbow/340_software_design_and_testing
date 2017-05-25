package com.group4.shared.Model;

import java.util.List;

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
    }

    private void initializeDestCardDeck()
    {
        //TODO: PHASE 2: initialize the destination card deck
        destinationCardDeck = new DestinationCardDeck("newgame");
    }

    private void initializeFaceUpDeck()
    {
        List<TrainCard> drawResult = trainCardDeck.draw();
        for(TrainCard card : drawResult)
        {
            card.setVisible(true);
            faceUpDeck.add(card);
            trainCardDeck.getCardDeck().remove(card);
        }
    }
}
