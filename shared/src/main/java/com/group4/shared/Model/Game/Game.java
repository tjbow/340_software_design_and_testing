package com.group4.shared.Model.Game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.Deck.CARD_COLOR;
import com.group4.shared.Model.Map.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Deck.Decks;
import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Map.PLAYER_COLOR;
import com.group4.shared.Model.Map.RouteSegment;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.command.Client.CEndGameCommandData;
import com.group4.shared.command.Client.CUpdateStateCommandData;
import com.group4.shared.command.ClientCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Game
{

    private List<Player> players;
    private int playerCount;
    private String gameName;
    private GAME_STATUS status;

    private TurnHistory turnHistory;
    private ChatHistory chatHistory;
    private Decks decks;
    private CommandList commandList;

    private RouteList routes;
    private List<City> cities;

    private GameStats gameStats;

    public Game(String gameName, int playerCount)
    {
        this.gameName = gameName;
        this.playerCount = playerCount;
        this.players = new ArrayList<>();
        this.status = GAME_STATUS.WAITING;

        turnHistory = new TurnHistory();
        chatHistory = new ChatHistory();
        decks = new Decks();
        commandList = new CommandList();

        routes = new RouteList();
        cities = new ArrayList<>();

        //initialize the first player as player at pos 0
        gameStats = new GameStats();
    }

//    PLAYERS

    public List<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getPlayerByUserName(String userName)
    {
        for(Player player : players)
        {
            if(player.getUserName().equals(userName)) return player;
        }
        return null;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public int getCurrentPlayerSize()
    {
        return players.size();
    }

//    GAME SETUP
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public GAME_STATUS getStatus()
    {
        return status;
    }

    public void setStatus(GAME_STATUS status)
    {
        this.status = status;
    }

    public Decks getDecks() {
        return decks;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }

    public RouteList getRoutes() {
        return routes;
    }

    public void setRoutes(RouteList routes) {
        this.routes = routes;
    }

    public void importRoutes()
    {
        InputStream is = null;
        try
        {
            is = new FileInputStream(new File("shared/src/main/java/com/group4/shared/Model/Game/routes.json"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Set<RouteSegment> routeSet = mapper.readValue(is, new TypeReference<Set<RouteSegment>>() { });

            this.routes.setRouteList(routeSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importCities()
    {
        InputStream is = null;
        try
        {
            is = new FileInputStream(new File("shared/src/main/java/com/group4/shared/Model/Game/cities.json"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        List<City> cityList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            cityList = mapper.readValue(is, new TypeReference<List<City>>() { });

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(City c : cityList)
        {
            this.cities.add(c);
        }
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

//    -------GAMEPLAY---------------------------------------------------------------

    public void dealInitialGameCards()
    {
        //init the game decks with all the cards (this also shuffles each deck thrice)
        decks.startGameDeck();

        //give four train cards to each player and remove them from the game's tcdeck
        for(Player player : players)
        {
            int count = 4;
            for(Iterator<TrainCard> iterator = decks.getTrainCardDeck().getCardDeck().iterator(); iterator.hasNext();)
            {
                TrainCard current = iterator.next();
                iterator.remove();
                player.getPlayerHand().getTrainCards().getCardDeck().add(current);
                count--;
                if(count == 0)break;
            }
        }

        //give three destination cards to each player (0 or 1 to be returned)
        for(Player player : players)
        {
            int count = 3;
            for(Iterator<DestinationCard> iterator = decks.getDestinationCardDeck().getDestDeck().iterator(); iterator.hasNext();)
            {
                DestinationCard current = iterator.next();
                iterator.remove();
                player.getPlayerHand().getDestinationCards().getDestDeck().add(current);
                count--;
                if(count == 0)break;
            }
        }
    }

    public List<DestinationCard> playerTurn_DrawDestinationCards(String userName)
    {
        Player player = getPlayerByUserName(userName);
        List<DestinationCard> receivedCards = new ArrayList<>();

        int count = 3;
        for(Iterator<DestinationCard> iterator = decks.getDestinationCardDeck().getDestDeck().iterator(); iterator.hasNext();)
        {
            DestinationCard current = iterator.next();
            iterator.remove();
            player.getPlayerHand().getDestinationCards().getDestDeck().add(current);
            receivedCards.add(current);
            count--;
            if(count == 0)break;
        }

        updatePlayerState(player, true, false);

        return receivedCards;
    }

    public void playerTurn_ReturnDestinationCards(String userName, List<DestinationCard> returnedCard)
    {
        if(returnedCard == null)
        {
            return;
        }
        Player player = getPlayerByUserName(userName);

        ArrayList<Integer> idNums = new ArrayList<>();
        for(DestinationCard card : returnedCard)
        {
            idNums.add(card.getId());
        }

        for(Iterator<DestinationCard> iterator = player.getPlayerHand().getDestinationCards().getDestDeck().iterator(); iterator.hasNext();)
        {
            DestinationCard current = iterator.next();
            if(idNums.contains(current.getId()))
            {
                iterator.remove();
                decks.getDestinationCardDeck().add(current);
            }
        }
    }

    /**
     *
     * @pre if position == -1, draw face down card. If 0 <= position < 4, draw face up card
     *
     * @param userName
     * @param position
     */
    public boolean playerTurn_DrawTrainCard(String userName, int position)
    {
        Player player = getPlayerByUserName(userName);

        boolean drawSuccess;
        if(position == -1)
        {
            //face down
            drawSuccess = drawFaceDownCard(player);
        }
        else
        {
            //face up
            drawSuccess = drawFaceUpCard(player, position);
        }
        return drawSuccess;
    }

    private boolean drawFaceDownCard(Player player)
    {
        boolean success;
        Iterator<TrainCard> iterator = decks.getTrainCardDeck().getCardDeck().iterator();
        if(iterator.hasNext())
        {
            TrainCard current = iterator.next();
            iterator.remove();
            player.getPlayerHand().getTrainCards().getCardDeck().add(current);
            addTurn(new Message("Drew a face down train card.", player.getUserName(), player.getColor()));
            System.out.println(player.getUserName() + " drew a face down train card.");

            updatePlayerState(player, false, false);

            success = true;
        }
        else
        {
            //empty deck? reshuffle?
            checkToReshuffleDeck();
            success = false;
        }

        checkToReshuffleDeck();

        return success;
    }

    private boolean drawFaceUpCard(Player player, int position)
    {
        boolean success = false;
        int index = 0;
        for(Iterator<TrainCard> iterator = decks.getFaceUpDeck().getFaceUpCards().iterator(); iterator.hasNext();)
        {
            TrainCard current = iterator.next();
            if(index == position)
            {
                //draw the card
                iterator.remove();
                player.getPlayerHand().getTrainCards().getCardDeck().add(current);
                current.setVisible(false);
                addTurn(new Message("Drew a face up train card.", player.getUserName(), player.getColor()));

                //check if locomotive
                if(current.getColor() == CARD_COLOR.RAINBOW)
                {
                    System.out.println(player.getUserName() + " drew a face up locomotive card.");
                    player.setCurrentState(MOVE_STATE.DRAWN_FIRST_TRAIN_CARD);
                    updatePlayerState(player, false, false);
                }
                else
                {
                    System.out.println(player.getUserName() + " drew a face up train card.");
                    updatePlayerState(player, false, false);
                }
                success = true;
            }
            index++;
        }

//        replace the taken card in the deck
        Iterator<TrainCard> iterator = decks.getTrainCardDeck().getCardDeck().iterator();
        if(iterator.hasNext())
        {
            TrainCard current = iterator.next();
            current.setVisible(true);
            iterator.remove();
            decks.getFaceUpDeck().getFaceUpCards().add(position, current);
        }
        else
        {
            System.out.println("No cards in deck to add to face up");
        }

//        check for 3+ rainbow cards
        decks.checkTooManyRainbows();

        checkToReshuffleDeck();

        return success;
    }

    private void checkToReshuffleDeck()
    {
        int deckSize = decks.getTrainCardDeck().getCardDeck().size();
        int discardSize = decks.getDiscardDeck().getCardDeck().size();
        if(deckSize <= 1)
        {
            if(discardSize == 0)
            {
                System.out.println("No discarded cards to shuffle in");
            }
            else
            {
                System.out.println("Shuffled in discarded cards");
                decks.shuffleInDiscarded();
            }

            int faceUpSize = decks.getFaceUpDeck().getFaceUpCards().size();
            if(faceUpSize < 5)
            {
                int numToAdd = 5 - faceUpSize;
                for(Iterator<TrainCard> iterator = decks.getTrainCardDeck().getCardDeck().iterator(); iterator.hasNext();)
                {
                    TrainCard current = iterator.next();
                    current.setVisible(true);
                    iterator.remove();
                    decks.getFaceUpDeck().getFaceUpCards().add(current);
                    numToAdd--;
                    if(numToAdd == 0) break;
                }
            }
        }
    }

    private boolean canClaimDoubleRoute(String nameShort, Player player)
    {
        for(RouteSegment routeSegment : player.getClaimedRouteList().getRouteList())
        {
            if(routeSegment.getRouteId().contains(nameShort))
            {
                return false;
            }
        }
        return true;
    }

    public boolean playerTurn_claimRoute(String userName, RouteSegment claimedSegment, List<TrainCard> usedCards)
    {
        Player player = this.getPlayerByUserName(userName);

        //check if route is available.
        if(claimedSegment.isClaimed()) return false;

        //TODO: check for double-route rules (two routes between same two cities)
        String name = claimedSegment.getRouteId();
        String nameShort = name;
        if(name.contains("Right"))
        {
            nameShort = name.substring(0, name.length() - 6);
            if(!canClaimDoubleRoute(nameShort, player)) return false;
        }
        else if(name.contains("Left"))
        {
            nameShort = name.substring(0, name.length() - 5);
            if(!canClaimDoubleRoute(nameShort, player)) return false;
        }


        //check if enough train pieces
        if(player.getTrainCarsRemaining() < claimedSegment.getLength()) return false;

        //the route can be claimed, continue:

        // deduct train pieces from player
        int newAmount = player.getTrainCarsRemaining() - claimedSegment.getLength();
        player.setTrainCarsRemaining(newAmount);

        //add route to player's routes and mark it?
        routes.claimRoute(claimedSegment.getRouteId(), player);
        player.addClaimedRoute(claimedSegment);

        //deduct cards from player and discard them
        for(TrainCard usedCard : usedCards)
        {
            for(Iterator<TrainCard> iterator = player.getPlayerHand().getTrainCards().getCardDeck().iterator(); iterator.hasNext();)
            {
                TrainCard current = iterator.next();
                if(usedCard.getColor() == current.getColor())
                {
                    iterator.remove();
                    decks.getDiscardDeck().add(current);
                    break;
                }
            }
        }

        //if the face down deck is empty, shuffle in the discard deck
        checkToReshuffleDeck();

        //add points to player
        int addition = 0;
        switch (claimedSegment.getLength())
        {
            case 1:
                addition = 1;
                break;
            case 2:
                addition = 2;
                break;
            case 3:
                addition = 4;
                break;
            case 4:
                addition = 7;
                break;
            case 5:
                addition = 10;
                break;
            case 6:
                addition = 15;
                break;
            default:
                System.out.println("Invalid route length");
                break;
        }
        player.getStats().incrementRouteScore(addition);

        //finish turn
        updatePlayerState(player, false, true);

        //check if player has 0-2 remaining train pieces
        if(player.getTrainCarsRemaining() <= 2)
        {
            this.setStatus(GAME_STATUS.FINAL_TURN);
            addTurn(new Message("Final Round!", "GAME", "black"));
        }

        addTurn(new Message("Claimed the " + claimedSegment.getRouteColor() + " route from " + claimedSegment.getCityA() + " to " + claimedSegment.getCityB(), player.getUserName(), player.getColor()));

        return true;
    }

//    TURN HISTORY
    public TurnHistory getTurnHistory()
    {
        return turnHistory;
    }

    public void setTurnHistory(TurnHistory turnHistory)
    {
        this.turnHistory = turnHistory;
    }

    public void addTurn(Message turn)
    {
        turnHistory.add(turn);
    }

    private void updatePlayerState(Player player, boolean drawDestCards, boolean claimRoute)
    {
        CUpdateStateCommandData updateStateCommandData = new CUpdateStateCommandData();
        updateStateCommandData.setType("updatestate");
        updateStateCommandData.setUserName(player.getUserName());

        //done with turn (dest card draw or claim route, or can't draw more train cards)
        if(player.getCurrentState() == MOVE_STATE.DRAWN_FIRST_TRAIN_CARD || drawDestCards || claimRoute)
        {
            if(this.status == GAME_STATUS.FINAL_TURN) useLastTurn(player.getUserName());

            updateStateCommandData.setState(MOVE_STATE.NOT_MY_TURN);
            player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
            setTurnToNextPlayer();
            this.addCommand(updateStateCommandData);
        }
        //allows to draw another train card
        else if(player.getCurrentState() == MOVE_STATE.MY_TURN)
        {
            updateStateCommandData.setState(MOVE_STATE.DRAWN_FIRST_TRAIN_CARD);
            player.setCurrentState(MOVE_STATE.DRAWN_FIRST_TRAIN_CARD);
            this.addCommand(updateStateCommandData);
        }
    }

    public void setTurnToNextPlayer()
    {
        String currentPlayerName = null;
        for(Player player : players)
        {
            if(player.isTurn())
            {
                currentPlayerName = player.getUserName();
                player.setTurn(false);
                player.setCurrentState(MOVE_STATE.NOT_MY_TURN);
                break;
            }
        }

        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getUserName().equals(currentPlayerName))
            {
                if(i == players.size() - 1)
                {
                    players.get(0).setTurn(true);
                    players.get(0).setCurrentState(MOVE_STATE.MY_TURN);
                    System.out.println("It is now " + players.get(0).getUserName() + "\'s turn.");

                    CUpdateStateCommandData cmd = new CUpdateStateCommandData();
                    cmd.setType("updatestate");
                    cmd.setUserName(players.get(0).getUserName());
                    cmd.setState(MOVE_STATE.MY_TURN);
                    this.addCommand(cmd);

                    return;
                }
                else
                {
                    players.get(i + 1).setTurn(true);
                    players.get(i + 1).setCurrentState(MOVE_STATE.MY_TURN);
                    System.out.println("It is now " + players.get(i + 1).getUserName() + "\'s turn.");

                    CUpdateStateCommandData cmd = new CUpdateStateCommandData();
                    cmd.setType("updatestate");
                    cmd.setUserName(players.get(i + 1).getUserName());
                    cmd.setState(MOVE_STATE.MY_TURN);
                    this.addCommand(cmd);

                    return;
                }
            }
        }
    }

    Set<String> finishedPlayers = new HashSet<>();
    private void useLastTurn(String userName)
    {
        finishedPlayers.add(userName);

        if(finishedPlayers.size() == players.size())
        {
            this.setStatus(GAME_STATUS.FINISHED);
            calculateScores();
            setRanks();
            CEndGameCommandData cmd = new CEndGameCommandData();
            cmd.setType("endgame");
            cmd.setWasSuccessful(true);
            this.addCommand(cmd);
            System.out.println("The game " + gameName + " was ended.");
        }
    }

    public void setRanks()
    {
        List<Player> rankedList = new ArrayList<>();
        rankedList.add(players.get(0));
        int maxScore = 0;
        for(int i = 1; i < players.size(); i++)
        {
            for(int j = 0; j < rankedList.size(); j++)
            {
                if(players.get(i).getStats().getTotalScore() > rankedList.get(j).getStats().getTotalScore())
                {
                    rankedList.add(j, players.get(i));
                    break;
                }
                else if(j + 1 == rankedList.size())
                {
                    rankedList.add(players.get(i));
                    break;
                }
            }
        }

        int rank = 1;
        for(Player player : rankedList)
        {
            player.getStats().setRank(rank);
            rank++;
        }
    }


    private void calculateScores()
    {

        for (Player player : players)
        {
            //add or subtract destination card points for each player
            for (DestinationCard destCard : player.getPlayerHand().getDestinationCards().getDestDeck())
            {
                if (player.destinationComplete(destCard.getCityA(), destCard.getCityB()))
                {
                    player.getStats().incrementDestinationScore(destCard.getPoints());
                }
                else
                {
                    player.getStats().decrementDestinationScore(destCard.getPoints());
                }
            }

            //add longest path
            if (player.isLongestPath())
            {
                player.getStats().setLongestPathScore();
            }

        }

    }

    private void setLongestPathPlayer()
    {
        Player longestPathWinner = players.get(0);
        int longestPathSize = 0;

        for (Player player : players)
        {
            if (player.getLongestPath() > longestPathSize)
            {
                longestPathSize = player.getLongestPath();
                longestPathWinner = player;
            }
        }

        longestPathWinner.setLongestPath(true);
    }

//    CHAT HISTORY
    public ChatHistory getChatHistory()
    {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory)
    {
        this.chatHistory = chatHistory;
    }

    public void addMessage(Message message)
    {
        chatHistory.add(message);
    }

//    COMMAND LIST
    public CommandList getCommandList()
    {
        return commandList;
    }

    public void setCommandList(CommandList commandList)
    {
        this.commandList = commandList;
    }

    public boolean addCommand(ClientCommand command)
    {
        boolean added = commandList.add(command);
        command.setCommandNumber(this.getNewCommandIndex());
        return added;
    }

    public int getNewCommandIndex()
    {
        int index = commandList.size();
//        System.out.println("index is: " + index);
        return index;
    }
}
