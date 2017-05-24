package com.group4.shared.Model;

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
}
