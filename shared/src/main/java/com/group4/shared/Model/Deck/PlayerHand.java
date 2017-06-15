package com.group4.shared.Model.Deck;

import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.shared.Model.Deck.TrainCardDeck;

import java.io.Serializable;

/**
 * Created by Russell Fitzpatrick on 5/27/2017.
 */

public class PlayerHand implements Serializable{

    TrainCardDeck trainCards;
    DestinationCardDeck destinationCards;

    public PlayerHand() {
        trainCards = new TrainCardDeck();
        destinationCards = new DestinationCardDeck();
    }

    public TrainCardDeck getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(TrainCardDeck trainCards) {
        this.trainCards = trainCards;
    }

    public DestinationCardDeck getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(DestinationCardDeck destinationCards) {
        this.destinationCards = destinationCards;
    }

    public int getDestinationCardCount(){
        if (destinationCards.getDestDeck() != null){
            return destinationCards.getDestDeck().size();
        }
        else
        {
            return 0;
        }

    }

    public int getTrainCardCount() {return trainCards.getCardDeck().size();}
}
