package com.group4.tickettoride.ClientModel;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.shared.Model.Map.City;
import com.group4.shared.Model.Deck.Decks;
import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.Game.GameStats;
import com.group4.shared.Model.Map.PLAYER_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.Model.MessageList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Deck.PlayerHand;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.Model.User;
import com.group4.tickettoride.Network.ServerProxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class ClientModel extends Observable {

    private User user;
    private GameList gameList;
    private int commandIDIndex; // last command run
    private String authToken;
    private Player player;
    private Game game;

    private Set<RouteSegment> routeSegments;

    private List<DestinationCard> tempDestPickerDeck;

    private int actionCounter;

    public static ClientModel SINGLETON = new ClientModel();

    private ClientModel() {
        actionCounter = 0;
    }

    public ClientModel(User user, GameList gameList) {
        this.user = user;
        this.gameList = gameList;
        this.commandIDIndex = 0;
        this.routeSegments = new HashSet<>();
    }

    public void clear()
    {
        user = null;
        gameList = null;
        authToken = null;
        player = null;
        game = null;
        commandIDIndex = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
        if(user != null)
        {
            this.game = this.gameList.getGameByUsername(user.getUsername());
        }
        sendToObservers(gameList);
    }


    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer() {
        String userName = user.getUsername();

        this.player = game.getPlayerByUserName(userName);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
        sendToObservers(ClientModel.SINGLETON.getGame());
    }

    public void setGameIfUserIsPlaying()
    {
        if (gameList.getGameByUsername(user.getUsername()) != null)
        {
            this.game = gameList.getGameByUsername(user.getUsername());
            setPlayer();
        }
        else
        {
            this.game = null;
        }
    }

    public void setChatHistory(ChatHistory messageList){
        game.setChatHistory(messageList);
        sendToObservers(messageList);
    }

    public MessageList getChatHistory()
    {
        return game.getChatHistory();
    }

    public void setTurnHistory(TurnHistory turnHistory){
        game.setTurnHistory(turnHistory);
        sendToObservers(turnHistory);
    }

    public MessageList getTurnHistory()
    {
        return game.getTurnHistory();
    }

    public void setDecks(Decks decks)
    {
        //used by updateGame when it receives a non-null deck
        if(decks != null)
        {
            game.setDecks(decks);
            sendToObservers(decks);
        }
    }

    public void updateMap(RouteList routeList, List<City> cities){
        game.setRoutes(routeList);
        sendToObservers(routeList);
//        game.setCities(cities);
//        sendToObservers(cities);
    }

    public void updateStats(GameStats stats){
        game.setGameStats(stats);
        sendToObservers(stats);
    }

    public void updatePlayerInfo(List<Player> players)
    {
        game.setPlayers(players);

        //also update the model player
        player = game.getPlayerByUserName(user.getUsername());

        //update the player info fragment
        sendToObservers(game.getPlayers());

        //update the player hand fragment
        if(player != null && player.getPlayerHand() != null)
        {
            sendToObservers(player.getPlayerHand());
        }
    }

    public void setDestPickerDeck(List<DestinationCard> receivedCards)
    {
        this.tempDestPickerDeck = receivedCards;
    }

    public List<DestinationCard> getTempDestPickerDeck()
    {
        return tempDestPickerDeck;
    }

    public void updateState(String userName, MOVE_STATE state){

        if(userName.equals(getPlayer().getUserName())){
            sendToObservers(state);
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    /**
     * Get the current command index for the model
     * @return the current command index
     */
    public int getCommandIDIndex()
    {
        return commandIDIndex;
    }

    /**
     * Sets the command list index, the index of the last command run
     * @param commandIDIndex the index of the last command run
     */
    public void setCommandIDIndex(int commandIDIndex)
    {
        if(this.commandIDIndex < commandIDIndex)
        {
            this.commandIDIndex = commandIDIndex;
        }
    }

    public void sendToObservers(Object arg)
    {
        setChanged();
        notifyObservers(arg);
        clearChanged();
    }

    public Set<RouteSegment> getRouteSegments()
    {
        return routeSegments;
    }

    public void setRouteSegments(Set<RouteSegment> routeSegments)
    {
        this.routeSegments = routeSegments;
    }

    @Deprecated
    public void testActions()
    {
        switch (actionCounter)
        {
            case 0:
                // update player points
                for(Player player : game.getPlayers())
                {
                    //player.setScore(new Random().nextInt(10 - 1 + 1) + 1);
                }
                sendToObservers(game.getPlayers());
                sendToObservers("Update points. Next: Remove Train Card for this player.");
                actionCounter++;
                break;
            case 1:
                // remove train card for this player
                PlayerHand hand = player.getPlayerHand();
                hand.getTrainCards().getCardDeck().remove(0);
                sendToObservers(hand);
                sendToObservers("Remove Train card. Next: Add Train card for this player.");
                actionCounter++;
                break;
            case 2:
                // add train cards for this player
//                ServerProxy.SINGLETON.drawFaceDownTrainCard(user.getUsername());
//                sendToObservers("Train cards added. Next: Remove destination cards");
                actionCounter++;
                break;
            case 3:
                // remove destination cards for this player
                PlayerHand hand1 = player.getPlayerHand();
                List<DestinationCard> removed = new ArrayList<>();
                removed.add(hand1.getDestinationCards().getDestDeck().get(0));
                removed.add(hand1.getDestinationCards().getDestDeck().get(1));
                ServerProxy.SINGLETON.returnDestinationCard(removed);
                sendToObservers("Remove Destination cards. Next: Add Destination cards for this player.");
                actionCounter++;
                break;
            case 4:
                // add destination cards for this player
                ServerProxy.SINGLETON.drawDestinationCards(user.getUsername());
                sendToObservers("Add Destination cards. Next: Update number of destination cards for other players");
                actionCounter++;
                break;
            case 5:
                // Update number of destination cards for other players
                for(Player player : game.getPlayers())
                {
                    ServerProxy.SINGLETON.drawDestinationCards(player.getUserName());
                }
                sendToObservers("Added Dest Cards for other players. Next: Update face up card decks");
                actionCounter++;
                break;
            case 6:
                // Update visible cards and number of invisible cards in train card deck
                List<TrainCard> newCards = new ArrayList<>();
                for(int i = 0; i < 5; i++)
                {
                    newCards.add(game.getDecks().getTrainCardDeck().draw());
                }
                game.getDecks().getFaceUpDeck().setFaceUpCards(newCards);
                sendToObservers("New face up cards drawn. Next: Update number of cards in destination deck");
                actionCounter++;
                break;
            case 7:
                // Update number of cards in destination card deck
                ServerProxy.SINGLETON.drawDestinationCards(player.getUserName());
                sendToObservers("Update number of cards in destination deck. Next: Add claimed route for any player");
                actionCounter++;
                break;
            case 8:
                // Add claimed route (for any player)
//                actionCounter++;
//                routeSegments.get(0).setClaimed(true);
//                routeSegments.get(0).setOwner(getPlayer());
//                routeSegments.get(0).setPlayer_color(PLAYER_COLOR.BLUE);
//
//                routeSegments.get(2).setClaimed(true);
//                routeSegments.get(2).setOwner(getPlayer());
//                routeSegments.get(2).setPlayer_color(PLAYER_COLOR.RED);
//                sendToObservers("Added claimed route");
//                break;
                break;
            case 9:
                // Add chat message from any player
                actionCounter++;
                break;
            case 10:
                // Add game history entries
                actionCounter++;
                break;
            default:
                actionCounter = 0;
                break;
        }
    }
}
