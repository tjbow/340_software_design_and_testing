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
    public void LAToNYTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(0);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("ElPasoLosAngeles"));
        player.addClaimedRoute(routeList.findRoute("HoustonElPaso"));
        player.addClaimedRoute(routeList.findRoute("NewOrleansHouston"));
        player.addClaimedRoute(routeList.findRoute("AtlantaNewOrleansRight"));
        player.addClaimedRoute(routeList.findRoute("AtlantaRaleighLeft"));
        player.addClaimedRoute(routeList.findRoute("WashingtonRaleighTop"));
        player.addClaimedRoute(routeList.findRoute("WashingtonNewYorkRight"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void DuluthToHoustonTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(1);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("OmahaDuluthRight"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOmahaLeft"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOklahomaCityRight"));
        player.addClaimedRoute(routeList.findRoute("OklahomaCityDallasLeft"));
        player.addClaimedRoute(routeList.findRoute("DallasHoustonRight"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void SaultStMarieToNashvilleTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(2);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("SaultStMarieToronto"));
        player.addClaimedRoute(routeList.findRoute("TorontoPittsburgh"));
        player.addClaimedRoute(routeList.findRoute("NashvillePittsburgh"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void NewYorkToAtlantaTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(3);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("WashingtonNewYorkRight"));
        player.addClaimedRoute(routeList.findRoute("WashingtonRaleighBottom"));
        player.addClaimedRoute(routeList.findRoute("AtlantaRaleighRight"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void PortlandToNashvilleTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(4);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityPortland"));
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityDenverTop"));
        player.addClaimedRoute(routeList.findRoute("DenverKansasCityBottom"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisKansasCityTop"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisNashville"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void VancouverToMontrealTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(5);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("VancouverCalgary"));
        player.addClaimedRoute(routeList.findRoute("WinnipegCalgary"));
        player.addClaimedRoute(routeList.findRoute("WinnipegSaultStMarie"));
        player.addClaimedRoute(routeList.findRoute("MontrealSaultStMarie"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void DuluthToElPasoTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(6);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("OmahaDuluthLeft"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOmahaRight"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOklahomaCityLeft"));
        player.addClaimedRoute(routeList.findRoute("OklahomaCityDallasRight"));
        player.addClaimedRoute(routeList.findRoute("DallasElPaso"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void TorontoToMiamiTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(7);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("TorontoPittsburgh"));
        player.addClaimedRoute(routeList.findRoute("RaleighPittsburgh"));
        player.addClaimedRoute(routeList.findRoute("CharlestonRaleigh"));
        player.addClaimedRoute(routeList.findRoute("MiamiCharleston"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void PortlandToPhoenixTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(8);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityPortland"));
        player.addClaimedRoute(routeList.findRoute("LasVegasSaltLakeCity"));
        player.addClaimedRoute(routeList.findRoute("LasVegasLosAngeles"));
        player.addClaimedRoute(routeList.findRoute("LosAngelesPhoenix"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }
    @Test
    public void DallasToNewYorkTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(9);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("DallasLittleRock"));
        player.addClaimedRoute(routeList.findRoute("LittleRockSaintLouis"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisPittsburgh"));
        player.addClaimedRoute(routeList.findRoute("PittsburghNewYorkRight"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void CalgaryToSaltLakeCityTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(10);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("CalgaryHelena"));
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityHelena"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void CalgaryToPhoenixTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(11);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("CalgarySeattle"));
        player.addClaimedRoute(routeList.findRoute("HelenaSeattle"));
        player.addClaimedRoute(routeList.findRoute("DenverHelena"));
        player.addClaimedRoute(routeList.findRoute("DenverPhoenix"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void LosAngelesToMiami()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(12);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("ElPasoLosAngeles"));
        player.addClaimedRoute(routeList.findRoute("HoustonElPaso"));
        player.addClaimedRoute(routeList.findRoute("DenverPhoenix"));
        player.addClaimedRoute(routeList.findRoute("NewOrleansHouston"));
        player.addClaimedRoute(routeList.findRoute("MiamiNewOrleans"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void WinnipegToLittleRockTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(13);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("DuluthWinnipeg"));
        player.addClaimedRoute(routeList.findRoute("ChicagoDuluth"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisChicagoLeft"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisNashville"));
        player.addClaimedRoute(routeList.findRoute("NashvilleLittleRock"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    public void SanFranciscoToAtlantaTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(14);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("SanFranciscoSaltLakeCityTop"));
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityDenverBottom"));
        player.addClaimedRoute(routeList.findRoute("DenverKansasCityTop"));
        player.addClaimedRoute(routeList.findRoute("SaintLouisKansasCityBottom"));
        player.addClaimedRoute(routeList.findRoute("NashvillePittsburgh"));
        player.addClaimedRoute(routeList.findRoute("NashvilleAtlanta"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void KansasCityToHoustonTest()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(15);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("KansasCityOklahomaCityLeft"));
        player.addClaimedRoute(routeList.findRoute("OklahomaCityLittleRock"));
        player.addClaimedRoute(routeList.findRoute("NewOrleansLittleRock"));
        player.addClaimedRoute(routeList.findRoute("NewOrleansHouston"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void LosAngelesToChicago()
    {
        Player player = new Player(new User("player"));
        DestinationCard destination = destDeck.findCard(16);
        player.initializeGame();
        player.addClaimedRoute(routeList.findRoute("SanFranciscoLosAngelesRight"));
        player.addClaimedRoute(routeList.findRoute("SanFranciscoSaltLakeCityBottom"));
        player.addClaimedRoute(routeList.findRoute("SaltLakeCityDenverTop"));
        player.addClaimedRoute(routeList.findRoute("DenverKansasCityTop"));
        player.addClaimedRoute(routeList.findRoute("DenverOmaha"));
        player.addClaimedRoute(routeList.findRoute("OmahaChicago"));
        Assert.assertTrue(player.destinationComplete(destination.getCityA(), destination.getCityB()));
    }

    @Test
    public void possiblyBroken()
    {
        Player player = new Player(new User("player"));
        player.initializeGame();
        //19, "Vancouver", "SantaFe", 13
        DestinationCard VancouverToSantaFe = destDeck.findCard(19);
        //11, "Calgary", "Phoenix", 13
        DestinationCard CalgaryToPhoenix = destDeck.findCard(11);
        player.addClaimedRoute(routeList.findRoute("VancouverCalgary"));
        player.addClaimedRoute(routeList.findRoute("CalgarySeattle"));
        //player.addClaimedRoute(routeList.findRoute("SeattlePortlandRight"));
        player.addClaimedRoute(routeList.findRoute("SeattlePortlandLeft"));
        player.addClaimedRoute(routeList.findRoute("CalgaryHelena"));
        player.addClaimedRoute(routeList.findRoute("DenverHelena"));
        player.addClaimedRoute(routeList.findRoute("SantaFeDenver"));
        player.addClaimedRoute(routeList.findRoute("ElPasoSantaFe"));
        player.addClaimedRoute(routeList.findRoute("ElPasoPhoenix"));
        player.addClaimedRoute(routeList.findRoute("HoustonElPaso"));
        player.addClaimedRoute(routeList.findRoute("ElPasoOklahomaCity"));
        //player.addClaimedRoute(routeList.findRoute("OklahomaCityDallasLeft"));
        player.addClaimedRoute(routeList.findRoute("OklahomaCityDallasRight"));
        //player.addClaimedRoute(routeList.findRoute("KansasCityOklahomaCityLeft"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOklahomaCityRight"));
        //player.addClaimedRoute(routeList.findRoute("KansasCityOmahaLeft"));
        player.addClaimedRoute(routeList.findRoute("KansasCityOmahaRight"));
        //player.addClaimedRoute(routeList.findRoute("OmahaDuluthRight"));
        player.addClaimedRoute(routeList.findRoute("OmahaDuluthLeft"));
        player.addClaimedRoute(routeList.findRoute("DuluthSaultStMarie"));
        Assert.assertTrue(player.destinationComplete(VancouverToSantaFe.getCityA(), VancouverToSantaFe.getCityB()));
        Assert.assertTrue(player.destinationComplete(CalgaryToPhoenix.getCityA(), CalgaryToPhoenix.getCityB()));
    }


}
