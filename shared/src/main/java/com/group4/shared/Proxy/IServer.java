package com.group4.shared.Proxy;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public interface IServer {

    Results login(User user);
    Results register(User user);
    Results createGame(String gameName, int numberOfPlayers);
    Results joinGame(String gameName);
    Results startGame(String gameName);
    Results getGameList();
    Results endGame(String gameName);

//    PHASE 2
    Results getPendingCommands(User user, int lastCmdExecuted);
    Results sendChat(Message message);
    Results drawDestinationCards(String userName);
    Results returnDestinationCard(List<DestinationCard> returnedCard);
}
