package com.group4.tickettoride.NextLayerFacade;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Graph.RoutePaths;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NextLayerFacade implements INextLayerFacade {

    private NextLayerFacade(){}

    public static NextLayerFacade SINGLETON = new NextLayerFacade();

    @Override
    public void login(String username, String password) {
        User loggedUser = new User(username, password);
        ServerProxy.SINGLETON.login(loggedUser);
    }

    @Override
    public void register(String username, String password) {
        User registeredUser = new User(username, password);
        ServerProxy.SINGLETON.register(registeredUser);
    }

    @Override
    public void createGame(String gameName, int playerCount) {
        ServerProxy.SINGLETON.createGame(gameName, playerCount);
    }

    @Override
    public void joinGame(String gameName) {
        //Game game = ClientModel.SINGLETON.getGameList().getGame(position);
        ServerProxy.SINGLETON.joinGame(ClientModel.SINGLETON.getUser().getUsername(), gameName);
    }

    @Override
    public void startGame(String gameName) {
        ServerProxy.SINGLETON.startGame(gameName);
    }

    @Override
    public void endGame(String gameName)
    {
        ServerProxy.SINGLETON.endGame(gameName);
    }

    @Override
    public void claimRoute(String routeId, List<TrainCard> usedCards) {
//TODO: Remove Debug
// This is debug code to find issues with the routes and cities
//        RoutePaths paths = new RoutePaths();
//
//        Set<RouteSegment> routes = ClientModel.SINGLETON.getGame().getRoutes().getRouteList();
//        for(RouteSegment route : routes)
//        {
//            paths.add(route);
//            System.out.println(route.getCityA() + " to " + route.getCityB());
//        }
//        for(RouteSegment route : routes)
//        {
//            System.out.println(route.getRouteId());
//        }
//        for(String city : paths.getPlayerGraph().vertexSet())
//        {
//            System.out.println(city);
//        }
//        System.out.println("done");
        RouteSegment segment = ClientModel.SINGLETON.getGame().getRoutes().findRoute(routeId);
        String username = ClientModel.SINGLETON.getUser().getUsername();

        ServerProxy.SINGLETON.claimRoute(username, segment, usedCards);

    }

    @Override
    public void drawFaceUpTrainCard(int position) {

        String username = ClientModel.SINGLETON.getUser().getUsername();

        ServerProxy.SINGLETON.drawFaceUpTrainCard(username, position);

    }

    @Override
    public void drawFaceDownTrainCard() {

        String username = ClientModel.SINGLETON.getUser().getUsername();

        ServerProxy.SINGLETON.drawFaceDownTrainCard(username);

    }

    @Override
    public void drawDestinationCards() {

        String username = ClientModel.SINGLETON.getUser().getUsername();

        ServerProxy.SINGLETON.drawDestinationCards(username);

    }

    @Override
    public void sendMessage(String message)
    {
        Message newMessage = new Message(message, ClientModel.SINGLETON.getUser().getUsername(),
                ClientModel.SINGLETON.getPlayer().getColor());
        ServerProxy.SINGLETON.sendChat(newMessage);
    }
}
