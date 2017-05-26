package com.group4.tickettoride.NextLayerFacade;

import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.User;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;

import java.util.ArrayList;
import java.util.List;

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
        ServerProxy.SINGLETON.joinGame(gameName);
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
    public void claimRoute(String routeId) {
        return;
    }

    @Override
    public void sendMessage(String message) {
        Message newMessage = new Message(message, ClientModel.SINGLETON.getUser().getUsername(), ClientModel.SINGLETON.getPlayer().getColor());
        ServerProxy.SINGLETON.sendChat(newMessage);
    }

    @Override
    public void drawDestinationCards(List<Integer> destinationId) {
        List<DestinationCard> destinationCards = new ArrayList<>();
        for(int Id : destinationId){
            destinationCards.add(ClientModel.SINGLETON.getGame().getDecks().getDestinationCardDeck().findCard(Id));
        }

        ServerProxy.SINGLETON.drawDestinationCards(ClientModel.SINGLETON.getUser().getUsername(), destinationCards);
    }
}
