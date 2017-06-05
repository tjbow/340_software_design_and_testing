package com.group4.tickettoride.NextLayerFacade;


import com.group4.shared.Model.Deck.TrainCard;

import java.util.List;

interface INextLayerFacade {

    void login (String username, String password);

    void register (String username, String password);

    void createGame(String gameName, int playerCount);

    void joinGame(String gameName);

    void startGame(String gameName);

    void endGame(String gameName);

    void claimRoute(String routeId, List<TrainCard> usedCards);

    void drawFaceUpTrainCard(int position);

    void drawFaceDownTrainCard();

    void drawDestinationCards();

    void sendMessage(String message);
}
