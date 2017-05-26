package com.group4.shared.Model;

/**
 * Created by tyler on 5/22/17.
 */

public class DestinationCard
{

    private int id;
    private String cityA;
    private String cityB;
    private int points;

    public DestinationCard(int id, String cityA, String cityB, int points)
    {
        this.id = id;
        this.cityA = cityA;
        this.cityB = cityB;
        this.points = points;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCityA()
    {
        return cityA;
    }

    public void setCityA(String cityA)
    {
        this.cityA = cityA;
    }

    public String getCityB()
    {
        return cityB;
    }

    public void setCityB(String cityB)
    {
        this.cityB = cityB;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}
