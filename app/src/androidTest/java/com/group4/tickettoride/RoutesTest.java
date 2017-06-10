package com.group4.tickettoride;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.DestinationCardDeck;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tom on 6/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class RoutesTest
{
    RouteList routeList;
    DestinationCardDeck destDeck;

    public RoutesTest()
    {
        routeList = new RouteList();
        destDeck = new DestinationCardDeck("newgame");
        getRoutes();
    }

    private void getRoutes(){
        Set<RouteSegment> routeSegments = new HashSet<>();
        Context appContext = InstrumentationRegistry.getTargetContext();
        InputStream is = appContext.getResources().openRawResource(R.raw.routes);

        ObjectMapper mapper = new ObjectMapper();
        try {
            routeSegments = mapper.readValue(is, new TypeReference<Set<RouteSegment>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }
        routeList.setRouteList(routeSegments);
    }

    /*
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
        destDeck.add(new DestinationCard(29, "Seattle", "LosAngeles", 9)); */

    @Test
    public void LAtoNYTest()
    {
        Player player0 = new Player(new User("player0"));
        DestinationCard destination = destDeck.findCard(0);
        player0.initializeGame();
        player0.addClaimedRoute(routeList.findRoute("ElPasoLosAngeles"));
        player0.addClaimedRoute(routeList.findRoute("HoustonElPaso"));
        player0.addClaimedRoute(routeList.findRoute("NewOrleansHouston"));
        player0.addClaimedRoute(routeList.findRoute("AtlantaNewOrleansRight"));
        player0.addClaimedRoute(routeList.findRoute("AtlantaRaleigh"));
        player0.addClaimedRoute(routeList.findRoute("WashingtonRaleighTop"));
        player0.addClaimedRoute(routeList.findRoute("WashingtonNewYorkRight"));
        Assert.assertTrue(player0.destinationComplete(destination.getCityA(), destination.getCityB()));
    }
}
