package com.group4.tickettoride.NextLayerFacade;


 interface INextLayerFacade {

    void login (String username, String password);

    void register (String username, String password);

    void createGame();

    void joinGame();
}
