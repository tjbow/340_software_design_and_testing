package com.group4.shared.Model;

/**
 * Created by abgill on 5/22/2017.
 */

public class City {
    private float xConstraint;
    private float yConstraint;
    private String name;
    private float labelX;
    private float labelY;

    public float getLabelX() {
        return labelX;
    }

    public void setLabelX(float labelX) {
        this.labelX = labelX;
    }

    public float getLabelY() {
        return labelY;
    }

    public void setLabelY(float labelY) {
        this.labelY = labelY;
    }

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
