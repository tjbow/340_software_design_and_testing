package com.group4.shared.Model.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        destDeck.add(new DestinationCard(0, "LosAngeles", "NewYork", 21));
        destDeck.add(new DestinationCard(1, "Duluth", "Houston", 8));
        destDeck.add(new DestinationCard(2, "SaultStMarie", "Nashville", 8));
        destDeck.add(new DestinationCard(3, "NewYork", "Atlanta", 6));
        destDeck.add(new DestinationCard(4, "Portland", "Nashville", 17));
        destDeck.add(new DestinationCard(5, "Vancouver", "Montreal", 20));
        destDeck.add(new DestinationCard(6, "Duluth", "ElPaso", 10));
        destDeck.add(new DestinationCard(7, "Toronto", "Miami", 10));
        destDeck.add(new DestinationCard(8, "Portland", "Phoenix", 11));
        destDeck.add(new DestinationCard(9, "Dallas", "NewYork", 11));
        destDeck.add(new DestinationCard(10, "Calgary", "SaltLakeCity", 7));
        destDeck.add(new DestinationCard(11, "Calgary", "Phoenix", 13));
        destDeck.add(new DestinationCard(12, "LosAngeles", "Miami", 20));
        destDeck.add(new DestinationCard(13, "Winnipeg", "LittleRock", 11));
        destDeck.add(new DestinationCard(14, "SanFrancisco", "Atlanta", 17));
        destDeck.add(new DestinationCard(15, "KansasCity", "Houston", 5));
        destDeck.add(new DestinationCard(16, "LosAngeles", "Chicago", 16));
        destDeck.add(new DestinationCard(17, "Denver", "Pittsburgh", 11));
        destDeck.add(new DestinationCard(18, "Chicago", "SantaFe", 9));
        destDeck.add(new DestinationCard(19, "Vancouver", "SantaFe", 13));
        destDeck.add(new DestinationCard(20, "Boston", "Miami", 12));
        destDeck.add(new DestinationCard(21, "Chicago", "NewOrleans", 7));
        destDeck.add(new DestinationCard(22, "Montreal", "Atlanta", 9));
        destDeck.add(new DestinationCard(23, "Seattle", "NewYork", 22));
        destDeck.add(new DestinationCard(24, "Denver", "ElPaso", 4));
        destDeck.add(new DestinationCard(25, "Helena", "LosAngeles", 8));
        destDeck.add(new DestinationCard(26, "Winnipeg", "Houston", 12));
        destDeck.add(new DestinationCard(27, "Montreal", "NewOrleans", 13));
        destDeck.add(new DestinationCard(28, "SaultStMarie", "OklahomaCity", 9));
        destDeck.add(new DestinationCard(29, "Seattle", "LosAngeles", 9));
    }

    public List<DestinationCard> getDestDeck() {
        return destDeck;
    }

    public void setDestDeck(List<DestinationCard> destDeck) {
        this.destDeck = destDeck;
    }

    public List<DestinationCard> draw(){
        int numDrawCards = 3;
        if(destDeck.size() < 3)
        {
            numDrawCards = destDeck.size();
        }
        List<DestinationCard> drawnCards = destDeck.subList((destDeck.size() - numDrawCards), destDeck.size());
        destDeck.removeAll(drawnCards);
        return drawnCards;
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
