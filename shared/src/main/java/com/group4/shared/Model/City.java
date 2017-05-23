package com.group4.shared.Model;

/**
 * Created by abgill on 5/22/2017.
 */

public class City {
    private float xConstraint;
    private float yConstraint;
    private String name;

    public City(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getxConstraint() {
        return xConstraint;
    }

    public void setxConstraint(float xConstraint) {
        this.xConstraint = xConstraint;
    }

    public float getyConstraint() {
        return yConstraint;
    }

    public void setyConstraint(float yConstraint) {
        this.yConstraint = yConstraint;
    }

    public City(float xConstraint, float yConstraint, String name) {
        this.name = name;
        this.xConstraint = xConstraint;
        this.yConstraint = yConstraint;
    }
}
