package com.group4.shared.Model;

import java.util.UUID;

/**
 * Created by Russell Fitzpatrick on 5/13/2017.
 */

public class Player {

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
    private int score;
    private boolean winning;
    private boolean turn;

    public Player(User user)
    {
        this.userName = user.getUsername();
        this.id = user.getId();
        this.turn = false;
    }

    public void initializeGame()
    {
        playerHand = new PlayerHand();
        longestPath = false;
        trainCarsRemaining = 45;
        claimedRouteList = new RouteList();
        score = 0;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void decrementCars(int carsUsed){
        trainCarsRemaining -= carsUsed;
    }

    public void incrementScore(int score){
        this.score += score;
    }

    public void decrementScore(int negativeScore){
        this.score -= negativeScore;
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
}
