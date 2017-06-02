package com.group4.shared.Proxy;

import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Map.RouteSegment;
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

//    PHASE 3
    Results drawFaceUpTrainCard(String userName, int position);
    Results drawFaceDownTrainCard(String userName, TrainCard selectedCard);
    Results claimRoute(String userName, RouteSegment claimedSegment, List<TrainCard> usedCards);
}
