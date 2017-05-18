package com.group4.tickettoride.NextLayerFacade;


 interface INextLayerFacade {

    void login (String username, String password);

    void register (String username, String password);

    void createGame(String gameName, int playerCount);

    void joinGame(String gameName);

     void startGame(String gameName);
}
