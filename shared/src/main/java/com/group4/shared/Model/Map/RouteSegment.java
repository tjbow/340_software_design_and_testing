package com.group4.shared.Model.Map;

import com.group4.shared.Model.Player;

import java.io.Serializable;

/**
 * Created by Andrew Gill on 5/21/2017.
 */

public class RouteSegment implements Serializable{

    String routeId;
    boolean claimed;

    //Player owner;
    int length;
    int score;
    String cityA;
    String cityB;
    float x1Constraint;  //value between 1 and 100 used for positioning on map
    float y1Constraint;
    float x2Constraint;
    float y2Constraint;

    public PLAYER_COLOR getPlayer_color() {
        return player_color;
    }

    public void setPlayer_color(PLAYER_COLOR player_color) {
        this.player_color = player_color;
    }

    PLAYER_COLOR player_color = null;


    boolean isHighlighted = false;

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public float getX1Constraint() {
        return x1Constraint;
    }

    public void setX1Constraint(float x1Constraint) {
        this.x1Constraint = x1Constraint;
    }

    public float getY1Constraint() {
        return y1Constraint;
    }

    public void setY1Constraint(float y1Constraint) {
        this.y1Constraint = y1Constraint;
    }

    public float getX2Constraint() {
        return x2Constraint;
    }

    public void setX2Constraint(float x2Constraint) {
        this.x2Constraint = x2Constraint;
    }

    public float getY2Constraint() {
        return y2Constraint;
    }

    public void setY2Constraint(float y2Constraint) {
        this.y2Constraint = y2Constraint;
    }

    ROUTE_COLOR routeColor;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    //public Player getOwner() {
    //    return owner;
    //}

    //public void setOwner(Player owner) {
    //    this.owner = owner;
    //}

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCityA() {
        return cityA;
    }

    public void setCityA(String cityA) {
        this.cityA = cityA;
    }

    public String getCityB() {
        return cityB;
    }

    public void setCityB(String cityB) {
        this.cityB = cityB;
    }

    public ROUTE_COLOR getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(ROUTE_COLOR routeColor) {
        this.routeColor = routeColor;
    }
}
