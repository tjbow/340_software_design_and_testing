package com.group4.shared.Model.Deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class TrainCardDeck implements Serializable
{

    List<TrainCard> cardDeck;

    public TrainCardDeck(List<TrainCard> cardDeck)
    {
        this.cardDeck = cardDeck;
    }

    public TrainCardDeck()
    {
        cardDeck = new ArrayList<>();
    }

    public List<TrainCard> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(List<TrainCard> cardDeck) {
        this.cardDeck = cardDeck;
    }

    /**
     * Draw a single card from the deck, remove the card from the deck
     * @return the drawn train card, null if 0 cards left
     */
    public TrainCard draw()
    {
        if(!cardDeck.isEmpty())
        {
            return cardDeck.remove(cardDeck.size() - 1);
        }
        else
        {
            return null;
        }
    }

    public List<TrainCard> drawFour(){

        return cardDeck.subList((cardDeck.size() - 4), cardDeck.size());
    }

    public void add(TrainCard card){
        cardDeck.add(card);
    }

    public void shuffle()
    {
        Collections.shuffle(cardDeck);
        Collections.shuffle(cardDeck);
        Collections.shuffle(cardDeck);
    }

}
