package com.group4.shared.Model;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class TrainCardDeck {

    List<TrainCard> cardDeck;

    public List<TrainCard> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(List<TrainCard> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public List<TrainCard> draw(){
        return cardDeck.subList((cardDeck.size() - 4), cardDeck.size());
    }

    public void add(TrainCard card){
        cardDeck.add(card);
    }

}
