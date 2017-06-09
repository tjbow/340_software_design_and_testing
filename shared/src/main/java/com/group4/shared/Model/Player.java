package com.group4.shared.Model;

import com.group4.shared.Model.Deck.PlayerHand;
import com.group4.shared.Model.Game.MOVE_STATE;
import com.group4.shared.Model.Graph.RoutePaths;
import com.group4.shared.Model.Map.RouteList;
import com.group4.shared.Model.Map.RouteSegment;

import java.util.Comparator;
import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Player
{

    public static final String BLUE = "blue";
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";
    public static final String BLACK = "black";

    private UUID id;
    private User user;
    private String userName;
    private String color;

    private PlayerHand playerHand;
    private boolean longestPath;
    private int trainCarsRemaining;
    private RouteList claimedRouteList;
    private PlayerStats stats;
    private boolean winning;
    private boolean turn;
    private boolean firstDestCardsSelected;

    private MOVE_STATE currentState;

    public Player(User user)
    {
        this.userName = user.getUsername();
        this.id = user.getId();
        this.turn = false;
        this.firstDestCardsSelected = false;
    }

    public void initializeGame()
    {
        playerHand = new PlayerHand();
        longestPath = false;
        trainCarsRemaining = 15;
        claimedRouteList = new RouteList();
        stats = new PlayerStats();
        winning = false;
    }

    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(PlayerHand playerHand) {
        this.playerHand = playerHand;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLongestPath() {
        return longestPath;
    }

    public void setLongestPath(boolean longestPath) {
        this.longestPath = longestPath;

        if(longestPath == true){
            stats.setLongestPathScore();
        }
        else{
            stats.removeLongestPathScore();
        }
    }

    public int getTrainCarsRemaining() {
        return trainCarsRemaining;
    }

    public void setTrainCarsRemaining(int trainCarsRemaining) {
        this.trainCarsRemaining = trainCarsRemaining;
    }

    public RouteList getClaimedRouteList() {
        return claimedRouteList;
    }

    public void setClaimedRouteList(RouteList claimedRouteList) {
        this.claimedRouteList = claimedRouteList;
    }

    public boolean addClaimedRoute(RouteSegment claimedSegment)
    {
        return this.claimedRouteList.getRouteList().add(claimedSegment);
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public void decrementCars(int carsUsed){
        trainCarsRemaining -= carsUsed;
    }

    public int getScore(){
        return stats.getClaimedRouteScore();
    }

    public void incrementRouteScore(int score){
        stats.incrementRouteScore(score);
    }

    public void decrementDestinationScore(int negativeScore){
        stats.decrementDestinationScore(negativeScore);
    }

    public void incrementDestinationScore(int positiveScore){
        stats.incrementDestinationScore(positiveScore);
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isFirstDestCardsSelected()
    {
        return firstDestCardsSelected;
    }

    public void setFirstDestCardsSelected(boolean firstDestCardsSelected)
    {
        this.firstDestCardsSelected = firstDestCardsSelected;
    }

    public MOVE_STATE getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(MOVE_STATE currentState)
    {
        this.currentState = currentState;
    }

    /**
     * Returns the longest path currently held by the player
     * @return the longest path
     */
    public int getLongestPath()
    {
        RoutePaths routePaths = buildPlayerRoutePaths();
        return routePaths.longestPath();
    }

    /**
     * Returns whether the destination that goes from cityA to cityB is complete
     * @param cityA the first city in the destination
     * @param cityB the second city in the destination
     * @return whether the destination is complete
     */
    public boolean destinationComplete(String cityA, String cityB)
    {
        RoutePaths routePaths = buildPlayerRoutePaths();
        return routePaths.destinationComplete(cityA, cityB);
    }

    /**
     * Builds a graph of player routes from the route list
     * @return a graph of player routes
     */
    public RoutePaths buildPlayerRoutePaths()
    {
        RoutePaths routePaths = new RoutePaths();
        for(RouteSegment route : claimedRouteList.getRouteList())
        {
            routePaths.add(route);
        }
        return routePaths;
    }
}
