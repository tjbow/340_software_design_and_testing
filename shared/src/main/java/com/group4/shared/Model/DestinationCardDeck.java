package com.group4.shared.Model;

import java.util.List;

import sun.security.krb5.internal.crypto.Des;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class DestinationCardDeck {

    private List<DestinationCard> destDeck;

    public List<DestinationCard> getDestDeck() {
        return destDeck;
    }

    public void setDestDeck(List<DestinationCard> destDeck) {
        this.destDeck = destDeck;
    }

    public List<DestinationCard> draw(){
        return destDeck.subList((destDeck.size() - 4), destDeck.size());
    }

    public void add(DestinationCard destCard){
        destDeck.add(destCard);
    }

    public DestinationCard findCard(String Id){
        for(DestinationCard destinationCard : destDeck){
            if(destinationCard.getId().equals(Id)) {
                return destinationCard;
            }
        }

        return null;
    }
}
