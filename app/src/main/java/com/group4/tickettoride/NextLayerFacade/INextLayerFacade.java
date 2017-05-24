package com.group4.tickettoride.NextLayerFacade;


import java.util.List;

interface INextLayerFacade {

    void login (String username, String password);

    void register (String username, String password);

    void createGame(String gameName, int playerCount);

    void joinGame(String gameName);

    void startGame(String gameName);

    void endGame(String gameName);

    void claimRoute(String routeId);

    void sendMessage(String message);

    void drawDestinationCards(List<String> destinationId);
}
