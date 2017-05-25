package com.group4.shared.Model;

/**
 * Created by tyler on 5/22/17.
 */

public class DestinationCard
{

    private String id;
    private City cityA;
    private City cityB;
    private int points;

    public DestinationCard(String id, City cityA, City cityB, int points)
    {
        this.id = id;
        this.cityA = cityA;
        this.cityB = cityB;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City getCityA() {
        return cityA;
    }

    public void setCityA(City cityA) {
        this.cityA = cityA;
    }

    public City getCityB() {
        return cityB;
    }

    public void setCityB(City cityB) {
        this.cityB = cityB;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
