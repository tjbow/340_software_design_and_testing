package com.group4.shared.Model;

/**
 * Created by abgill on 5/22/2017.
 */

public class City {
    private float xConstraint;
    private float yConstraint;

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

    public City(float xConstraint, float yConstraint) {

        this.xConstraint = xConstraint;
        this.yConstraint = yConstraint;
    }
}
