package com.group4.shared.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sun.security.krb5.internal.crypto.Des;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class DestinationCardDeck {

    private List<DestinationCard> destDeck;

    //constructor for initializing player deck
    public DestinationCardDeck()
    {
        destDeck = new ArrayList<>();
    }

    //constructor for initializing game deck (start game)
    public DestinationCardDeck(String param)
    {
        destDeck = new ArrayList<>();
        if(param.equals("newgame"))
        {
            initializeGameDestDeck();
        }
    }

    private void initializeGameDestDeck()
    {
        destDeck.add(new DestinationCard(0, "Los Angeles", "New York", 21));
        destDeck.add(new DestinationCard(1, "Duluth", "Houston", 8));
        destDeck.add(new DestinationCard(2, "Sault St. Marie", "Nashville", 8));
        destDeck.add(new DestinationCard(3, "New York", "Atlanta", 6));
        destDeck.add(new DestinationCard(4, "Portland", "Nashville", 17));
        destDeck.add(new DestinationCard(5, "Vancouver", "Montreal", 20));
        destDeck.add(new DestinationCard(6, "Duluth", "El Paso", 10));
        destDeck.add(new DestinationCard(7, "Toronto", "Miami", 10));
        destDeck.add(new DestinationCard(8, "Portland", "Phoenix", 11));
        destDeck.add(new DestinationCard(9, "Dallas", "New York", 11));
        destDeck.add(new DestinationCard(10, "Calgary", "Salt Lake City", 7));
        destDeck.add(new DestinationCard(11, "Calgary", "Phoenix", 13));
        destDeck.add(new DestinationCard(12, "Los Angeles", "Miami", 20));
        destDeck.add(new DestinationCard(13, "Winnipeg", "Little Rock", 11));
        destDeck.add(new DestinationCard(14, "San Francisco", "Atlanta", 17));
        destDeck.add(new DestinationCard(15, "Kansas City", "Houston", 5));
        destDeck.add(new DestinationCard(16, "Los Angeles", "Chicago", 16));
        destDeck.add(new DestinationCard(17, "Denver", "Pittsburgh", 11));
        destDeck.add(new DestinationCard(18, "Chicago", "Santa Fe", 9));
        destDeck.add(new DestinationCard(19, "Vancouver", "Santa Fe", 13));
        destDeck.add(new DestinationCard(20, "Boston", "Miami", 12));
        destDeck.add(new DestinationCard(21, "Chicago", "New Orleans", 7));
        destDeck.add(new DestinationCard(22, "Montreal", "Atlanta", 9));
        destDeck.add(new DestinationCard(23, "Seattle", "New York", 22));
        destDeck.add(new DestinationCard(24, "Denver", "El Paso", 4));
        destDeck.add(new DestinationCard(25, "Helena", "Los Angeles", 8));
        destDeck.add(new DestinationCard(26, "Winnipeg", "Houston", 12));
        destDeck.add(new DestinationCard(27, "Montreal", "New Orleans", 13));
        destDeck.add(new DestinationCard(28, "Sault St. Marie", "Oklahoma City", 9));
        destDeck.add(new DestinationCard(29, "Seattle", "Los Angeles", 9));
    }

    public List<DestinationCard> getDestDeck() {
        return destDeck;
    }

    public void setDestDeck(List<DestinationCard> destDeck) {
        this.destDeck = destDeck;
    }

    public List<DestinationCard> draw(){
        return destDeck.subList((destDeck.size() - 3), destDeck.size());
    }

    public void add(DestinationCard destCard){
        destDeck.add(destCard);
    }

    public DestinationCard findCard(int Id){
        for(DestinationCard destinationCard : destDeck){
            if(destinationCard.getId() == Id) {
                return destinationCard;
            }
        }

        return null;
    }
    public void shuffle()
    {
        Collections.shuffle(destDeck);
        Collections.shuffle(destDeck);
        Collections.shuffle(destDeck);
    }
}
