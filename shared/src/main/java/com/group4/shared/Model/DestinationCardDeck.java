package com.group4.shared.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import sun.security.krb5.internal.crypto.Des;

/**
 * Created by Russell Fitzpatrick on 5/23/2017.
 */

public class DestinationCardDeck {

    private List<DestinationCard> destDeck;

    public DestinationCardDeck(String param)
    {
        destDeck = new ArrayList<>();
        if(param.equals("newgame"))
        {
            initializeGameDestDeck();
        }
    }

    public DestinationCardDeck()
    {
        destDeck = new ArrayList<>();
    }

    private void initializeGameDestDeck()
    {

//        destDeck.add(new DestinationCard("1", new City()));
    }

    private void getCities(){

//        Staff staff = gson.fromJson(new FileReader("D:\\file.json"), Staff.class);

//        InputStream is = new FileInputStream(new File());
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            this.cities = mapper.readValue(is, new TypeReference<List<City>>() { });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

    }

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
