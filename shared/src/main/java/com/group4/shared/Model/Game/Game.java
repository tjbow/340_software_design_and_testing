package com.group4.shared.Model.Game;

import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.Map.City;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Deck.Decks;
import com.group4.shared.Model.Deck.DestinationCard;
import com.group4.shared.Model.Deck.TrainCard;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.TurnHistory;
import com.group4.shared.command.ClientCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public void playerTurn_DrawDestinationCards(String userName)
    {
        Player player = getPlayerByUserName(userName);

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

    public void setTurnToNextPlayer()
    {
        String currentPlayer = gameStats.getPlayerCurrentTurn();
        List<Player> list = getPlayers();
        String getNewTurnPlayer = null;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getUserName().equals(currentPlayer))
            {
                if(i == list.size() - 1)
                {
                    getNewTurnPlayer = list.get(0).getUserName();
                    gameStats.setPlayerCurrentTurn(getNewTurnPlayer);
                    return;
                }
                else
                {
                    getNewTurnPlayer = list.get(i + 1).getUserName();
                    gameStats.setPlayerCurrentTurn(getNewTurnPlayer);
                    return;
                }
            }
        }
        gameStats.setPlayerCurrentTurn(null);
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
        return commandList.add(command);
    }

    public int getNewCommandIndex()
    {
        int index = commandList.size();
        System.out.println("index is: " + index);
        return index;
    }
}
