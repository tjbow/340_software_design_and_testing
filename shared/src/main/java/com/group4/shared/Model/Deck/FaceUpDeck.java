package com.group4.shared.Model.Deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class FaceUpDeck implements Serializable
{

    private List<TrainCard> faceUpCards;

    public FaceUpDeck()
    {
        faceUpCards = new ArrayList<>();
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    public List<TrainCard> draw(){
        return faceUpCards.subList((faceUpCards.size() - 4), faceUpCards.size());
    }

    public void add(TrainCard card){
        faceUpCards.add(card);
    }
}
