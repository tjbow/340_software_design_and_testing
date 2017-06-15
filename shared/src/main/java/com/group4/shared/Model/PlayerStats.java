package com.group4.shared.Model;

import java.io.Serializable;

/**
 * Created by Russell Fitzpatrick on 6/4/2017.
 */

public class PlayerStats implements Serializable{

    private int rank;
    private int claimedRouteScore;
    private int completedDestinationCards;
    private int incompleteDestinationCards;
    private int longestPathScore;

    public PlayerStats(){
        claimedRouteScore = 0;
        completedDestinationCards = 0;
        incompleteDestinationCards = 0;
        longestPathScore = 0;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getClaimedRouteScore() {
        return claimedRouteScore;
    }

    public void setClaimedRouteScore(int claimedRouteScore) {
        this.claimedRouteScore = claimedRouteScore;
    }

    public int getCompletedDestinationCards() {
        return completedDestinationCards;
    }

    public void setCompletedDestinationCards(int completedDestinationCards) {
        this.completedDestinationCards = completedDestinationCards;
    }

    public int getIncompleteDestinationCards() {
        return incompleteDestinationCards;
    }

    public void setIncompleteDestinationCards(int incompleteDestinationCards) {
        this.incompleteDestinationCards = incompleteDestinationCards;
    }

    public int getLongestPathScore() {
        return longestPathScore;
    }

    public void setLongestPathScore() {
        this.longestPathScore = 10;
    }

    public void removeLongestPathScore(){
        this.longestPathScore = 0;
    }

    public int getTotalScore() {

        return claimedRouteScore + completedDestinationCards + longestPathScore + incompleteDestinationCards;
    }

    public void incrementRouteScore(int score){
        claimedRouteScore += score;
    }

    public void incrementDestinationScore(int score){
        completedDestinationCards += score;
    }

    public void decrementDestinationScore(int score){
        incompleteDestinationCards -= score;
    }

}
